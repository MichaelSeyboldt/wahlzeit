package org.wahlzeit.model;

import org.wahlzeit.services.*;
import java.util.HashMap;
import java.util.*;



public class FliegerType  {


    protected String id;

    protected String manufacturer;
    protected String name;
    protected float wingspan;
    protected boolean motorized;
    protected int seats;
    protected FliegerType superType;
    protected HashMap<String, FliegerType> subTypes;
    protected boolean isConcret;

    protected void assertNotNullArg(Object object, String msg) {
        if (object == null) {
            throw new IllegalArgumentException("is null: " + msg);
        }
    }

    protected void assertUsableArg(float arg, String msg) {
        if (arg <= 0) {
            throw new IllegalArgumentException("arg must be positive: " + msg);
        }
    }

    protected void assertUsableArg(int arg, String msg) {
        if (arg <= 0) {
            throw new IllegalArgumentException("arg must be positive: " + msg);
        }
    }

    public FliegerType() {
        id = "BASETYPE";
        name = "BASETYPE";
        isConcret = false;
        subTypes = new HashMap<String, FliegerType>();
    }

    public FliegerType(FliegerType superType, String id, String name) throws IllegalArgumentException {
        assertNotNullArg(superType, "superType");
        assertNotNullArg(id, "id");
        assertNotNullArg(name, "name");

        this.superType = superType;
        this.id = id;
        this.name = name;
        subTypes = new HashMap<String, FliegerType>();
        isConcret = false;
    }

    public FliegerType(String id, String name) throws IllegalArgumentException {
        assertNotNullArg(id, "id");
        assertNotNullArg(name, "name");

        this.id = id;
        this.name = name;
        subTypes = new HashMap<String, FliegerType>();
        isConcret = false;
    }

    public FliegerType(FliegerType superType, String id, String name, String manufacturer) throws IllegalArgumentException {
        assertNotNullArg(superType, "superType");
        assertNotNullArg(id, "id");
        assertNotNullArg(name, "name");
        assertNotNullArg(manufacturer, "manufacturer");

        this.superType = superType;
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        isConcret = false;
        subTypes = new HashMap<String, FliegerType>();
    }

    public FliegerType(FliegerType superType, String id, String manufacturer, String name, float wingspan, boolean motorized, int seats) throws IllegalArgumentException {
        assertNotNullArg(superType, "superType");
        assertNotNullArg(id, "id");
        assertNotNullArg(manufacturer, "manufacturer");
        assertNotNullArg(name, "name");
        assertUsableArg(wingspan, "Wingspan");
        assertUsableArg(seats, "seats");

        this.manufacturer = manufacturer;
        this.id = id;
        this.superType = superType;
        this.name = name;
        this.wingspan = wingspan;
        this.motorized = motorized;
        this.seats = seats;
        isConcret = true;
        subTypes = new HashMap<String, FliegerType>();
    }

    public String getId() {
        return id;
    }

    public boolean isConcret() {
        return isConcret;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public float getWingspan() {
        return wingspan;
    }

    public boolean isMotorized() {
        return motorized;
    }

    public int getSeats() {
        return seats;
    }

    public FliegerType getSuperType() {
        return superType;
    }

    public Map<String, FliegerType> getSubTypes() {
        return subTypes;
    }

    public void addSubType(FliegerType newSubType) {
        subTypes.put(newSubType.getId(), newSubType);
    }


}
