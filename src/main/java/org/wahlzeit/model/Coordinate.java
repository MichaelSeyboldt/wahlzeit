package org.wahlzeit.model;

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
        return  (x==target.x && y==target.y && z==target.z);
    }


    @Override
    public boolean equals(Object target){
        //reroute to is Equal happens only if its a coordinate because of lazy evaluation
        return target.getClass()==this.getClass() && isEqual((Coordinate)target);
    }

}
