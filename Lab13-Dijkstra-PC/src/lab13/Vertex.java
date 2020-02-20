package lab13;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author James Gibb
 */
public class Vertex {

	// used to id the Vertex
	private String name;
	
	public boolean visited;
	
	public double dFromStart;
	
	public Vertex previous;

	// adjacency list
	private LinkedList<Edge> adj;

	public Vertex(String name) {
		this.name = name;
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

	public String toString() {
		String s = "Vertex " + name + " adjacent to ";
		Iterator<Edge> itr = adj.iterator();
		while(itr.hasNext())
			s += itr.next() + "  ";
		return s;
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
}