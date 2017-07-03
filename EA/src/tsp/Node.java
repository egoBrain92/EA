/*
* Nodes.java
* Models a Node
*/

package tsp;

public class Node {
    double x;
    double y;
    int nodeID = -1;
    
    // Constructs a randomly placed node
    public Node(int nodeID){
        this.x = (double)(Math.random()*200);
        this.y = (double)(Math.random()*200);
        this.nodeID = nodeID;
    }
    
    // Constructs a node at chosen x, y location
    public Node(double x, double y, int nodeID){
        this.x = x;
        this.y = y;
        this.nodeID = nodeID;
    }
    
    // Gets nodes x coordinate
    public double getX(){
        return this.x;
    }
    
    // Gets nodes y coordinate
    public double getY(){
        return this.y;
    }
    
    public int getNodeID(){
    	return this.nodeID;
    }
    
    // Gets the distance to given node
    public double distanceTo(Node node){
    	
    	double xDistance = Math.abs(getX() - node.getX());
    	double yDistance = Math.abs(getY() - node.getY());
        double distance  = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
        int distance2 = (int) (distance + 0.5);
        distance = distance2;
        
//    	double distance this.g
        return distance;
    }
    
    @Override
    public String toString(){
    	String returnString = "("+ getNodeID()+ "}, ";
//    	String returnString = "(" + getNodeID() + ") " +  getX()+", "+getY();
        return returnString;
    }
}