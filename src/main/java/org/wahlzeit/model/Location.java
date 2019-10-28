package org.wahlzeit.model;

import java.util.Objects;

public class Location {
    protected Coordinate coordinate;

    public Location(Coordinate position){
        coordinate = position;
    }


    /***
     *
     * @param target location
     * @return double: direct distance or Double.NaN if the one of the locations never was set
     */

    public double getDistance(Location target) {
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
    @Override
    public int hashCode(){
        return Objects.hashCode(coordinate);
    }
}
