package lab12;

import static org.junit.Assert.*;

import org.junit.Test;

import components.list.List;
import components.util.Utilities;

public class GraphTest {

  /**
   * Creates an empty graph with the given number of nodes.
   * 
   * @param numNodes number of nodes in the graph
   * @return generated graph
   * @requires numNodes > 0
   */
  private Graph uut(int numNodes) {
    return new AdjListGraph(numNodes); 
  }

  /**
   * Reads from the file and creates the graph based on its contents.
   * 
   * @param filename relative path of the file to read from
   * @return generated graph
   * @requires the file is in this format: first line is an integer, indicating
   *           number of vertices (n), followed by n lines, each containing an
   *           edge in this comma-separated format: (src,dst,cost)
   */
  private Graph uut(String filename) {
    return new AdjListGraph(filename); 
  }

  @Test
  public void constructorEmpty() {
    Graph g = uut(3);
    assertEquals("digraph g {\n}\n", g.toString());
  }

  @Test
  public void graphOneEdge() {
    Graph g = uut(2);
    g.addVertex("A");
    g.addVertex("B");
    g.addEdge("A", "B", 10);
    assertEquals("digraph g {\nA->B [label=10];\n}\n", g.toString());
  }

  @Test
  public void graphSimple() {
    Graph g = uut("data/L12-graph-simple.txt");
    assertEquals("digraph g {\nA->B [label=1];\nB->C [label=1];\n}\n",
        g.toString());
  }

  @Test
  public void graphCycles() {
    Graph g = uut("data/L12-graph-cycles.txt");
    assertEquals("digraph g {\n" + "A->B [label=10];\n" + "A->E [label=3];\n"
        + "B->E [label=4];\n" + "B->C [label=2];\n" + "E->B [label=1];\n"
        + "E->C [label=8];\n" + "E->D [label=2];\n" + "C->D [label=9];\n"
        + "D->C [label=7];\n" + "}\n", g.toString());
  }

  @Test
  public void graphFlights() {
    Graph g = uut("data/L12-graph-flights.txt");
    assertEquals("digraph g {\n" + "SEA->SFO [label=114];\n"
        + "SEA->ORD [label=175];\n" + "SFO->SLC [label=87];\n"
        + "SLC->SEA [label=98];\n" + "SLC->ORD [label=110];\n"
        + "SLC->ATL [label=98];\n" + "ORD->SFO [label=125];\n"
        + "ORD->JFK [label=110];\n" + "ATL->ORD [label=90];\n"
        + "ATL->JFK [label=104];\n" + "}\n", g.toString());
  }

  @Test
  public void graphFlightsPathCostOrdSlc() {
    Graph g = uut("data/L12-graph-flights.txt");
    List<String> path = Utilities.listFromArgs("ORD", "SLC");
    assertEquals(Graph.INFINITY, g.pathCost(path));
  }

  @Test
  public void graphFlightsPathCostSeaOrdSfoSlc() {
    Graph g = uut("data/L12-graph-flights.txt");
    List<String> path = Utilities.listFromArgs("SEA", "ORD", "SFO", "SLC");
    assertEquals(387, g.pathCost(path));
  }

  @Test
  public void graphFlightsPathCostAtlOrdSfoSlcSea() {
    Graph g = uut("data/L12-graph-flights.txt");
    List<String> path = Utilities.listFromArgs("ATL", "ORD", "SFO", "SLC",
        "SEA");
    assertEquals(400, g.pathCost(path));
  }

  @Test
  public void graphFlightsPathCostSeaSfo() {
    Graph g = uut("data/L12-graph-flights.txt");
    List<String> path = Utilities.listFromArgs("SEA", "SFO");
    assertEquals(114, g.pathCost(path));
  }

  @Test
  public void graphFlightsDfsSea() {
    Graph g = uut("data/L12-graph-flights.txt");

    assertEquals("[SEA,SFO,SLC,ORD,JFK,ATL]", g.dfs("SEA").toString());
  }

  @Test
  public void graphFlightsDfsSfo() {
    Graph g = uut("data/L12-graph-flights.txt");

    assertEquals("[SFO,SLC,SEA,ORD,JFK,ATL]", g.dfs("SFO").toString());
  }

  @Test
  public void graphFlightsDfsSlc() {
    Graph g = uut("data/L12-graph-flights.txt");

    assertEquals("[SLC,SEA,SFO,ORD,JFK,ATL]", g.dfs("SLC").toString());
  }

  @Test
  public void graphFlightsDfsOrd() {
    Graph g = uut("data/L12-graph-flights.txt");

    assertEquals("[ORD,SFO,SLC,SEA,ATL,JFK]", g.dfs("ORD").toString());
  }

  @Test
  public void graphFlightsDfsAtl() {
    Graph g = uut("data/L12-graph-flights.txt");

    assertEquals("[ATL,ORD,SFO,SLC,SEA,JFK]", g.dfs("ATL").toString());
  }

  @Test
  public void graphFlightsDfsJfk() {
    Graph g = uut("data/L12-graph-flights.txt");

    assertEquals("[JFK]", g.dfs("JFK").toString());
  }
}
