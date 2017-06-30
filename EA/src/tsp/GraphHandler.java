package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GraphHandler {

	double noEdgeLenght = 9999;
	public static ArrayList<City> inputCitys = new ArrayList<City>();
	int numberOfCitys = 0;
	int numberOfEdges = 0;
	public static double[][] adj;
	
	public void reachGraphFromFile(String fileNameAndPath)
			throws FileNotFoundException {

		// reading meta information
		Scanner sc = new Scanner(new File(fileNameAndPath));
		numberOfCitys = sc.nextInt();
//		
		numberOfEdges = sc.nextInt();
//		

		
		adj = new double[numberOfCitys][numberOfCitys];

		readingCitys(sc);
		readingEdges(sc, adj);
//		
		
		replaceNotExisitingEdges(adj, numberOfCitys, 0, noEdgeLenght);
		
		
		if(numberOfEdges == 0){
			System.out.println("calculated all edges ");
			calcGrid(adj, numberOfCitys);
		}
		if(TSP_GA.graphInfos == 1 || TSP_GA.graphInfos == 2){
			System.out.println("Number of Citys: " + numberOfCitys);
			System.out.println("Number of Edges: " + numberOfEdges);
			printAndCalcEdgeCoverage(numberOfCitys, numberOfEdges);
			if(TSP_GA.graphInfos == 1){
				printGrid(adj, numberOfCitys);
			}
		}
//		

	}

	private void readingCitys(Scanner sc) {
		// reading citys
		for (int i = 0; i < numberOfCitys; i++) {
			double x = Double.parseDouble(sc.next());
			double y = Double.parseDouble(sc.next());
			// System.out.println("City: " + i + " x: "+x + " y: "+y);
			City dummy = new City(x, y, i);
			inputCitys.add(dummy);
			TourManager.addCity(dummy);
		}
//		System.out.println("added citys");
	}

	private void readingEdges(Scanner sc, double[][] adj) {
		// reading edges
		for (int i = 0; i < numberOfEdges; i++) {
			int x = Integer.parseInt(sc.next());
			int y = Integer.parseInt(sc.next());
			double dis = Double.parseDouble(sc.next());
			// System.out.println("edge: " + dis +" x: "+x + " y: "+y);
			adj[x][y] = dis;
			adj[y][x] = dis;
		}
//		System.out.println("added edges");
//		System.out.println();
	}
	
	private void printAndCalcEdgeCoverage (int numberOfCitys, int numberOfEdges) {
		int citys = numberOfCitys;
		double edges = numberOfEdges;
		int maxEdges = 0;
		maxEdges = (citys * (citys -1)) / 2;
		double coverageInPercent = 0;
		coverageInPercent = edges/maxEdges;
		System.out.println("Maxiumum possible edges population: " + maxEdges);
		System.out.println("Edge population for the graph is: " + coverageInPercent * 100 +"%.");
	}

	public void printGrid(double[][] a, int matrixSize) {
		System.out.println("Resulting adj Matrix: ");
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				if (a[i][j] == noEdgeLenght) {
					System.out.printf("%s\t", "[   ]");
				} else {
					System.out.printf("%.0f\t", a[i][j]);
				}
			}
			System.out.println();
		}
	}

	public void calcGrid(double[][] a, int matrixSize) {
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				if(a[i][j] != 0 || a[i][j] !=noEdgeLenght){
					City cityI = inputCitys.get(i);
					City cityJ = inputCitys.get(j);
					a[i][j] = distanceTo(cityI, cityJ);
					a[j][i] = distanceTo(cityI, cityJ);

				}
			}
		}
	}

	public void replaceNotExisitingEdges(double[][] a, int matrixSize, double target,
			double replaceValue) {
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				if (a[i][j] == target) {
					if (i != j) { //avoid diagonal line
						a[i][j] = replaceValue;
					}
				}
			}
		}
	}
	
	public void replaceHalfGridWithDis(double[][] a, int matrixSize) {
		int j = 0;
		for (int i = 0; i < matrixSize; i++) {
			j = 0;
			while (j < i) {
				a[i][j] = 666;
				j++;
			}
		}
	}
	
    // Gets the distance to given city
    public double distanceTo(City cityStart, City city){
    	double xDistance = Math.abs(cityStart.getX() - city.getX());
    	double yDistance = Math.abs(cityStart.getY() - city.getY());
        double distance  = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        //rounding for better readability in console
        int distance2 = (int) (distance + 0.5);
        distance = distance2;
        return distance;
    }
}
