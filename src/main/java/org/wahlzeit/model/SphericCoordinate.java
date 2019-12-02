package org.wahlzeit.model;

import javax.swing.text.MutableAttributeSet;
import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate {

    protected final double EPSILON = 1e-15;

    protected double radius, phi, theta;

    protected boolean checkLesZero(double  a){
        return (a < (0-EPSILON));
    }
    protected boolean checkGt180(double a){
        return (a > (180. + EPSILON));
    }
    protected boolean checkGt360(double a){
        return (a > (360. + EPSILON));
    }

    @Override
    protected void assertInvariant() {
    if(checkLesZero(radius)) throw new IllegalStateException("radius has to be positive");
    if(checkLesZero(phi)) throw new IllegalStateException("phi has to be positive");
    if(checkLesZero(theta)) throw new IllegalStateException("theta has to be positive");
    if(checkGt180(phi)) throw  new IllegalStateException("phi has to be less or equal 180");
    if(checkGt360(theta)) throw new IllegalStateException("theta has to be less or equal 360");
    }

    SphericCoordinate(double radius, double phi, double theta){
        if(checkLesZero(radius)) throw new IllegalArgumentException("radius has to be positive");
        if(checkLesZero(phi)) throw new IllegalArgumentException("phi has to be positive");
        if(checkLesZero(theta)) throw new IllegalArgumentException("theta has to be positive");
        if(checkGt180(phi)) throw  new IllegalArgumentException("phi has to be less or equal 180");
        if(checkGt360(theta)) throw new IllegalArgumentException("theta has to be less or equal 360");

        this.radius=radius;
        this.phi=phi;
        this.theta=theta;

    }


    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        //precon
        assertInvariant();
        CartesianCoordinate res =  convertToCartesian();
        ///postcon
        res.assertInvariant();
        return res;
    }
    protected CartesianCoordinate convertToCartesian(){
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertInvariant();
        return this;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        SphericCoordinate that;
        if(getClass() != o.getClass()){
            if(o instanceof ICoordinate){
                ICoordinate a = (ICoordinate) o;
                that =a.asSphericCoordinate();
            } else {
                return false;
            }
        } else {
            that = (SphericCoordinate) o;
        }
        this.assertInvariant();
        that.assertInvariant();
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
