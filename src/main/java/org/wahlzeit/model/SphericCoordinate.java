package org.wahlzeit.model;

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
        assertUsable(radius,"radius");
        assertUsable(phi,"phi");
        assertUsable(theta, "theta");
        if(checkLesZero(radius)) throw new IllegalStateException("radius has to be positive");
        if(checkLesZero(phi)) throw new IllegalStateException("phi has to be positive");
        if(checkLesZero(theta)) throw new IllegalStateException("theta has to be positive");
        if(checkGt180(phi)) throw  new IllegalStateException("phi has to be less or equal 180");
        if(checkGt360(theta)) throw new IllegalStateException("theta has to be less or equal 360");
    }

    SphericCoordinate(double radius, double phi, double theta) throws IllegalArgumentException{
        assertUsableArg(radius,"radius");
        assertUsableArg(phi,"phi");
        assertUsableArg(theta, "theta");
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
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException {
        //precon
        assertInvariant();
        CartesianCoordinate res =  convertToCartesian();
        ///postcon
        res.assertInvariant();
        return res;
    }
    protected CartesianCoordinate convertToCartesian(){
        return CoordinateManager.getInstance().getCartesianCoordinate(this);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() throws IllegalStateException{
        assertInvariant();
        return this;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        if(getClass() != o.getClass()){
            if(o instanceof AbstractCoordinate){
                AbstractCoordinate a = (AbstractCoordinate) o;
                CoordinateManager.getInstance().areEqual(this,a);
            } else {
                return false;
            }
        } else {

        }
        return false;
    }
    private boolean isEqual(SphericCoordinate coordinate){
        return Double.compare(coordinate.radius, radius) == 0 &&
                Double.compare(coordinate.phi, phi) == 0 &&
                Double.compare(coordinate.theta, theta) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(CoordinateManager.getInstance().getName(this));
    }
}
