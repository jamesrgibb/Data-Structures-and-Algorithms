package assignment10;

import java.util.HashMap;
import java.util.PriorityQueue;



/**
 * Represents a directed graph (a set of vertices and a set of edges). The graph
 * is not generic and assumes that a string name is stored at each vertex.
 * 
 * Dijkstra's shortest-path algorithm is included.
 * 
 * @author Erin Parker
 * @version March 1, 2019
 */
public class Graph {

	// the graph -- a set of vertices (String name mapped to Vertex instance)
	HashMap<String, Vertex> vertices;
	double distance = 0;
	int counter = 0;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<String, Vertex>();
	}

	/**
	 * Adds to the graph an edge from the vertex with name "name1" to the vertex
	 * with name "name2". The edge is associated with the "weight". If either vertex
	 * does not already exist in the graph, it is added.
	 */
	public void addEdge(Vertex vertex1, Vertex vertex2, int weight) {
		if (vertices.containsKey(vertex1.getName()))
			vertex1 = vertices.get(vertex1.getName());
		else {
			vertices.put(vertex1.getName(), vertex1);
		}
		if (vertices.containsKey(vertex2.getName()))
			vertex2 = vertices.get(vertex2.getName());
		else {
			vertices.put(vertex2.getName(), vertex2);
		}
		vertex1.addEdge(vertex2, weight);
	}

	// method to get vertex from graph so I can know distance and where dots go.
	public Vertex getVertex(String goal) {

		// return goal vertex
		return vertices.get(goal);
	}

	/**
	 * Uses Dijkstra's algorithm to determine the shortest paths from the starting
	 * vertex to every other vertex in the graph.
	 * 
	 * Use Java's PriorityQueue class to find the "unvisited vertex with smallest
	 * distance from s". In order to associate each vertex with a priority (related
	 * to its distance from s), you must either use the Comparable interface or the
	 * Comparator interface. See the API for PriorityQueue, and ask the course staff
	 * if you need help.
	 */
	public void dijkstras(String startName) {
		// get starting vertex
		Vertex start = vertices.get(startName);
		if (start == null)
			throw new UnsupportedOperationException("Vertex " + startName + " does not exist.");

		// set up priority queue of vertices prioritized by smallest distance
		// from
		// start (CHANGE if using Comparator)
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>((v1, v2) -> (int) (v1.dFromStart - v2.dFromStart));

		// for all vertices: set distance from start to INF
		for (Vertex v : vertices.values())
			v.setDistanceFromStart(Double.POSITIVE_INFINITY);

		start.setDistanceFromStart(0);
		pq.offer(start);
		Vertex x = new Vertex("");
		// while there are vertices left to consider . . .
		while (!pq.isEmpty()) {
			// get the unvisited vertex with the smallest distance from start
			x = pq.poll();

			if (x.getName().equals("G")) {
				x.setVisited(true);
				break;
			}
			// for all of the edges from vertex x . . .
			for (Edge e : x.getEdges()) {
				// get the destination vertex for the edge and consider the cost
				// of this
				// path
				Vertex dest = e.getOtherVertex();
				double cost = e.getWeight() + x.getDistanceFromStart();

				// if the cost of this path is smaller than the previously
				// considered
				// path, update

				if (dest.getDistanceFromStart() > cost) {
					dest.setDistanceFromStart(cost);
					dest.setPrevious(x);

					// if the destination vertex has not been visited, add to
					// the priority
					// queue

					if (!dest.getVisited())
						pq.offer(dest);

				}
			}
			x.setVisited(true);
		}
			distance = x.dFromStart;
		//System.out.println("Shortest path from " + start.getName() + " to " + x.getName() + " " + " has length "
		//		+ x.getDistanceFromStart() + " and is ");
		String s = "";
		while (!x.getName().equals(start.getName())) {
			if (!x.getName().equals("G")) {
				x.setName(".");
			}
			s = " --> " + x.getName() + s;
			x = x.getPrevious();

		}

		//System.out.println(start.getName() + s);
	}
};
