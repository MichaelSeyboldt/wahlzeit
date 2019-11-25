package org.wahlzeit.model;


import javax.swing.*;

public abstract class AbstractCoordinate implements ICoordinate {

    private void testNotNull(ICoordinate coordinate){
        if(coordinate==null) throw new IllegalArgumentException();
    }

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
        testNotNull(coordinate);
        return doGetCentralAngle(coordinate);
    }

    @Override
    public double getCartesianDistance(ICoordinate coordinate){
        testNotNull(coordinate);
        return doGetCartesianDistance(coordinate);
    }



}
