package org.wahlzeit.model;


import java.util.Objects;

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
        double radius = Math.sqrt((x*x)+(y*y)+(z*z));
        double phi    = Math.atan2(y,x);
        double theta  = (radius!=0) ? Math.acos(z/radius): 0;
        SphericCoordinate coordinate = new SphericCoordinate(radius,phi,theta);
        return coordinate;
    }



    public boolean equals(Object o) {
        //precon
        if (this == o) return true;
        if (o == null ) return false;
        CartesianCoordinate that ;
        if( getClass() != o.getClass()){
            if(o instanceof ICoordinate){
                ICoordinate a = (ICoordinate) o;
                that = a.asCartesianCoordinate();
            }else {
                return false;
            }

        } else {
            that = (CartesianCoordinate) o;
        }
        //invariant
        this.assertInvariant();
        that.assertInvariant();

        return isEqual(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
