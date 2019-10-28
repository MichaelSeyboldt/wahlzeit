package org.wahlzeit.model;

import java.util.Objects;

public class Coordinate {

    private double x ,y ,z;



    Coordinate(double x, double y  , double z ){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    protected double getDistance(Coordinate target){
        double dx = x - target.x;
        double dy = y - target.y;
        double dz = z - target.z;

        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }
    protected boolean isEqual(Coordinate target){
        return  Double.compare(target.x, x) == 0 &&
                Double.compare(target.y, y) == 0 &&
                Double.compare(target.z, z) == 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return isEqual((Coordinate) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
