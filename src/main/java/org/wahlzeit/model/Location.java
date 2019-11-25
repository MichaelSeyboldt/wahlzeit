package org.wahlzeit.model;


import java.util.Objects;

public class Location {
    protected AbstractCoordinate coordinate;

    public Location(AbstractCoordinate position){
        coordinate = position;
    }

    public ICoordinate getCoordinate(){
        return coordinate;
    }


    private boolean isEqual(Location target){
        return coordinate != null && target.coordinate != null && coordinate.equals(target.coordinate);
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
    @Override
    public int hashCode(){
        return Objects.hashCode(coordinate);
    }
}
