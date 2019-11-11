package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FliegerTest {

    Flieger a , a2, b;

    @Test
    public void testEquals(){
        assertEquals(a,a2);
        assertEquals(a2,a);
        assertEquals(a,a);
        assertNotEquals(a,b);


    }

    @Test
    public void testSameKenzeichen(){

        assertTrue(a.hasSameKenzeichen(a2));
        assertFalse(a.hasSameKenzeichen(b));
    }

    @Test
    public void testSameManufacturer(){
        assertTrue(a.hasSameManufacturer(b));
        assertTrue(a.hasSameManufacturer(a2));
    }


    @Before
    public void initFlieger(){
        a  = new  Flieger("D-3620","SZD","50" );
        a2 = new Flieger("D-3620","SZD","50");
        b  = new Flieger("D-1718","SZD","55");
    }
}
