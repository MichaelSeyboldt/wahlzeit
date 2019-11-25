package org.wahlzeit.model;

public interface ICoordinate {

    public CartesianCoordinate asCartesianCoordinate();

    public SphericCoordinate asSphericCoordinate();

    public double getCartesianDistance(ICoordinate coordinate);

    public double getCentralAngle(ICoordinate coordinate);

    public boolean equals(Object coordinate);

    public int hashCode();






}
