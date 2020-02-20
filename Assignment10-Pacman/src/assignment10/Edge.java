package assignment10;

/**
 * This class represents an edge between a source vertex and a destination
 * vertex in a directed graph.
 * 
 * The source of this edge is the Vertex whose object has an adjacency list
 * containing this edge.
 * 
 * @author Erin Parker
 * @modified James Gibb and Jacob Morrison
 * @version March 1, 2019
 */

public class Edge {

	// destination of this directed edge
	private Vertex dst;
	// cost of each edge
	public double weight;
	/*
	 * constructor with param of a Vertex
	 */
	public Edge(Vertex dst) {
		this.dst = dst;
	}
	
	/**
	 * returns the connecting vertex
	 * @return - dst
	 */
	public Vertex getOtherVertex() {
		return this.dst;
	}
	/**
	 * returns the name of the Edge
	 */
	public String toString() {
		return this.dst.getName();
	}
	/**
	 * Returns the weight/cost of the edge
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}
}