package org.wahlzeit.model;


import java.util.Objects;

public class CartesianCoordinate implements ICoordinate {

    private double x ,y ,z;



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
        double phi    = Math.atan(y/x);
        double theta  = Math.acos(z/radius);
        SphericCoordinate coordinate = new SphericCoordinate(radius,phi,theta);
        return coordinate;
    }

    @Override
    public double getCartesianDistance(ICoordinate coordinate) {
        return getDistance(coordinate.asCartesianCoordinate());
    }

    @Override
    public double getCentralAngle(ICoordinate coordinate) {
        return convertToSpheric().getCentralAngle(coordinate);
    }

    @Override
    public double getGreatCircleDistance(ICoordinate coordinate) {
        return convertToSpheric().getGreatCircleDistance(coordinate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        CartesianCoordinate that ;
        if( getClass() != o.getClass()){
            if(o.getClass()==ICoordinate.class){
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
