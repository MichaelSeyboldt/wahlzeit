package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LocationTest {
    private Coordinate zero, tenx, teny, tenz;

    @Before
    public void setup(){
        zero = new Coordinate(0 ,0,0);
        tenx = new Coordinate( 10,0,0);
        teny = new Coordinate( 0,10,0);
        tenz = new Coordinate( 0 ,0,10);


    }


    @Test
    public void testdistances(){
        assertEquals(zero.getDistance(zero),0., 1e-15);
        assertEquals(zero.getDistance(tenx),10.,1e-15);
        assertEquals(zero.getDistance(teny),10.,1e-15);
        assertEquals(zero.getDistance(tenz),10.,1e-15);

    }

    @Test
    public void testequality(){
        Coordinate tx = new Coordinate(10, 0, 0);
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
}
