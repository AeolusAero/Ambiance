package com.aeolus_aero.ambiance.atmosphere;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class InternationalStandardAtmosphere1975Test {

    public InternationalStandardAtmosphere1975Test() {
    }

    /**
     * Test that probing the InternationalStandardAtmosphere1975 within and on
     * its bounds does not fail.
     */
    @Test
    public void testInBounds() {
        InternationalStandardAtmosphere1975 a = new InternationalStandardAtmosphere1975();
        //Test in bounds
        try {
            a.temperature(a.geometricAltitude(a.getAlt_max()));
            a.temperature(a.geometricAltitude(a.getAlt_min()));
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    /**
     * Test that probing the InternationalStandardAtmosphere1975 out of bounds
     * fails.
     */
    @Test
    public void testOutOfBounds() {
        InternationalStandardAtmosphere1975 a = new InternationalStandardAtmosphere1975();
        //Test in bounds
        try {
            a.temperature(a.geometricAltitude(1.00001 * a.getAlt_max()));
            a.temperature(a.geometricAltitude(1.00001 * a.getAlt_min()));
            fail();
        } catch (IllegalArgumentException e) {
            //That is ecpected. Pass.
        }
    }

    /**
     * Test of geometricAltitude method, of class
     * InternationalStandardAtmosphere1975.
     */
    @Test
    public void testGeometricAltitude() {
        System.out.println("geometricAltitude");
        InternationalStandardAtmosphere1975 a = new InternationalStandardAtmosphere1975();
        assertEquals(0, a.geometricAltitude(0), 0.01);
        assertEquals(81019.63, a.geometricAltitude(80000), 0.01);
    }

    /**
     * Test of geopotentialAltitude method, of class
     * InternationalStandardAtmosphere1975.
     */
    @Test
    public void testGeopotentialAltitude() {
        System.out.println("geopotentialAltitude");
        InternationalStandardAtmosphere1975 a = new InternationalStandardAtmosphere1975();
        assertEquals(-2000, a.geopotentialAltitude(-1999.37), 0.01);
        assertEquals(0, a.geopotentialAltitude(0), 0.01);
        assertEquals(80000., a.geopotentialAltitude(81019.63), 0.01);
    }

    /**
     * Test of temperature method, of class InternationalStandardAtmosphere1975.
     */
    @Test
    public void testTemperature() {
        System.out.println("temperature");
        InternationalStandardAtmosphere1975 a = new InternationalStandardAtmosphere1975();

        //Temps at layer bases
        assertEquals(301.15, a.temperature(a.geometricAltitude(-2000)), 1.e-6);
        assertEquals(288.15, a.temperature(a.geometricAltitude(0)), 1.e-6);
        assertEquals(216.65, a.temperature(a.geometricAltitude(11000)), 1.e-6);
        assertEquals(216.65, a.temperature(a.geometricAltitude(20000)), 1.e-6);
        assertEquals(228.65, a.temperature(a.geometricAltitude(32000)), 1.e-6);
        assertEquals(270.65, a.temperature(a.geometricAltitude(47000)), 1.e-6);
        assertEquals(270.65, a.temperature(a.geometricAltitude(51000)), 1.e-6);
        assertEquals(214.65, a.temperature(a.geometricAltitude(71000)), 1.e-6);
        assertEquals(196.65, a.temperature(a.geometricAltitude(80000)), 1.e-6);

        //Computed temps        
        assertEquals(297.902, a.temperature(-1500), 0.01);
        assertEquals(288.15, a.temperature(0), 0.01);
        assertEquals(255.676, a.temperature(5000), 0.01);
        assertEquals(216.650, a.temperature(15000), 0.01);
        assertEquals(217.581, a.temperature(21000), 0.01);
        assertEquals(236.513, a.temperature(35000), 0.01);
        assertEquals(270.65, a.temperature(50000), 0.01);
        assertEquals(247.021, a.temperature(60000), 0.01);
        assertEquals(208.399, a.temperature(75000), 0.01);

    }

    /**
     * Test of density method, of class InternationalStandardAtmosphere1975.
     */
    @Test
    public void testDensity() {
        System.out.println("density");
        InternationalStandardAtmosphere1975 a = new InternationalStandardAtmosphere1975();
        //Computed pressures      
        assertEquals(1.4114e0, a.density(-1500), 1.e-4);
        assertEquals(1.225e0, a.density(0), 1.e-4);
        assertEquals(7.3643e-1, a.density(5000), 1.e-5);
        assertEquals(1.9476e-1, a.density(15000), 1.e-5);
        assertEquals(7.5715e-2, a.density(21000), 1.e-5);
        assertEquals(8.4634e-3, a.density(35000), 1.e-6);
        assertEquals(1.0269e-3, a.density(50000), 1.e-6);
        assertEquals(3.0968e-4, a.density(60000), 1.e-7);
        assertEquals(3.9921e-5, a.density(75000), 1.e-8);
    }

    /**
     * Test of pressure method, of class InternationalStandardAtmosphere1975.
     */
    @Test
    public void testPressure() {
        System.out.println("pressure");
        InternationalStandardAtmosphere1975 a = new InternationalStandardAtmosphere1975();
        //Pressures at layer bases
        assertEquals(1.2777e5, a.pressure(a.geometricAltitude(-2000)), 1.e-1);
        assertEquals(1.01325e5, a.pressure(a.geometricAltitude(0.000)), 1.e-1);
        assertEquals(2.2632e4, a.pressure(a.geometricAltitude(11000)), 1.e-1);
        assertEquals(5.4748e3, a.pressure(a.geometricAltitude(20000)), 1.e-1);
        assertEquals(8.6801e2, a.pressure(a.geometricAltitude(32000)), 1.e-1);
        assertEquals(1.1090e2, a.pressure(a.geometricAltitude(47000)), 1.e-1);
        assertEquals(6.6938e1, a.pressure(a.geometricAltitude(51000)), 1.e-2);
        assertEquals(3.9564e0, a.pressure(a.geometricAltitude(71000)), 1.e-3);

        //Computed pressures      
        assertEquals(1.2069e5, a.pressure(-1500), 1.e1);
        assertEquals(1.01325e5, a.pressure(0), 1.e0);
        assertEquals(5.4048e4, a.pressure(5000), 1.e0);
        assertEquals(1.2111e4, a.pressure(15000), 1.e0);
        assertEquals(4.7289e3, a.pressure(21000), 1.e-1);
        assertEquals(5.7459e2, a.pressure(35000), 1.e-2);
        assertEquals(7.9779e1, a.pressure(50000), 1.e-2);
        assertEquals(2.1958e1, a.pressure(60000), 1.e-3);
        assertEquals(2.3881e0, a.pressure(75000), 1.e-4);
    }

    /**
     * Test of speedOfSound method, of class
     * InternationalStandardAtmosphere1975. In absence of tabulated data in the
     * Standard, this test uses tabulated data from the US Standard Atmosphere
     * 1976.
     */
    @Test
    public void testSpeedOfSound() {
        System.out.println("speedOfSound");
        InternationalStandardAtmosphere1975 a = new InternationalStandardAtmosphere1975();
        assertEquals(347.70, a.speedOfSound(-1950), 0.01);
        assertEquals(340.29, a.speedOfSound(0), 0.01);
        assertEquals(316.45, a.speedOfSound(6000), 0.01);
        assertEquals(295.15, a.speedOfSound(11000), 0.01);
        assertEquals(295.07, a.speedOfSound(20000), 0.01);
        assertEquals(301.05, a.speedOfSound(29000), 0.01);
        assertEquals(329.80, a.speedOfSound(50000), 0.01);
    }

    /**
     * Test of dynamicViscosity method, of class
     * InternationalStandardAtmosphere1975. In absence of tabulated data in the
     * Standard, this test uses tabulated data from the US Standard Atmosphere
     * 1976.
     */
    @Test
    public void testDynamicViscosity() {
        System.out.println("dynamicViscosity");
        InternationalStandardAtmosphere1975 a = new InternationalStandardAtmosphere1975();
        assertEquals(1.8206e-5, a.dynamicViscosity(-1000), 1.e-8);
        assertEquals(1.7894e-5, a.dynamicViscosity(0), 1.e-8);
        assertEquals(1.5781e-5, a.dynamicViscosity(6500), 1.e-8);
        assertEquals(1.4490e-5, a.dynamicViscosity(25000), 1.e-8);
        assertEquals(1.3759e-5, a.dynamicViscosity(75000), 1.e-8);
    }

}
