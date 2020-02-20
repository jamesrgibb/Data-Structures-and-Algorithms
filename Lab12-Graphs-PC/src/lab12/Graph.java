package lab12;

import components.list.List;

/**
 * Graph component with primary methods. (Note: by package-wide convention, all
 * references are non-null.)
 * 
 * @author James Gibb
 * 
 */
public interface Graph {
  static final int INFINITY = Integer.MAX_VALUE;

  /**
   * Adds a vertex with the given label to this Graph.
   * 
   * @param label label for the added vertex
   * @modifies this
   * @requires label is not a node in this graph
   */
  void addVertex(String label);

  /**
   * Adds the given edge to this Graph.
   * 
   * @param src  source node label of this edge
   * @param dst  destination node label of this edge
   * @param cost cost of this edge
   * @modifies this
   * @requires cost >= 0 and src, dst are nodes already in this graph
   */
  void addEdge(String src, String dst, int cost);

  /**
   * Returns the total cost of the given path.
   * 
   * @param path List of labels which represents the shortest path
   * @return total cost of the shortest path
   * @requires all the labels in the path are nodes of this graph 
   */
  int pathCost(List<String> path);

  /**
   * Depth first traversal of the graph from the given start node.
   * 
   * @param start label of the node to start with
   * @return list of labels in the dfs order
   * @requires start is a node in this graph
   */
  List<String> dfs(String start);
}
