package lab13;

/**
 * This class represents an edge between a source vertex and a destination
 * vertex in a directed graph.
 * 
 * The source of this edge is the Vertex whose object has an adjacency list
 * containing this edge.
 * 
 * @author James Gibb
 
 */
public class Edge {

	// destination of this directed edge
	private Vertex dst;
	
	public double weight;
	
	public Edge(Vertex dst) {
		this.dst = dst;
	}

	public Vertex getOtherVertex() {
		return this.dst;
	}

	public String toString() {
		return this.dst.getName();
	}

	public double getWeight() {
		return weight;
	}
}