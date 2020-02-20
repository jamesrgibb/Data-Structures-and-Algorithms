package lab12;

import components.list.List;
import components.list.ListOnJavaArrayList;
import components.map.Map;
import components.map.MapOnHashTable;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * Adjacency Matrix representation of the Graph.
 * 
 * @author James Gibb
 *
 */
public class AdjMatrixGraph implements Graph {
  /*
   * Private members
   */
  private int[][] matrix;
  private Map<String, Integer> vertexMap;
  private Map<Integer, String> intVertexMap;
  private int nodeId;

  /**
   * Resets the matrix to the given number of vertices and resets the other fields
   * to their initial values.
   * 
   * @param numVertices number of vertices for the new graph
   * @modifies this
   */
  private void resize(int numVertices) {
    matrix = new int[numVertices][numVertices];
    for (int i = 0; i < numVertices; i++)
      for (int j = 0; j < numVertices; j++)
        matrix[i][j] = INFINITY;
    vertexMap = new MapOnHashTable<String, Integer>();
    intVertexMap = new MapOnHashTable<Integer, String>();
    nodeId = 0;
  }

  /**
   * No argument constructor.
   * 
   * @param numVertices number of vertices
   */
  public AdjMatrixGraph(int numVertices) {
    resize(numVertices);
  }

  /**
   * Constructor from a file.
   * 
   * @param file path of a file in the specified format
   * @requires the file is in this format: first line is an integer, indicating
   *           number of vertices (n), followed by n lines, each containing an
   *           edge in this comma-separated format: (src,dst,cost)
   */
  public AdjMatrixGraph(String file) {
    // precondition not checked
    SimpleReader in = new SimpleReader1L(file);
    int numVertices = Integer.parseInt(in.nextLine());
    resize(numVertices);
    while (!in.atEOS()) {
      String line = in.nextLine();
      String[] edgeParts = line.split(",");
      String src = edgeParts[0];
      if (!vertexMap.hasKey(src))
        addVertex(src);
      String dst = edgeParts[1];
      if (!vertexMap.hasKey(dst))
        addVertex(dst);
      int cost = Integer.parseInt(edgeParts[2]);
      addEdge(src, dst, cost);
    }
    in.close();
  }

  @Override
  public void addVertex(String label) {
    vertexMap.add(label, nodeId);
    intVertexMap.add(nodeId, label);
    nodeId++;
  }

  @Override
  public void addEdge(String src, String dst, int cost) {
    assert cost >= 0 : "Violation of: edge cost is non negative";
    assert vertexMap
        .hasKey(src) : "Violation of: src is a vertex already in the graph";
    assert vertexMap
        .hasKey(dst) : "Violation of: dst is a vertex already in the graph";

    int srcIndex = vertexMap.value(src);
    int dstIndex = vertexMap.value(dst);
    matrix[srcIndex][dstIndex] = cost;
  }

  @Override
  public int pathCost(List<String> path) {
    int cost = 0;
    int edgeCost = 0;
    int i = 0;
    while (edgeCost < INFINITY && i < path.size() - 1) {
      int srcIdx = vertexMap.value(path.get(i));
      int dstIdx = vertexMap.value(path.get(i + 1));
      edgeCost = matrix[srcIdx][dstIdx];
      cost += edgeCost;
      i++;
    }
    return edgeCost < INFINITY ? cost : INFINITY;
  }

  @Override
  public List<String> dfs(String start) {
    List<Integer> idxResult = new ListOnJavaArrayList<Integer>();
    dfs(vertexMap.value(start), idxResult);
    List<String> result = new ListOnJavaArrayList<String>();
    for (Integer idx : idxResult)
      result.add(intVertexMap.value(idx));
    return result;
  }

  /**
   * Recursively computes the depth first traversal from the given node by adding
   * the next node in DFS to the given list.
   * 
   * @param srcIdx       index of the starting node
   * @param visitedNodes indexes of the nodes visited in DFS so far
   */
  private void dfs(int srcIdx, List<Integer> visitedNodes) {
    visitedNodes.add(srcIdx);
    for (int dstIdx = 0; dstIdx < matrix[srcIdx].length; dstIdx++) {
      // if there is an edge and the dstIdx node has not been visited yet
      if (matrix[srcIdx][dstIdx] < INFINITY
          && visitedNodes.indexOf(dstIdx) < 0)
        dfs(dstIdx, visitedNodes);
    }
  }

  /*
   * Methods from Object
   */
  @Override
  public String toString() {
    int numVertices = intVertexMap.size();
    StringBuilder sb = new StringBuilder();
    sb.append("digraph g {\n");
    for (int i = 0; i < numVertices; i++)
      for (int j = 0; j < numVertices; j++)
        if (matrix[i][j] != INFINITY) {
          sb.append(String.format("%s->%s [label=%d];\n",
              intVertexMap.value(i), intVertexMap.value(j), matrix[i][j]));
        }
    sb.append("}\n");
    return sb.toString();
  }

}
