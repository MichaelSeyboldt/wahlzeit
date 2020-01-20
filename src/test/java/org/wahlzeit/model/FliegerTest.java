package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class FliegerTest {

    Flieger a , a2, b;
    FliegerType segelflieger, pzl, shempp, pirat;

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
        FliegerManager manager = FliegerManager.getInstance();
        manager.initDefault();
        Iterator<FliegerType> types = manager.getAllTypes();

        while (types.hasNext()){
            FliegerType next = types.next();

            if(next.getId().equals("Segelflieger")){
                segelflieger = next;
            } else if(next.getId().equals("PZL") ){
                pzl = next;
            } else if(next.getId().equals("Schempp-Hirt")){
                shempp = next;
            } else if (next.getId().equals("SZD-30")){
                pirat = next;
            }
        }

        a  = new  Flieger("D-3620",pirat );
        a2 = new Flieger("D-3620",pirat);
        b  = new Flieger("D-1718",pirat);
    }
}
