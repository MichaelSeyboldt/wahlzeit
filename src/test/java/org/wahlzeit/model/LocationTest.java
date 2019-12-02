package org.wahlzeit.model;

import com.google.appengine.repackaged.com.google.gson.internal.$Gson$Preconditions;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LocationTest {
    private CartesianCoordinate zero, tenx, teny, tenz;
    private SphericCoordinate sph0, sph1, sph2;

    @Before
    public void setup() {
        zero = new CartesianCoordinate(0, 0, 0);
        tenx = new CartesianCoordinate(10, 0, 0);
        teny = new CartesianCoordinate(0, 10, 0);
        tenz = new CartesianCoordinate(0, 0, 10);
        sph0 = new SphericCoordinate(1, 0, 0);
        sph1 = new SphericCoordinate(1, Math.PI, 0);
        sph2 = new SphericCoordinate(1, 0, 0);

    }


    @Test
    public void testDistancesCartesian() {
        assertEquals(zero.getCartesianDistance(zero), 0., 1e-15);
        assertEquals(zero.getCartesianDistance(tenx), 10., 1e-15);
        assertEquals(zero.getCartesianDistance(teny), 10., 1e-15);
        assertEquals(zero.getCartesianDistance(tenz), 10., 1e-15);

    }

    @Test
    public void testAngleSpheric() {
        assertEquals(sph0.getCentralAngle(sph1), Math.PI, 1e-10);
        assertEquals(sph0.getCentralAngle(sph2), 0, 1e-10);
        assertEquals(sph1.getCentralAngle(sph1), 0, 1e-10);
    }


    @Test
    public void testequalityCartesian() {
        CartesianCoordinate tx = new CartesianCoordinate(10, 0, 0);
        assertTrue(zero.equals(zero));
        assertFalse(zero.equals(tenx));
        assertTrue(tenx.equals(tx));
        assertFalse(tenx.equals(teny));
        Location lx = new Location(tenx);
        Location lx2 = new Location(tx);
        Location ly = new Location(teny);
        assertTrue(lx.equals(lx2));
        assertFalse(lx.equals(ly));
        assertFalse(lx.equals(tenx));

    }

    @Test
    public void testEqualitySpheric() {
        assertTrue(sph0.equals(sph0));
        assertTrue(sph0.equals(sph2));
        assertFalse(sph0.equals(sph1));

    }

    @Test
    public void testEqalityConversion() {
        assertEquals(sph0, sph0.asCartesianCoordinate().asSphericCoordinate());
        assertTrue(sph0.equals(sph0.asCartesianCoordinate()));
        assertEquals(zero, zero.asSphericCoordinate().asCartesianCoordinate());
        assertTrue(zero.equals(zero.asSphericCoordinate()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstrCart() {
        CartesianCoordinate nah = new CartesianCoordinate(Double.NaN, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstrSpher(){
        SphericCoordinate nah = new SphericCoordinate(-50, -5, -9);
    }

    @Test(expected = IllegalStateException.class)
    public void testToooFaaarAwaaay(){
        CartesianCoordinate inAGalaxy = new CartesianCoordinate(Double.MAX_VALUE,0,0);
        CartesianCoordinate faaarFaaarAwaaay = new CartesianCoordinate(0,0,Double.MAX_VALUE);
        inAGalaxy.getCartesianDistance(faaarFaaarAwaaay);

    }
}
