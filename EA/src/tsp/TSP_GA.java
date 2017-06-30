/*
* TSP_GA.java
* Create a tour and evolve a solution
*/

package tsp;

import java.io.FileNotFoundException;

public class TSP_GA {

    public static void main(String[] args) throws FileNotFoundException {

        // Create and add our cities
    	GraphHandler graphHandler = new GraphHandler();
    	
    	String file = "inputGraph.txt";
    	
    	int generations = 6000;
    	int populationSize = 5000;
//    	double mutationRate = 0.0001;

    	
    	Tour bestTour = new Tour();
    	
    	graphHandler.reachGraphFromFile(file);
    	
        
        Population pop = new Population(populationSize, true);
        bestTour = pop.getFittest();
//        System.out.println("Initial distance: " + pop.getFittest().getDistance());
//        System.out.println(bestTour);

        // Evolve population for "generations" amount of times
        pop = GA.evolvePopulation(pop);
        
        for (int i = 0; i < generations; i++) {
        	if(i % 1 == 0 && i < generations/1){
        		System.out.println("gen:\t" + i + "\tavgFitnest:\t" + pop.getAVGLenght() +"\t" + "best:\t" + bestTour.getDistance());
        	}
            pop = GA.evolvePopulation(pop);
            if(pop.getFittest().getDistance() < bestTour.getDistance()){
            	bestTour = pop.getFittest();
            }    
        }
       
        // Print results

        System.out.println("Solution is:\t" + bestTour.getDistance() + "\t avg:\t " + pop.getAVGLenght());
//        System.out.println(bestTour);
    }
}