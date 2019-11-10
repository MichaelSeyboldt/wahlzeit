package org.wahlzeit.model;

public class FliegerPhoto  extends Photo{
    protected Flieger flieger;

    public FliegerPhoto() {
        super();
    }

    public FliegerPhoto(PhotoId myId) {
        super(myId);
    }
    public FliegerPhoto(Flieger flieger){
        super();
        this.flieger =flieger;
    }
    public FliegerPhoto(PhotoId myId,Flieger flieger){
        super(myId);
        this.flieger=flieger;
    }

    public void setFlieger(Flieger flieger) {
        this.flieger = flieger;
    }

    public Flieger getFlieger() {
        return flieger;
    }

}
