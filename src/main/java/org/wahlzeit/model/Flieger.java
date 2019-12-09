package org.wahlzeit.model;

import java.util.Objects;
import java.util.logging.Logger;

public class Flieger {
    private static final Logger log = Logger.getLogger(Flieger.class.getName());

    protected String kenzeichen;
    protected String wetbewerbskenzeichen;
    protected String manufacturer;
    protected String fType;
    protected float  wingspan;

    public Flieger(){

    }
    public Flieger(String kenzeichen, String manufacturer ,String fType){
        this.kenzeichen = kenzeichen;
        this.manufacturer = manufacturer;
        this.fType = fType;
    }

    public void setKenzeichen(String kenzeichen) {
        this.kenzeichen = kenzeichen;
    }

    public String getKenzeichen() {
        return kenzeichen;
    }



    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public boolean hasSameManufacturer(Flieger flieger){
        if(flieger==null || flieger.manufacturer==null|| this.manufacturer==null ){
            log.info("tryed to compare null values in \'hasSameManufacturer\'");
            return false;
        }
        return (manufacturer.toUpperCase().equals(flieger.manufacturer.toUpperCase()));

    }

    public String getWetbewerbskenzeichen() {
        return wetbewerbskenzeichen;
    }

    public void setWetbewerbskenzeichen(String wetbewerbskenzeichen) {
        this.wetbewerbskenzeichen = wetbewerbskenzeichen;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public float getWingspan() {
        return wingspan;
    }

    public void setWingspan(float wingspan) {
        this.wingspan = wingspan;
    }

    public boolean hasSameKenzeichen(Flieger flieger){
        if(flieger==null || flieger.kenzeichen ==null||kenzeichen ==null){
            //fail
            log.info("tryed to compare null value in hasSameKenzeichen");
            //correct
            return false;
        }
        return (kenzeichen.toUpperCase().equals(flieger.kenzeichen.toUpperCase()));
    }
    public  boolean hasSameWetbewerbskenzeichen(Flieger flieger){
        if(flieger==null||flieger.wetbewerbskenzeichen==null||wetbewerbskenzeichen==null){
            //fail
            log.info("tryed to compare null value in hasSameWettbewerbskennzeichen");
            return false;
        }
        return (wetbewerbskenzeichen.toUpperCase().equals(flieger.wetbewerbskenzeichen.toUpperCase()));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flieger flieger = (Flieger) o;
        return Objects.equals(kenzeichen, flieger.kenzeichen) &&
                Objects.equals(wetbewerbskenzeichen, flieger.wetbewerbskenzeichen) &&
                Objects.equals(manufacturer, flieger.manufacturer) &&
                Objects.equals(fType,flieger.fType) &&
                Float.compare(wingspan,flieger.wingspan) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kenzeichen, wetbewerbskenzeichen, manufacturer, fType);
    }
}
