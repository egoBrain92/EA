package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GraphHandler {

	double noEdgeLenght = 9999;
	public static ArrayList<Node> inputNodes = new ArrayList<Node>();
	int numberOfNodes = 0;
	int numberOfEdges = 0;
	public static double[][] adj;
	
	public void reachGraphFromFile(String fileNameAndPath)
			throws FileNotFoundException {

		// reading meta information
		Scanner sc = new Scanner(new File(fileNameAndPath));
		numberOfNodes = sc.nextInt();
//		
		numberOfEdges = sc.nextInt();
//		

		
		adj = new double[numberOfNodes][numberOfNodes];

		readingNodes(sc);
		readingEdges(sc, adj);
//		
		
		replaceNotExisitingEdges(adj, numberOfNodes, 0, noEdgeLenght);
		
		
		if(numberOfEdges == 0){
			System.out.println("calculated all edges ");
			calcGrid(adj, numberOfNodes);
		}
		if(TSP_EA_Main.graphInfos == 1 || TSP_EA_Main.graphInfos == 2){
			System.out.println("Number of Nodes: " + numberOfNodes);
			System.out.println("Number of Edges: " + numberOfEdges);
			printAndCalcEdgeCoverage(numberOfNodes, numberOfEdges);
			if(TSP_EA_Main.graphInfos == 1){
				printGrid(adj, numberOfNodes);
			}
		}
//		

	}

	private void readingNodes(Scanner sc) {
		// reading nodes
		for (int i = 0; i < numberOfNodes; i++) {
			double x = Double.parseDouble(sc.next());
			double y = Double.parseDouble(sc.next());
			// System.out.println("node: " + i + " x: "+x + " y: "+y);
			Node dummy = new Node(x, y, i);
			inputNodes.add(dummy);
			TourManager.addNode(dummy);
		}
//		System.out.println("added nodes");
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
	
	private void printAndCalcEdgeCoverage (int numberOfNodes, int numberOfEdges) {
		int nodes = numberOfNodes;
		double edges = numberOfEdges;
		int maxEdges = 0;
		maxEdges = (nodes * (nodes -1)) / 2;
		double coverageInPercent = 0;
		coverageInPercent = edges/maxEdges;
		System.out.println("Maxiumum possible edges population: " + maxEdges);
		
		if(numberOfEdges == 0){
			System.out.println("Edge population for the graph is: 100%");
		}else{
			System.out.println("Edge population for the graph is: " + coverageInPercent * 100 +"%.");
		}
		
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
					Node nodeI = inputNodes.get(i);
					Node nodeJ = inputNodes.get(j);
					a[i][j] = distanceTo(nodeI, nodeJ);
					a[j][i] = distanceTo(nodeI, nodeJ);

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
	
    // Gets the distance to given node
    public double distanceTo(Node nodeStart, Node node){
    	double xDistance = Math.abs(nodeStart.getX() - node.getX());
    	double yDistance = Math.abs(nodeStart.getY() - node.getY());
        double distance  = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        //rounding for better readability in console
        int distance2 = (int) (distance + 0.5);
        distance = distance2;
        return distance;
    }
}
