package com.aeolus_aero.ambiance.atmosphere.layer;

/**
 * An atmospheric layer with a constant temperature gradient.
 *
 */
public class LinearTemperatureLayer extends Layer {

    private final double beta; //Constant temperature gradient

    /**
     *
     * @param h0 Geopotential Altitude at the base of the layer
     * @param t0 Temperature at the base of the layer
     * @param p0 Pressure at the base of the layer
     * @param beta Temperature gradient
     */
    public LinearTemperatureLayer(double h0, double t0, double p0, double beta) {
        super(h0, t0, p0);
        this.beta = beta;
    }

    /**
     * Returns the temperature at a given geopotential altitude.
     *
     * @param h Geopotential Altitude
     * @return Temperature at a given altitude
     */
    @Override
    public double temperature(final double h) {
        return t0 + (h - h0) * beta;
    }

    /**
     * Returns the pressure at a given geopotential altitude.
     *
     * @param h Geopotential Altitude
     * @param R Specific gas constant
     * @param gn Standard acceleration of free fall
     * @return Pressure at a given altitude
     */
    @Override
    public double pressure(final double h, final double R, final double gn) {
        if (beta == 0) {
            return p0 * Math.exp(-gn / (R * temperature(h)) * (h - h0)); //Eq (13) from ISO 2533:1975.
        } else {
            return p0 * Math.pow(1 + beta / t0 * (h - h0), -gn / (beta * R)); //Eq (12) from ISO 2533:1975.
        }
    }

}
