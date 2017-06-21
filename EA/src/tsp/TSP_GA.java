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
    	
    	int generations = 1000;
    	int populationSize = 3000;
//    	double mutationRate = 0.0001;
    	int nodeCount = 30;
    	
    	graphHandler.reachGraphFromFile(file);
    	
    	int j = 0;
        for (int i = 0; i < nodeCount; i++) {
        	
//        	City dummy = new City(0, j, i);
        	City dummy = new City(i);
        	j = j + 10;
            //TourManager.addCity(dummy);
        }
        /*
        City city = new City(60, 200);
        TourManager.addCity(city);
        City city2 = new City(180, 200);
        TourManager.addCity(city2);
        City city3 = new City(80, 180);
        TourManager.addCity(city3);
        City city4 = new City(140, 180);
        TourManager.addCity(city4);
        City city5 = new City(20, 160);
        TourManager.addCity(city5);
        City city6 = new City(100, 160);
        TourManager.addCity(city6);
        City city7 = new City(200, 160);
        TourManager.addCity(city7);
        City city8 = new City(140, 140);
        TourManager.addCity(city8);
        City city9 = new City(40, 120);
        TourManager.addCity(city9);
        City city10 = new City(100, 120);
        TourManager.addCity(city10);
        City city11 = new City(180, 100);
        TourManager.addCity(city11);
        City city12 = new City(60, 80);
        TourManager.addCity(city12);
        City city13 = new City(120, 80);
        TourManager.addCity(city13);
        City city14 = new City(180, 60);
        TourManager.addCity(city14);
        City city15 = new City(20, 40);
        TourManager.addCity(city15);
        City city16 = new City(100, 40);
        TourManager.addCity(city16);
        City city17 = new City(200, 40);
        TourManager.addCity(city17);
        City city18 = new City(20, 20);
        TourManager.addCity(city18);
        City city19 = new City(60, 20);
        TourManager.addCity(city19);
        City city20 = new City(160, 20);
        TourManager.addCity(city20);
        */
        // Initialize population
        
        Population pop = new Population(populationSize, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());
        System.out.println(pop.getFittest());

        // Evolve population for "generations" amount of times
        pop = GA.evolvePopulation(pop);
        
        for (int i = 0; i < generations; i++) {
        	if(i % 5 == 0 && i < generations/10){
        		System.out.println("gen:\t" + i + "\tavgFitnest:\t" + pop.getAVGLenght() +"\t" + "best:\t" + pop.getFittest().getDistance());
        	}
            pop = GA.evolvePopulation(pop);
        }
        System.out.println("100% ");
        
        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
}