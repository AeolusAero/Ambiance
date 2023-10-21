package com.aeolus_aero.ambiance.atmosphere;

public abstract class Atmosphere {

    private final double planetRadius; //Nominal planet radius

    public Atmosphere(final double radius) {
        this.planetRadius = radius;
    }

    public abstract double temperature(double alt);

    public abstract double density(double alt);

    public abstract double pressure(double alt);

    public abstract double speedOfSound(double alt);

    public abstract double dynamicViscosity(double alt);

    /**
     * Computes the geopotential altitude.
     *
     * @param z Geometric altitude in meters
     * @return Geopotential altitude in meters
     */
    public double geopotentialAltitude(final double z) {
        return planetRadius * z / (planetRadius + z); //Eq (8) in ISO2533-1975.

    }

    /**
     * Computes the geometric altitude.
     *
     * @param h Geopotential altitude in meters
     * @return Geometric altitude in meters
     */
    public double geometricAltitude(final double h) {
        return planetRadius * h / (planetRadius - h); //Eq (9) in ISO2533-1975.
    }

}
