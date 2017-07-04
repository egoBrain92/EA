/*
* TSP_GA.java
* Create a tour and evolve a solution
*/

package tsp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStreamReader;


public class TSP_EA_Main {
	
	//EA params
	public static int generations = 1000;
	public static int populationSize = 5000;
	public static double mutationRate = 0.015;
	public static String file = "inputGraph";
	
	//output stuff
	public static int useDefaults = 1;
	public static int graphInfos = -1;
	public static int consoleLimiter = 10;
	public static int howManyGensToPrint = generations/10;
	public static int progressAllowed = 1;
	public static int init = 1;
	public static int Continue = 1;
	
		
    public static void main(String[] args) throws IOException {

    	GraphHandler graphHandler = new GraphHandler();
    	Menu menu = new Menu();
    	
    	Tour bestTour = new Tour();
 
        menu.printInit();
        menu.getUserInputOnInit();
        
        if(init == 0){
            menu.printMenu();
            menu.getUserInputOnGraphPrinting();
            
            menu.printMenuEA();
        	menu.getUserInputOnEA();
        	
            menu.printMenuEA2();
        	menu.getUserInputOnEA2();
        	
            menu.printMenuEA3();
        	menu.getUserInputOnEA3();
        	
            menu.printMenuEA4();
        	menu.getUserInputOnEA4();
        	
        	if(useDefaults == 0){
        		menu.printEASettings();
        	}
        }

    	graphHandler.reachGraphFromFile(file);

    	//Setup the Population
        Population pop = new Population(populationSize, true);
        
        bestTour = pop.getFittest();
        
         // Evolve population for "generations" amount of times
        long startTime = System.currentTimeMillis();
        pop = EA.evolvePopulation(pop);
        
        int j = 1;
        int k = 10;
        if(progressAllowed == 1){
        	System.out.println("Progress: 0" + "%");
        }
		for (int i = 0; i < generations; i++) {
			if(j % (generations/10 ) == 0 && progressAllowed == 1){
				System.out.println("Progress: " + k + "%");
				k = k + 10;			
			}
			j++;
			if (i % consoleLimiter == 0 && i < howManyGensToPrint){
        		System.out.println("gen:\t" + i + "\tavgFitnest:\t" + pop.getAVGLenght() +"\t" + "best:\t" + bestTour.getDistance());
        	}

            pop = EA.evolvePopulation(pop);
            if(pop.getFittest().getDistance() < bestTour.getDistance()){
            	bestTour = pop.getFittest();
//            	if(bestTour.getDistance() == 440){
//            		long endTime   = System.currentTimeMillis();
//            		long totalTime = endTime - startTime;
//            		System.out.println("Runtime in MS: " + totalTime);
//            	}
            }
        }
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
        // Print results
		System.out.println();
		System.out.println("Runtime in MS: " + totalTime);
		System.out.println("Average for remaining individuals: " + pop.getAVGLenght());
        System.out.println("Best individual is: " + bestTour.getDistance());
        System.out.println("Best individual visits the nodes in the following order: ");
        System.out.println(bestTour);
    }
}