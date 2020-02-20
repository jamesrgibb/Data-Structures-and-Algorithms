package assignment10;

import components.list.List;
import components.list.ListOnArrays;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Jacob Morrison and James Gibb
 *
 */
public class PathFinder {
	public static Integer[][] map;
	static int numOfRows;
	static int numOfCols;
	private static List<Vertex> edges;
	private static Graph g;
	static Vertex start;
	static Vertex goal;

	/**
	 * This method will read a maze from a file with the given input name, and
	 * output the solved maze to a file with the given output name.
	 * 
	 * @param inputFileName
	 * @param outputFileName
	 * @return
	 */
	public static int solveMaze(String inputFileName, String outputFileName) {
		// .prev will tell me where to put the dots
		// call dfromstart on goal vertex (index goal is at)
		numOfCols = 0;
		numOfRows = 0;
		g = new Graph();
		edges = new ListOnArrays<>();
		loadMaze(inputFileName);
		int pathCount = (int) g.distance;
		if(!goal.visited) {
			pathCount = -1 ;
		}
		// System.out.println("distance: " + g.distance + "\n");
		printMaze(outputFileName);

		return pathCount;
	}

	/**
	 * Outputs the solved maze into a file with "." in the shortest path
	 * 
	 *
	 * @returns solvedFile
	 */
	// TODO make sure javaDoc comments are correct
	public static void printMaze(String outputFileName) {

	SimpleWriter writer = new SimpleWriter1L(outputFileName);
	writer.println(numOfRows + " " + numOfCols);
	// System.out.println(numOfRows + " " + numOfCols);
	for (int i = 0; i < edges.size(); i++) {
	if (i % numOfCols == 0 && i != 0) {
	// System.out.println();
	writer.println();
	}
	if (edges.get(i).getName() != "." && edges.get(i).getName() != "S" && edges.get(i).getName() != "G"
	&& edges.get(i).getName() != "X") {
	edges.get(i).setName(" ");
	}
	// System.out.print(edges.get(i).getName());
	writer.print(edges.get(i).getName());
	}
	writer.println();

	writer.close();

	}

	/**
	 * Loads maze for a .txt file
	 * 
	 * @param first row must be two numbers, the first being the number of rows
	 *        and the second being the number of cols
	 * @param fileName
	 */
	public static void loadMaze(String fileName) {
		SimpleReader file = new SimpleReader1L(fileName);
		String[] dimensions = file.nextLine().split(" ");
		numOfRows = Integer.parseInt(dimensions[0]);
		numOfCols = Integer.parseInt(dimensions[1]);
		map = new Integer[numOfRows][numOfCols];
		int rowCount = 0;
		Integer nodeUniqueId = 0;
		Integer startUniqueId = 0;
		Integer goalUniqueId = 0;

		while (!file.atEOS()) {
			char[] eachChar = file.nextLine().toCharArray();
			for (int i = 0; i < numOfCols; i++) {
				if (eachChar[i] == 'X') {
					map[rowCount][i] = -1;
					Vertex x = new Vertex("X");
					x.uniqueId = nodeUniqueId;
					nodeUniqueId++;
					edges.add(x);
				}
				if (eachChar[i] == 'S') {
					// create start
					start = new Vertex("S");
					start.start = true;
					start.uniqueId = nodeUniqueId;
					map[rowCount][i] = nodeUniqueId;
					startUniqueId = nodeUniqueId;
					nodeUniqueId++;
					edges.add(start);

				}

				if (eachChar[i] == 'G') {
					// create end point
					goal = new Vertex("G");
					goal.goal = true;
					goal.uniqueId = nodeUniqueId;
					map[rowCount][i] = nodeUniqueId;
					goalUniqueId = nodeUniqueId;
					nodeUniqueId++;
					edges.add(goal);
				}

				if (eachChar[i] == ' ') {
					Vertex newVertex = new Vertex(nodeUniqueId.toString());
					newVertex.uniqueId = nodeUniqueId;
					nodeUniqueId++;
					map[rowCount][i] = nodeUniqueId;
					edges.add(newVertex);
				}
			}
			rowCount++;
		}
		file.close();

//		System.out.println("number of rows & cols: " + numOfRows + ", " + numOfCols);
//		System.out.println("startId: " + startUniqueId);
//		System.out.println("goalId: " + goalUniqueId);

		// System.out.println(Arrays.deepToString(map));
		// getEdges for start
		// System.out.println("start");
		getEdges(map, startUniqueId, start);
		// getEdges for goal
		// System.out.println("goal");
		getEdges(map, goalUniqueId, goal);
		// get edges for all nodes
		// can use double for loop to go through 2d array, to pass in the number at each
		// spot as long as it's not -1
		// by doing that we can lessen our code because we won't need a node/vertex
		// class, the graph class has
		// everything we will need
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).uniqueId != startUniqueId || edges.get(i).uniqueId != goalUniqueId) {
				getEdges(map, edges.get(i).uniqueId, edges.get(i));
			}
		}

		// number for start is parameter
		g.dijkstras(start.getName());
		// gets vertex from hashMap in Graph.java
		g.getVertex(goal.getName());
	}

	/**
	 * Sets north, south, east, and west edges if applicable
	 * 
	 * @param map
	 * @param node
	 */
	private static void getEdges(Integer[][] map, int uniqueId, Vertex vertex) {
//		int counter = 0;
//		for (int i = 1; i < numOfRows - 1; i++) {
//			for (int j = 1; j < numOfCols - 1; j++) {
//				counter++;
//				System.out.println("counter: " + counter);
		// i = rows
		int i = uniqueId / numOfCols;
		// j = cols
		int j = uniqueId % numOfCols;

		// System.out.println("this is node: " + uniqueId);
		if (i < numOfRows && j < numOfCols) {
			if (map[i][j] != -1) {
				// sets north node
				if (map[i - 1][j] != -1) {
					Integer temp = uniqueId - numOfCols;

					g.addEdge(vertex, edges.get(temp), 1);
//						System.out.println("n");
				}
				if (map[i + 1][j] != -1) {
					Integer temp = uniqueId + numOfCols;
					g.addEdge(vertex, edges.get(temp), 1);
//						System.out.println("s");
					// sets south node
				}
				if (map[i][j + 1] != -1) {
					Integer temp = uniqueId + 1;
					g.addEdge(vertex, edges.get(temp), 1);
//						System.out.println("e");
					// sets east node
				}

				if (map[i][j - 1] != -1) {
					Integer temp = uniqueId + -1;
					g.addEdge(vertex, edges.get(temp), 1);
//						System.out.println("w");

				}
			}
		}
//		}
	}

}
