package org.wahlzeit.model;

public class Location {
    private Coordinate coordinate;

    public Location(){
        coordinate=null;
    }
    public Location(double x, double y, double z){
        coordinate = new Coordinate(x,y,z);
    }
    public void setPositon(double x,double y, double z){
        coordinate =  new Coordinate(x,y,z);
    }

    /***
     *
     * @param target location
     * @return double: direct distance or Double.NaN if the one of the locations never was set
     */

    public double getDistance(Location target) {
        if(coordinate==null) throw new NullPointerException("This Location has no position");
        if(target==null || target.coordinate==null) throw new IllegalArgumentException("Target location null or target positon not set");
        return coordinate.getDistance(target.coordinate);
    }

    private boolean isEqual(Location target){
        return coordinate != null && target.coordinate != null && coordinate.isEqual(target.coordinate);
    }

    /***
     *
     * @param target
     * @return bool: true if the objects match
     * false if the objects differ or one or both of them have never been set spesificly
     */
    @Override
    public boolean equals(Object target){
        return this==target || target.getClass() == this.getClass() && isEqual((Location) target);
    }
}
