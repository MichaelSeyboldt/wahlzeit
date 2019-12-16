package org.wahlzeit.model;

import com.google.appengine.repackaged.com.google.gson.internal.$Gson$Preconditions;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LocationTest {
    private CartesianCoordinate zero, tenx, teny, tenz;
    private SphericCoordinate sph0, sph1, sph2;

    private CoordinateManager manager;

    @Before
    public void setup() {
        manager = CoordinateManager.getInstance();
        zero = manager.getCartesianCoordinate(0, 0, 0);
        tenx =manager.getCartesianCoordinate(10, 0, 0);
        teny =manager.getCartesianCoordinate(0, 10, 0);
        tenz =manager.getCartesianCoordinate(0, 0, 10);
        sph0 =manager.getSphericCoordinate(1, 0, 0);
        sph1 =manager.getSphericCoordinate(1, Math.PI, 0);
        sph2 =manager.getSphericCoordinate(1, 0, 0);

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
        CartesianCoordinate tx = manager.getCartesianCoordinate(10, 0, 0);
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
