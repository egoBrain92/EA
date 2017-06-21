package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GraphHandler {

	double noEdgeLenght = 9999999;
	public static ArrayList<City> inputCitys = new ArrayList<City>();
	int numberOfCitys = 0;
	int numberOfEdges = 0;
	public static double[][] adj;
	
	public void reachGraphFromFile(String fileNameAndPath)
			throws FileNotFoundException {

		// reading meta information
		Scanner sc = new Scanner(new File(fileNameAndPath));
		numberOfCitys = sc.nextInt();
		System.out.println("numberOfCitys: " + numberOfCitys);
		numberOfEdges = sc.nextInt();
		System.out.println("numberOfEdges: " + numberOfEdges);

		
		adj = new double[numberOfCitys][numberOfCitys];
		// ArrayList[][] adj = new ArrayList[numberOfCitys][numberOfCitys];

		readingCitys(sc);
		System.out.println("added citys");

		readingEdges(sc, adj);

		System.out.println("Resulting adj Matrix: ");
		printGrid(adj, numberOfCitys);
		calcGrid(adj, numberOfCitys);
		//printGrid(adj, numberOfCitys);
		replaceGrid(adj, numberOfCitys, 0, noEdgeLenght);
		printGrid(adj, numberOfCitys);
		// System.out.println(Arrays.deepToString(adj));

		// return;
		// sqrt(abs((9.5 - 35.4))^2 + abs((22.4 - 16.1))^2)
		// sqrt(abs((99.508652 - 60.130619))^2 + abs((40.058596 - 50.508133))^2)
		// (double)Math.round(value * 100000d) / 100000d
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
	}

	private void readingEdges(Scanner sc, double[][] adj) {
		// reading edges
		for (int i = 0; i < numberOfEdges; i++) {
			int x = Integer.parseInt(sc.next());
			int y = Integer.parseInt(sc.next());
			double dis = Double.parseDouble(sc.next());
			// System.out.println("edge: " + dis +" x: "+x + " y: "+y);
			adj[x][y] = dis;
		}
		System.out.println("added edges");
	}

	public void printGrid(double[][] a, int matrixSize) {
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				if (a[i][j] == noEdgeLenght) {
					System.out.printf("%s \t", "----");
				} else {
					System.out.printf("%.2f \t", a[i][j]);
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

	public void replaceGrid(double[][] a, int matrixSize, double target,
			double x) {
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				if (a[i][j] == target) {
					if (i != j) {
						a[i][j] = x;
					}
				}
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
