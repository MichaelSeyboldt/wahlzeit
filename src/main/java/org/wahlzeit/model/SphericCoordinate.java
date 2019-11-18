package org.wahlzeit.model;

import javax.swing.text.MutableAttributeSet;
import java.util.Objects;

public class SphericCoordinate implements ICoordinate {

    private double radius, phi, theta;

    SphericCoordinate(double radius, double phi, double theta){
        this.radius=radius;
        this.phi=phi;
        this.theta=theta;

    }

    protected double getDistance(SphericCoordinate coordinate){
        double ctrlAngle = doGetAngle(coordinate);
        double umpfangProGrad = ((radius+coordinate.radius) * Math.PI)/360;
        return ctrlAngle * umpfangProGrad;
    }

    @Override
    public double getGreatCircleDistance(ICoordinate coordinate) {
        return 0;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return convertToCartesian();
    }
    protected CartesianCoordinate convertToCartesian(){
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);

        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCartesianDistance(ICoordinate coordinate) {
        return convertToCartesian().getCartesianDistance(coordinate);
    }

    protected double doGetAngle(SphericCoordinate coordinate){
        return Math.acos(Math.sin(theta) * Math.sin(coordinate.theta) + (Math.cos(theta) * Math.cos(coordinate.theta) * Math.cos(coordinate.phi-phi)));
    }

    @Override
    public double getCentralAngle(ICoordinate coordinate) {
        return doGetAngle(coordinate.asSphericCoordinate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        SphericCoordinate that;
        if(getClass() != o.getClass()){
            if(o.getClass()==ICoordinate.class ){
                ICoordinate a = (ICoordinate) o;
                that =a.asSphericCoordinate();
            } else {
                return false;
            }
        } else {
            that = (SphericCoordinate) o;
        }
        return isEqual(that);
    }
    private boolean isEqual(SphericCoordinate coordinate){
        return Double.compare(coordinate.radius, radius) == 0 &&
                Double.compare(coordinate.phi, phi) == 0 &&
                Double.compare(coordinate.theta, theta) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, phi, theta);
    }
}
