package org.wahlzeit.model;

import java.util.Objects;
import java.util.logging.Logger;

public class Flieger {
    private static final Logger log = Logger.getLogger(Flieger.class.getName());

    protected String kenzeichen;
    protected String wetbewerbskenzeichen;
    protected FliegerType type;


    public Flieger(FliegerType fliegerType){
        this.type = fliegerType;

    }
    public Flieger(String kenzeichen, FliegerType fliegerType ){
        this.kenzeichen = kenzeichen;
        this.type = fliegerType;
    }

    public void setKenzeichen(String kenzeichen) {
        this.kenzeichen = kenzeichen;
    }

    public String getKenzeichen() {
        return kenzeichen;
    }





    public String getManufacturer() {
        return type.getManufacturer();

    }

    public boolean hasSameManufacturer(Flieger flieger){
        if(flieger==null || flieger.getManufacturer()==null|| this.getManufacturer()==null ){
            log.info("tryed to compare null values in \'hasSameManufacturer\'");
            return false;
        }
        return (getManufacturer().toUpperCase().equals(flieger.getManufacturer().toUpperCase()));

    }

    public String getWetbewerbskenzeichen() {
        return wetbewerbskenzeichen;
    }

    public void setWetbewerbskenzeichen(String wetbewerbskenzeichen) {
        this.wetbewerbskenzeichen = wetbewerbskenzeichen;
    }

    public FliegerType getType() {
        return type;
    }


    public float getWingspan() {
        return type.getWingspan();
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
                Objects.equals(getType(),flieger.getType()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kenzeichen, wetbewerbskenzeichen, type);
    }
}
