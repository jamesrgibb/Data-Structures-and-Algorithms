package lab13;

/**
 * An example of using the Graph class.
 * 
 * @author James Gibb
 */
public class GraphDemo {

	  public static void main(String[] args) {

	    // build a sample graph
	    Graph g = new Graph();
	    
	    g.addEdge("A", "E", 4);
	    g.addEdge("A", "B", 1);
	    g.addEdge("A", "F", 8);
	    g.addEdge("E", "F", 5);
	    g.addEdge("B", "F", 6);
	    g.addEdge("B", "C", 2);
	    g.addEdge("B", "G", 6);
	    g.addEdge("C", "G", 2);
	    g.addEdge("C", "D", 1);
	    g.addEdge("G", "F", 1);
	    g.addEdge("G", "H", 1);
	    g.addEdge("D", "G", 1);
	    g.addEdge("D", "H", 4);
	    
	    //Where Dijkstras starts at. 
	    g.dijkstras("A");
	    System.out.println();

	  }
}