package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LocationTest {
    private Location zero, tenx, teny, tenz;

    @Before
    public void setup(){
        zero = new Location(0,0,0);
        tenx = new Location(10,0,0);
        teny = new Location(0,10,0);
        tenz = new Location(0,0,10);

    }


    @Test
    public void distances(){
        assertEquals(zero.getDistance(zero),0., 1e-15);
        assertEquals(zero.getDistance(tenx),10.,1e-15);
        assertEquals(zero.getDistance(teny),10.,1e-15);
        assertEquals(zero.getDistance(tenz),10.,1e-15);

    }

    @Test
    public void equality(){
        Location tx = new Location(10, 0, 0);
        Location undefined1 = new Location();
        Location undefined2 = new Location();
        assertTrue(zero.equals(zero));
        assertFalse(zero.equals(tenx));
        assertTrue(tenx.equals(tx));
        assertFalse(tenx.equals(teny));
        assertTrue(undefined1.equals(undefined1));
        assertFalse(undefined1.equals(zero));
        assertFalse(undefined1.equals(undefined2));
    }
}
