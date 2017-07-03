package tsp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public void printMenu(){
        System.out.println("Menu for Graph:");
        System.out.println("1. Print graph meta information and adjacency matrix");
        System.out.println("2. Print graph meta information without adjacency matrix");
        System.out.println("3. Skip printing graph meta information and adjacency matrix");
	}
	
	public void getUserInputOnGraphPrinting() throws IOException{
        try{
        	TSP_EA.graphInfos = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format.");
        }
	}
	
	public void printMenuEA(){
		System.out.println();
        System.out.println("Menu for EA output 1/4:");
        System.out.println("How often should the results of the current generation be printed? (1 = every time, 2 = every second time, 3 = every third time, ... )");
	}
	
	public void getUserInputOnEA() throws IOException{
        try{
        	TSP_EA.consoleLimiter = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format.");
        }
	}
	
	public void printMenuEA2(){
		System.out.println();
        System.out.println("Menu for EA output 2/4:");
        System.out.println("For up to how many generations should results be printed?");
	}
	
	public void getUserInputOnEA2() throws IOException{
        try{
        	TSP_EA.howManyGensToPrint = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format.");
        }
	}
	
	
	public void printMenuEA3(){
		System.out.println();
        System.out.println("Menu for EA output 3/4:");
        System.out.println("Should Progress for every 10% be printed? (0 = no, 1 = yes)");
	}
	
	public void getUserInputOnEA3() throws IOException{
        try{
        	TSP_EA.progressAllowed = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format.");
        }
	}
	
	public void printMenuEA4(){
		System.out.println();
        System.out.println("Menu for EA output 4/4:");
        System.out.println("Use default settings for EA? (0 = no, 1 = yes)");
	}
	
	public void getUserInputOnEA4() throws IOException{
        try{
        	TSP_EA.useDefaults = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format.");
        }
	}
	
	public void printEASettings() throws IOException{
		System.out.println();
        System.out.println("Menu for EA Settings 1/3:");
        System.out.println("How many generations should be evolved? (influences runtime)");
        getUserInputOnEAGenerations();
		System.out.println();
        System.out.println("Menu for EA Settings 2/3:");
        System.out.println("How large should the population be? (influences runtime)");
        getUserInputOnEAPopulationSize();
		System.out.println();
        System.out.println("Menu for EA Settings 3/3:");
        System.out.println("How high should be the mutation rate? (floating-point number possible)");
        getUserInputOnEAMutationRate();
        
        System.out.println();
        System.out.println("Following settings will be used for EA");
        System.out.println("Generations: "+ TSP_EA.generations);
        System.out.println("Population size: "+ TSP_EA.populationSize);
        System.out.println("Mutation ratee: "+ TSP_EA.mutationRate);
        System.out.println();
        System.out.println("Continue? (1 = yes)");
        getUserInputOnEAContinue();
	}
	
	public void getUserInputOnEAGenerations() throws IOException{
        try{
        	TSP_EA.generations = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format.");
        }
	}
	public void getUserInputOnEAPopulationSize() throws IOException{
        try{
        	TSP_EA.populationSize = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format.");
        }
	}
	public void getUserInputOnEAMutationRate() throws IOException{
		try {
			TSP_EA.mutationRate = Double.parseDouble(br.readLine());
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid Format.");
		}
	}
	
	public void getUserInputOnEAContinue() throws IOException{
        try{
        	TSP_EA.Continue = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format.");
        }
	}

	public void printInit() {
		System.out.println("Use default values for everything? (0 = no, 1 = yes)");
	}

	public void getUserInputOnInit() throws IOException {
		try {
			TSP_EA.init = Integer.parseInt(br.readLine());
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid Format.");
		}
	}
}
