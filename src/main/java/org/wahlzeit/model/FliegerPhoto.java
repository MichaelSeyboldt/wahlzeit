package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.annotations.DesignPattern;


@DesignPattern(
        name="AbstractFactory",
        participants = {
                "ConcreteProduct"
        }
)
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
