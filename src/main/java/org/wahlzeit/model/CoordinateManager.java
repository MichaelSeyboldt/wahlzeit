package org.wahlzeit.model;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

import java.util.Map;



public class CoordinateManager {

    //singelton
    protected static CoordinateManager instance;

    protected Map<String ,CartesianCoordinate> cartesianCoordinates;
    protected Map<String,SphericCoordinate> sphericCoordinates;


    protected CoordinateManager(){
        cartesianCoordinates = new HashMap<>();
        sphericCoordinates = new HashMap<>();
    }

    synchronized public static CoordinateManager getInstance(){
            if(instance==null){
                instance= new CoordinateManager();
            }

        return instance;
    }

    private String getName(double a, double b , double c){
        return Double.toHexString(a) + Double.toHexString(b) + Double.toHexString(c);
    }

    synchronized private void  createCoordinate(double x, double y, double z) throws IllegalArgumentException{
        String id = getName(x,y,z);
        if(cartesianCoordinates.containsKey(id)) return; //allready there

        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x,y,z);
        double radius = Math.sqrt((x*x)+(y*y)+(z*z));
        double phi    = Math.atan2(y,x);
        double theta  = (radius!=0) ? Math.acos(z/radius): 0;
        SphericCoordinate sphericCoordinate = new SphericCoordinate(radius,phi,theta);

        cartesianCoordinates.put(id,cartesianCoordinate);
        sphericCoordinates.put(id,sphericCoordinate);


    }

    public CartesianCoordinate getCartesianCoordinate(double x,double y, double z) throws IllegalArgumentException{
        //prep
        String id = getName(x,y,z);
        //check
        if(!cartesianCoordinates.containsKey(id)  ){//not there yet
            createCoordinate(x,y,z);
        }
        return cartesianCoordinates.get(id);


    }

    public SphericCoordinate getSphericCoordinate(double radius, double phi, double theta) throws IllegalArgumentException{
        //convert to basic coords
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);

        //check
        String id = getName(x,y,z);
        if (!sphericCoordinates.containsKey(id)){
            createCoordinate(x,y,z);
        }
        return sphericCoordinates.get(id);
    }

    public SphericCoordinate getSphericCoordinate(CartesianCoordinate coordinate) throws  IllegalStateException {
        if(!isValid(coordinate)){
            //error not included
            throw  new IllegalStateException("this coordinate is invalid because it was never registered");
        }
        return sphericCoordinates.get(getName(coordinate));
    }

    public CartesianCoordinate getCartesianCoordinate(SphericCoordinate coordinate) throws IllegalStateException{
        if(!isValid(coordinate)){
            throw new IllegalStateException("this coordinate is invalid because it was never registered");
        }
        return cartesianCoordinates.get(getName(coordinate));
    }

    public boolean isValid(SphericCoordinate c){
        return sphericCoordinates.containsKey(getName(c)) ;
    }
    public boolean isValid(CartesianCoordinate c){
        return cartesianCoordinates.containsKey(getName(c));
    }


    public String getName(SphericCoordinate coordinate){
        //convert to basic coords
        double x = coordinate.radius * Math.sin(coordinate.theta) * Math.cos(coordinate.phi);
        double y = coordinate.radius * Math.sin(coordinate.theta) * Math.sin(coordinate.phi);
        double z = coordinate.radius * Math.cos(coordinate.theta);
        return getName(x,y,z);

    }
    public String getName(CartesianCoordinate coordinate){
        return getName(coordinate.x,coordinate.y,coordinate.z);
    }

    public String getName(AbstractCoordinate coordinate){
        if(coordinate instanceof CartesianCoordinate) return getName((CartesianCoordinate )coordinate);
        if(coordinate instanceof SphericCoordinate) return getName((SphericCoordinate) coordinate);
        throw new NotImplementedException( );//"this type of Coordinate is not yet implemented in CoordinateManager"

    }
    public Boolean areEqual(AbstractCoordinate a, AbstractCoordinate b){

        return getName(a).equals(getName(b));

    }



}
