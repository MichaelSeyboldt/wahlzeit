package org.wahlzeit.model;


public abstract class AbstractCoordinate implements ICoordinate {

    private void testNotNull(ICoordinate coordinate){
        if(coordinate==null) throw new IllegalArgumentException();
    }

    protected void assertUsable(double x, String msg){
        if(Double.isNaN(x)){
            throw new IllegalStateException("is NaN: " + msg);
        }
        if (Double.isInfinite(x)){
            throw new IllegalStateException("is infinite: " + msg);
        }
    }
    protected void assertUsableArg(double x, String msg){
        if(Double.isNaN(x)){
            throw new IllegalArgumentException("is NaN: " + msg);
        }
        if(Double.isInfinite(x)){
            throw new IllegalArgumentException("is infinite" + msg);
        }
    }

    protected abstract void assertInvariant() ;


    private double doGetCentralAngle(ICoordinate coordinate){

        SphericCoordinate from = this.asSphericCoordinate();
        SphericCoordinate target = coordinate.asSphericCoordinate();

        return Math.acos(Math.sin(from.theta) * Math.sin(target.theta) + (Math.cos(from.theta) * Math.cos(target.theta) * Math.cos(target.phi-from.phi)));

    }

    private double doGetCartesianDistance(ICoordinate coordinate){

        CartesianCoordinate from = this.asCartesianCoordinate();
        CartesianCoordinate to = coordinate.asCartesianCoordinate();

        double dx = to.x - from.x;
        double dy = to.y - from.y;
        double dz = to.z - from.z;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }
    @Override
    public double getCentralAngle(ICoordinate coordinate){
        //precon
        testNotNull(coordinate);

        //invariant
        this.assertInvariant();
        if(coordinate instanceof AbstractCoordinate) {
            ((AbstractCoordinate) coordinate).assertInvariant();
        }
        double res = doGetCentralAngle(coordinate);
        assertUsable(res,"getCentralAngle: result out of bounds: NaN");
        return res;
    }

    @Override
    public double getCartesianDistance(ICoordinate coordinate){
        //precon
        testNotNull(coordinate);
        //invariant
        assertInvariant();
        if(coordinate instanceof AbstractCoordinate) {
            ((AbstractCoordinate) coordinate).assertInvariant();
        }

        double res = doGetCartesianDistance(coordinate);
        //postcon
        assertUsable(res,"getCartesianDistance: result out of bounds: NaN");
        return res;
    }



}
