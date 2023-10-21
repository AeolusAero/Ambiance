package com.aeolus_aero.ambiance.atmosphere;

import com.aeolus_aero.ambiance.atmosphere.layer.Layer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An Atmosphere made up of descrete Layers. It is valid within its altitude
 * bounds.
 *
 *
 */
public abstract class LayeredAtmosphere extends Atmosphere {

    private final double R; //Specific gas constant
    private final double gn; //Standard acceleration of free fall
    private final double kappa; //Adiabatic index

    final double alt_max; //Max Geopotential altitude
    final double alt_min; //Min Geopotential altitude
    final List<Layer> layers; //Layers, with altitudes in geopotential altitude

    /**
     *
     * @param alt_min Min Geopotential altitude
     * @param alt_max Max Geopotential altitude
     * @param kappa Adiabatic index
     * @param R Specific gas constant
     * @param radius Nominal planet radius
     * @param gn Standard acceleration of free fall
     */
    public LayeredAtmosphere(final double alt_min, final double alt_max, final double kappa, final double R, final double radius, final double gn) {
        super(radius);
        this.alt_min = alt_min;
        this.alt_max = alt_max;
        this.kappa = kappa;
        this.R = R;
        this.gn = gn;

        this.layers = new ArrayList<>();
    }

    /**
     *
     * @return Max geopotential altitude
     */
    public double getAlt_max() {
        return alt_max;
    }

    /**
     *
     * @return Min geopotential altitude
     */
    public double getAlt_min() {
        return alt_min;
    }

    /**
     * Identifies the layer to use Ffor a given altitude.
     *
     * Throws IllegalArgumentException when no layer can be found, or when
     * altitude is out of bounds.
     *
     * @param h Geopotential altitude
     * @return The Layer for this altitude.
     */
    Layer findLayer(double h) throws IllegalArgumentException {
        Optional<Layer> optionalLayer = layers.stream().filter((l) -> l.getBaseAlt() <= h).max(Layer.BaseAltutideComparator);
        if (optionalLayer.isEmpty() || h < alt_min || h > alt_max) {
            throw new IllegalArgumentException("Geopotential altitude out of bounds");
        }
        return optionalLayer.get();
    }

    /**
     * Computes the temperature at a given geometric altitude.
     *
     * @param z Geometric altitude
     * @return Temperature
     */
    @Override
    public double temperature(final double z) throws IllegalArgumentException {
        final double h = geopotentialAltitude(z);
        return findLayer(h).temperature(h);
    }

    /**
     * Computes the density at a given geometric altitude.
     *
     * @param z Geometric altitude
     * @return Density
     */
    @Override
    public double density(double z) throws IllegalArgumentException {
        final double h = geopotentialAltitude(z);
        return findLayer(h).density(h, R, gn);
    }

    /**
     * Computes the pressure at a given geometric altitude.
     *
     * @param z Geometric altitude
     * @return Pressure
     */
    @Override
    public double pressure(double z) throws IllegalArgumentException {
        final double h = geopotentialAltitude(z);
        return findLayer(h).pressure(h, R, gn);
    }

    /**
     * Computes the speed of sound at a given geometric altitude.
     *
     * @param z Geometric altitude
     * @return Speed of sound
     */
    @Override
    public double speedOfSound(double z) throws IllegalArgumentException {
        final double h = geopotentialAltitude(z);
        return findLayer(h).speedOfSound(h, kappa, R);
    }

}
