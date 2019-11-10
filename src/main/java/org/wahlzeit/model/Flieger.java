package org.wahlzeit.model;

import java.util.Objects;

public class Flieger {

    protected String kenzeichen;
    protected String wetbewerbskenzeichen;
    protected String manufacturer;

    public void setKenzeichen(String kenzeichen) {
        this.kenzeichen = kenzeichen;
    }

    public String getKenzeichen() {
        return kenzeichen;
    }

    public void setWetbewerbskeinzeichen(String wetbewerbskeinzeichen) {
        this.wetbewerbskenzeichen = wetbewerbskeinzeichen;
    }

    public String getWetbewerbskeinzeichen() {
        return wetbewerbskenzeichen;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public boolean hasSameManufacturer(Flieger flieger){
        return (manufacturer.toUpperCase().equals(flieger.manufacturer.toUpperCase()));

    }
    public boolean hasSameKenzeichen(Flieger flieger){
        return (kenzeichen.toUpperCase().equals(flieger.kenzeichen.toUpperCase()));
    }
    public  boolean hasSameWetbewerbskenzeichen(Flieger flieger){
        return (wetbewerbskenzeichen.toUpperCase().equals(flieger.wetbewerbskenzeichen.toUpperCase()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flieger flieger = (Flieger) o;
        return Objects.equals(kenzeichen, flieger.kenzeichen) &&
                Objects.equals(wetbewerbskenzeichen, flieger.wetbewerbskenzeichen) &&
                Objects.equals(manufacturer, flieger.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kenzeichen, wetbewerbskenzeichen, manufacturer);
    }
}
