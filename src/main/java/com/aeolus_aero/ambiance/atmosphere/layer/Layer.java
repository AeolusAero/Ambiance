package com.aeolus_aero.ambiance.atmosphere.layer;

import java.util.Comparator;

/**
 * Base class for an atmospheric layer
 *
 */
public abstract class Layer {

    final double h0;
    final double t0;
    final double p0;

    public static final Comparator<Layer> BaseAltutideComparator = (Layer l1, Layer l2) -> Double.compare(l1.h0, l2.h0);

    /**
     *
     * @param h0 Altitude at the base of the layer
     * @param t0 Temperature at the base of the layer
     * @param p0 Pressure at the base of the layer
     */
    Layer(double h0, double t0, double p0) {
        this.h0 = h0;
        this.t0 = t0;
        this.p0 = p0;
    }

    /**
     *
     * @return Altitude at the base of the layer
     */
    public double getBaseAlt() {
        return h0;
    }

    /**
     *
     * @return Temperature at the base of the layer
     */
    public double getBaseTemp() {
        return t0;
    }

    /**
     *
     * @return Temperature at the base of the layer
     */
    public double getBasePressure() {
        return p0;
    }

    /**
     * Returns the temperature at a given altitude.
     *
     * @param h Altitude
     * @return Temperature at a given altitude
     */
    public abstract double temperature(final double h);

    /**
     * Returns the pressure at a given altitude.
     *
     * @param h Altitude
     * @param R Specific gas constant
     * @param gn Standard acceleration of free fall
     * @return Pressure at a given altitude
     */
    public abstract double pressure(final double h, final double R, final double gn);

    /**
     * Returns the density at a given altitude. It is calculated from the
     * pressure and the temperature using the perfect gas law.
     *
     * @param h Altitude
     * @param R Specific gas constant
     * @param gn Standard acceleration of free fall
     * @return Density at a given altitude
     */
    public double density(final double h, final double R, final double gn) {
        return pressure(h, R, gn) / (R * temperature(h));
    }

    /**
     * Returns the speed of sound at a given altitude.
     *
     * @param h Altitude
     * @param kappa Adiabatic index
     * @param R Specific gas constant
     * @return Speed of sound at a given altitude
     */
    public double speedOfSound(final double h, final double kappa, final double R) {
        return Math.sqrt(kappa * R * temperature(h));
    }
}
