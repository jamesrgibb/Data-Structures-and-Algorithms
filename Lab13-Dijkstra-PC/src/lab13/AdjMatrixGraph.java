package lab13;

import java.util.Comparator;

import components.list.List;
import components.list.ListOnJavaArrayList;
import components.map.Map;
import components.map.MapOnHashTable;
import components.queue.PriorityQueue;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Adjacency Matrix representation of the Graph.
 * 
 * @author James Gibb
 *
 */
public class AdjMatrixGraph {
  private class Vertex {
    int prev = UNDEFINED;
    int dist = INFINITY;

    int indexInMatrix;

    public Vertex(int index) {
      indexInMatrix = index;
    }

    @Override
    public String toString() {
      return "Vertex +" + indexInMatrix + "(dist=" + dist + ",prev=" + prev
          + ")";
    }
  }

  private static final int INFINITY = Integer.MAX_VALUE;
  private static final int UNDEFINED = Integer.MIN_VALUE;

  /*
   * Private members
   */
  private int[][] matrix;
  private Map<String, Integer> vertexMap;
  private Map<Integer, String> intVertexMap;
  private Map<Integer, Vertex> shorestPathMap;
  private String sspSource;
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
    vertexMap = new MapOnHashTable<>();
    intVertexMap = new MapOnHashTable<>();
    shorestPathMap = new MapOnHashTable<>();
    sspSource = "";
    nodeId = 0;
  }

  private List<String> intListToStringList(List<Integer> idxResult) {
    List<String> result = new ListOnJavaArrayList<>();
    for (Integer idx : idxResult)
      result.add(intVertexMap.value(idx));
    return result;
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

  /**
   * Adds a new vertex with the given label to this graph.
   * 
   * @param label label for the new vertex
   * 
   * @requires label is not a node of this graph
   * @modifies this
   */
  public void addVertex(String label) {
    assert !vertexMap
        .hasKey(label) : "Violation of: label is not a node of this graph";

    vertexMap.add(label, nodeId);
    intVertexMap.add(nodeId, label);
    sspSource = "";// adding a vertex invalidates previous ssp run
    nodeId++;
  }

  /**
   * Adds the edge (src, dst, cost) to this graph.
   * 
   * @param src  source node label
   * @param dst  destination node label
   * @param cost edge weight
   * @requires [edge cost is non-negative] and [src and dst are nodes in this
   *           graph]
   * @modifies this
   */
  public void addEdge(String src, String dst, int cost) {
    assert cost >= 0 : "Violation of: edge cost is non negative";
    assert vertexMap
        .hasKey(src) : "Violation of: src is a vertex already in the graph";
    assert vertexMap
        .hasKey(dst) : "Violation of: dst is a vertex already in the graph";

    int srcIndex = vertexMap.value(src);
    int dstIndex = vertexMap.value(dst);
    matrix[srcIndex][dstIndex] = cost;
    sspSource = "";// adding an edge invalidates previous ssp run
  }

  /**
   * Computes the single source shortest paths from the given source node.
   * 
   * @param src label of the start node
   * @requires [src is a node in this graph]
   * @modifies this
   */
  public void dijkstra(String src) {
    assert vertexMap
        .hasKey(src) : "Violation of: src is a node in this graph";

    int srcIdx = vertexMap.value(src);

    Comparator<Vertex> compareDistOfVerts = (v1, v2) -> Integer
        .compare(v1.dist, v2.dist);
    Queue<Vertex> pq = new PriorityQueue<Vertex>(compareDistOfVerts);

    shorestPathMap.clear();

    int numVerts = matrix.length;
    for (int currVertIdx = 0; currVertIdx < numVerts; currVertIdx++) {
      Vertex v = new Vertex(currVertIdx);
      shorestPathMap.add(currVertIdx, v);
      if (currVertIdx == srcIdx)
        v.dist = 0;
      pq.enqueue(v);
    }

    while (pq.size() > 0) {
      Vertex u = pq.dequeue();

      for (int v = 0; v < numVerts; v++) {
        if (matrix[u.indexInMatrix][v] < INFINITY && u.dist < INFINITY) {
          // if an edge exists and the node itself is reachable from the src
          int alt = u.dist + matrix[u.indexInMatrix][v];
          Vertex vertexV = shorestPathMap.value(v);
          if (alt < vertexV.dist) {
            vertexV.dist = alt;
            vertexV.prev = u.indexInMatrix;
          }
        }
      }
    }
    sspSource = src;
  }

  /**
   * Returns the shortest path cost from {@code src} to {@code dst}. Invokes
   * dijkstra if its last run was not with {@code src}.
   * 
   * @param src source node label
   * @param dst destination node label
   * @return shortest path cost, {@code INFINITY} if {@code dst} is not reachable
   *         from {@code src}
   * @requires [src is a node in this graph] and [dst is a node in this graph]
   * @modifies this
   */
  public int shortestPathCost(String src, String dst) {
    assert vertexMap
        .hasKey(src) : "Violation of: src is a node in this graph";
    assert vertexMap
        .hasKey(dst) : "Violation of: dst is a node in this graph";

    if (!src.equals(sspSource))
      dijkstra(src);
    int dstIdx = vertexMap.value(dst);
    Vertex sspVertex = shorestPathMap.value(dstIdx);
    return sspVertex.dist;
  }

  /**
   * Returns the description of the shortest path from {@code src} to {@code dst}.
   * Invokes dijkstra if its last run was not with {@code src}.
   * 
   * @param src source node label
   * @param dst destination node label
   * @return shortest path cost and the actual path
   * @requires [src is a node in this graph] and [dst is a node in this graph]
   * @modifies this
   */
  public String shortestPath(String src, String dst) {
    assert vertexMap
        .hasKey(src) : "Violation of: src is a node in this graph";
    assert vertexMap
        .hasKey(dst) : "Violation of: dst is a node in this graph";

    List<Integer> path = new ListOnJavaArrayList<>();
    if (!src.equals(sspSource))
      dijkstra(src);
    int dstIdx = vertexMap.value(dst);
    Vertex sspVertex = shorestPathMap.value(dstIdx);

    if (sspVertex.dist < INFINITY) {
      String result = "Shortest path cost = " + sspVertex.dist + ", Path = ";
      while (sspVertex.prev != UNDEFINED) {
        path.add(0, sspVertex.indexInMatrix);
        sspVertex = shorestPathMap.value(sspVertex.prev);
      }
      path.add(0, vertexMap.value(src));
      return result + intListToStringList(path).toString();
    } else {
      return dst + " is not reachable from " + src;
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

  public static void main(String[] args) {
    SimpleReader kbd = new SimpleReader1L();
    SimpleWriter console = new SimpleWriter1L();

    console.print(
        "Enter the path of the file to read the graph from (hit ENTER to quit): ");
    String file = kbd.nextLine(); // "e.g., data/lab13-graph-flights.txt"

    while (!file.equals("")) {
      AdjMatrixGraph g = new AdjMatrixGraph(file);

      console.println("Graph read from " + file + "\n");
      console.println(g.toString());

      console.print(
          "\n\nEnter the source node for the shortest path (hit ENTER to quit): ");
      String src = kbd.nextLine();

      while (!src.equals("")) {
        console.print("Enter the destination node for the shortest path: ");
        String dst = kbd.nextLine();

        console.println(g.shortestPath(src, dst));

        console.print("\n\nEnter the source node for the shortest path: ");
        src = kbd.nextLine();
      }
      console.print(
          "Enter the path of the file to read the graph from (hit ENTER to quit): ");
      file = kbd.nextLine();
    }
    kbd.close();
    console.close();
  }
}
