package assignment10;

import java.util.LinkedList;

import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author Erin Parker, modified by Jacob Morrison and James Gibb
 * @version March 1, 2019
 */
public class Vertex {

	// used to id the Vertex
	private String name;
	
	public boolean visited; 
	public boolean start = false;
	public boolean goal = false;
	
	public double dFromStart;
	
	public int uniqueId;
	
	public Vertex previous;
	
	// adjacency list
	public LinkedList<Edge> adj;

	public Vertex(String name1) {
		this.setName(name1);
		this.adj = new LinkedList<Edge>();
	}

	public String getName() {
		return name;
	}

	public void addEdge(Vertex otherVertex) {
		adj.add(new Edge(otherVertex));
	}

	public Iterator<Edge> edges() {
		return adj.iterator();
	}


	public void addEdge(Vertex vertex2, double weight) {
		Edge newEdge = new Edge(vertex2);
		newEdge.weight = weight;
		adj.add(newEdge);
	}

	public void setDistanceFromStart(double positiveInfinity) {
		dFromStart = positiveInfinity;
		
	}

	public LinkedList<Edge> getEdges() {
		return adj;
	}

	public double getDistanceFromStart() {
		return dFromStart;
	}

	public void setPrevious(Vertex x) {
				previous = x;
	}

	public boolean getVisited() {
		return visited;
	}

	public void setVisited(boolean b) {
		visited = b;
	}

	public Vertex getPrevious() {
		return previous;
	}
	/**
	 * @return the start
	 */
	public boolean getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(boolean start) {
		this.start = start;
	}

	/**
	 * @return the goal
	 */
	public boolean getGoal() {
		return goal;
	}

	/**
	 * @param goal the goal to set
	 */
	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	public void setName(String name) {
		this.name = name;
	}
}