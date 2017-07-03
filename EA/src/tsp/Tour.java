/*
* Tour.java
* Stores a candidate tour
*/

package tsp;

import java.util.ArrayList;
import java.util.Collections;

public class Tour{
	GraphHandler graphHandler = new GraphHandler();
    // Holds our tour of nodes
    private ArrayList tour = new ArrayList<Node>();
    // Cache
    private double fitness = 0;
    private int distance = 0;
    
    // Constructs a blank tour
    public Tour(){
        for (int i = 0; i < TourManager.numberOfNodes(); i++) {
            tour.add(null);
        }
    }
    
    public Tour(ArrayList tour){
        this.tour = tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination nodes and add them to our tour
        for (int nodesIndex = 0; nodesIndex < TourManager.numberOfNodes(); nodesIndex++) {
          setNode(nodesIndex, TourManager.getNode(nodesIndex));
        }
        // Randomly reorder the tour
        Collections.shuffle(tour);
    }

    // Gets a node from the tour
    public Node getNode(int tourPosition) {
        return (Node)tour.get(tourPosition);
    }

    // Sets a node in a certain position within a tour
    public void setNode(int tourPosition, Node node) {
        tour.set(tourPosition, node);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }
    
    // Gets the tours fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }
    
    // Gets the total distance of the tour
    public int getDistanceBackup(){
        if (distance == 0) {
            int tourDistance = 0;
            // Loop through our tour's nodes
            for (int nodeIndex=0; nodeIndex < tourSize(); nodeIndex++) {
                // Get node we're travelling from
                Node fromNode = getNode(nodeIndex);
                // Node we're travelling to
                Node destinationNode;
                // Check we're not on our tour's last node, if we are set our
                // tour's final destination node to our starting node
                if(nodeIndex+1 < tourSize()){
                    destinationNode = getNode(nodeIndex+1);
                }
                else{
                    destinationNode = getNode(0);
                }
                // Get the distance between the two nodes
                tourDistance += fromNode.distanceTo(destinationNode);
            }
            distance = tourDistance;
        }
        return distance;
    }
    
    // Gets the total distance of the tour
	public int getDistance() {
		double disFromMatrix = 0; // graphHandler.adj[0][0];
		double tempInt = 0;
		if (distance == 0) {
			int tourDistance = 0;
			// Loop through our tour's nodes
			for (int nodeIndex = 0; nodeIndex < tourSize(); nodeIndex++) {
				// Get node we're travelling from
				Node fromNode = getNode(nodeIndex);
				// Node we're travelling to
				Node destinationNode;
				// Check we're not on our tour's last node, if we are set our
				// tour's final destination node to our starting node
				if (nodeIndex + 1 < tourSize()) {
					destinationNode = getNode(nodeIndex + 1);
				} else {
					destinationNode = getNode(0);
				}
				// Get the distance between the two nodes
				tempInt = (int) graphHandler.adj[fromNode.nodeID][destinationNode.nodeID];
				tourDistance += tempInt;

			}
			distance = tourDistance;
		}
		return distance;
	}

    // Get number of nodes on our tour
    public int tourSize() {
        return tour.size();
    }
    
    // Check if the tour contains a node
    public boolean containsNode(Node node){
        return tour.contains(node);
    }
    
    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getNode(i);
        }
        if (geneString.endsWith(", ")) {
        	geneString = geneString.substring(0, geneString.length() - 2);
        	}
        return geneString;
    }
}