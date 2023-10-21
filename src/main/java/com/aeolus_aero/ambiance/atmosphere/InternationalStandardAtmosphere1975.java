package com.aeolus_aero.ambiance.atmosphere;

import com.aeolus_aero.ambiance.atmosphere.layer.LinearTemperatureLayer;

/**
 * International Standard Atmosphere defined in ISO2533:1975.
 *
 * Using SI units (m, kg, s, Kelvin)
 *
 */
public class InternationalStandardAtmosphere1975 extends LayeredAtmosphere {

    public InternationalStandardAtmosphere1975() {
        super(-2000, 80000, 1.4, 287.05287, 6356766., 9.80665);
        //Temperature layers from table 4.
        layers.add(new LinearTemperatureLayer(-2000, 301.15, 1.2777e5, -0.0065));
        layers.add(new LinearTemperatureLayer(0.000, 288.15, 1.01325e5, -0.0065));
        layers.add(new LinearTemperatureLayer(11000, 216.65, 2.2632e4, 0.));
        layers.add(new LinearTemperatureLayer(20000, 216.65, 5.4748e3, 0.001));
        layers.add(new LinearTemperatureLayer(32000, 228.65, 8.6801e2, 0.0028));
        layers.add(new LinearTemperatureLayer(47000, 270.65, 1.1090e2, 0));
        layers.add(new LinearTemperatureLayer(51000, 270.65, 6.6938e1, -0.0028));
        layers.add(new LinearTemperatureLayer(71000, 214.65, 3.9564e0, -0.002));
    }

    /**
     * Computes the dynamic viscosity at a given geometric altitude.
     *
     * @param z Geometric altitude
     * @return Density
     */
    @Override
    public double dynamicViscosity(double z) throws IllegalArgumentException {
        final double betaS = 1.458e-6; //From Table 1
        final double S = 110.4; //From Table 1
        final double temp = this.temperature(z);
        return betaS * Math.pow(temp, 1.5) / (temp + S);
    }

}
