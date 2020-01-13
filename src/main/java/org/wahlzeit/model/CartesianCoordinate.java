package org.wahlzeit.model;


import org.wahlzeit.annotations.DesignPattern;

import java.util.Objects;

@DesignPattern(
        name = "Factory Pattern",
        participants = {
                "ConcreteProduct"
        }
)
public class CartesianCoordinate extends AbstractCoordinate {

    protected double x ,y ,z;



    CartesianCoordinate(double x, double y  , double z )throws IllegalArgumentException{
        //precon
        assertUsableArg(x, "x");
        assertUsableArg(y, "y");
        assertUsableArg(z, "z");
        this.x=x;
        this.y=y;
        this.z=z;
        //invariant/postcon would be duplicateed

    }
    protected boolean isEqual(CartesianCoordinate target){
        return  Double.compare(target.x, x) == 0 &&
                Double.compare(target.y, y) == 0 &&
                Double.compare(target.z, z) == 0;
    }

    @Override
    protected void assertInvariant() {
        assertUsable(x, "x");
        assertUsable(y, "y");
        assertUsable(z, "z");
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException{
        assertInvariant();
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() throws  IllegalStateException{
        //precon / invariant
        assertInvariant();
        SphericCoordinate res= convertToSpheric();

        //postcon
        res.assertInvariant();
        return res;
    }


    protected SphericCoordinate convertToSpheric() throws IllegalStateException{
        return CoordinateManager.getInstance().getSphericCoordinate(this);
    }



    public boolean equals(Object o) {
        //precon
        if (this == o) return true;
        if (o == null ) return false;
        if( getClass() != o.getClass()){
            if(o instanceof AbstractCoordinate){
                AbstractCoordinate a = (AbstractCoordinate) o;
                return CoordinateManager.getInstance().areEqual(this,a);
            }else {
                return false;
            }

        } else {

        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(CoordinateManager.getInstance().getName(this));
    }
}
