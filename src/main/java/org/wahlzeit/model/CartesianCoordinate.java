package org.wahlzeit.model;


import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate {

    protected double x ,y ,z;



    CartesianCoordinate(double x, double y  , double z ){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    protected double getDistance(CartesianCoordinate target){
        double dx = x - target.x;
        double dy = y - target.y;
        double dz = z - target.z;

        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }
    protected boolean isEqual(CartesianCoordinate target){
        return  Double.compare(target.x, x) == 0 &&
                Double.compare(target.y, y) == 0 &&
                Double.compare(target.z, z) == 0;
    }


    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return convertToSpheric();
    }
    protected SphericCoordinate convertToSpheric(){
        double radius = Math.sqrt((x*x)+(y*y)+(z*z));
        double phi    = Math.atan2(y,x);
        double theta  = (radius!=0) ? Math.acos(z/radius): 0;
        SphericCoordinate coordinate = new SphericCoordinate(radius,phi,theta);
        return coordinate;
    }



    public boolean equals(Object o) {
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

        return isEqual(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
