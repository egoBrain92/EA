/*
* City.java
* Models a city
*/

package tsp;

public class City {
    double x;
    double y;
    int cityID = -1;
    
    // Constructs a randomly placed city
    public City(int cityID){
        this.x = (double)(Math.random()*200);
        this.y = (double)(Math.random()*200);
        this.cityID = cityID;
    }
    
    // Constructs a city at chosen x, y location
    public City(double x, double y, int cityID){
        this.x = x;
        this.y = y;
        this.cityID = cityID;
    }
    
    // Gets city's x coordinate
    public double getX(){
        return this.x;
    }
    
    // Gets city's y coordinate
    public double getY(){
        return this.y;
    }
    
    public int getCityID(){
    	return this.cityID;
    }
    
    // Gets the distance to given city
    public double distanceTo(City city){
    	
    	double xDistance = Math.abs(getX() - city.getX());
    	double yDistance = Math.abs(getY() - city.getY());
        double distance  = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
        int distance2 = (int) (distance + 0.5);
        distance = distance2;
        
//    	double distance this.g
        return distance;
    }
    
    @Override
    public String toString(){
    	String returnString = "(" + getCityID() + ") " +  getX()+", "+getY();
        return returnString;
    }
}