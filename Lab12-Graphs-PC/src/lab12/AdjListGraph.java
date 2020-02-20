package lab12;

import components.list.List;
import components.list.ListOnArrays;
import components.list.ListOnJavaArrayList;
import components.map.Map;
import components.map.MapOnHashTable;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * Adjacency List representation of the Graph.
 * 
 * @author James Gibb
 *
 */
public class AdjListGraph implements Graph {
	public static class Edge {
		int dest;
		int cost;

		public Edge(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

	}

	/*
	 * Private members
	 */
	private List<List<Edge>> adjList;
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
		// initialize the adjList
		adjList = new ListOnArrays<List<Edge>>();
		for (int i = 0; i < numVertices; i++) {
			List<Edge> index = new ListOnArrays<>();
			// add the edges to the list
			adjList.add(index);

		}

		vertexMap = new MapOnHashTable<String, Integer>();
		intVertexMap = new MapOnHashTable<Integer, String>();
		nodeId = 0;
	}

	/**
	 * No argument constructor.
	 * 
	 * @param numVertices number of vertices
	 */
	public AdjListGraph(int numVertices) {
		resize(numVertices); // create a new adj list
	}

	/**
	 * Constructor from a file.
	 * 
	 * @param file path of a file in the specified format
	 * @requires the file is in this format: first line is an integer, indicating
	 *           number of vertices (n), followed by n lines, each containing an
	 *           edge in this comma-separated format: (src,dst,cost)
	 */
	public AdjListGraph(String file) {
		SimpleReader in = new SimpleReader1L(file);
		int numVertices = Integer.parseInt(in.nextLine());
		resize(numVertices);
		while (!in.atEOS()) { // iterate over the file
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
		// add label to vertexMap and intVertexMap
		vertexMap.add(label, nodeId);
		intVertexMap.add(nodeId, label);
		nodeId++;
		adjList.add(nodeId, new ListOnArrays<Edge>());
	}

	@Override
	public void addEdge(String src, String dst, int cost) {
		// add edges to the corresponding lists and map
		int srcIndex = vertexMap.value(src);
		int dstIndex = vertexMap.value(dst);
		adjList.get(srcIndex).add(new Edge(dstIndex, cost));
	}

	@Override
	public int pathCost(List<String> path) {
		int total = 0;
		int cost = INFINITY;
		for (int i = 0; i < path.size() - 1; i++) {
			int pIndex = vertexMap.value(path.get(i)); // retreive the value p from vertex map
			int nextIndex = vertexMap.value(path.get(i + 1));
			// retreive the following p +1 from vertex map
			for (int j = 0; j < adjList.get(pIndex).size(); j++) {
				if (adjList.get(pIndex).get(j).dest == nextIndex) {
					cost = adjList.get(pIndex).get(j).cost; // set cost equal to the following index
				}
			}
			total += cost; // increment the total cost
		}
		return total;
	}

	/*
	 * Methods from Object
	 */
	@Override
		public String toString() {
		int comp;
		int x;
		int k;
		for(x=1;x<adjList.get(x).size();x++) {
			k = x-1;
			comp = adjList.get(x).get(x).dest;
			while(k>=0 && adjList.get(x).get(k).dest>comp) {
				adjList.get(x).get(k+1).dest =  adjList.get(x).get(k+1).dest;
				k = k-1;
			}
			adjList.get(x).get(k+1).dest = comp;
		}
		int numVertices = intVertexMap.size();
		StringBuilder sb = new StringBuilder();
		sb.append("digraph g {\n");
		for (int i = 0; i < numVertices; i++)
			for (int j = 0; j < adjList.get(i).size(); j++)
				sb.append(String.format("%s->%s [label=%d];\n", intVertexMap.value(i),
						intVertexMap.value(adjList.get(i).get(j).dest), adjList.get(i).get(j).cost));
		sb.append("}\n");
		return sb.toString();
	}

	/*
	 * Recursive Method for depth first search
	 * 
	 * @param String start - the starting value on the vertex map
	 */
	@Override
	public List<String> dfs(String start) {
		List<Integer> visitedNodes = new ListOnJavaArrayList<Integer>();
		dfsH(vertexMap.value(start), visitedNodes);
		List<String> result = new ListOnJavaArrayList<String>();
		for (Integer i : visitedNodes)
			result.add(intVertexMap.value(i));
		return result;
	}

	/*
	 * Helper method for the recursive dfs
	 */
	private void dfsH(int srcIndex, List<Integer> visitedNodes) {
		visitedNodes.add(srcIndex);
		for (int dstIdx = 0; dstIdx < adjList.get(srcIndex).size(); dstIdx++) {
			if (visitedNodes.indexOf(adjList.get(srcIndex).get(dstIdx).dest) < 0) {
				dfsH(adjList.get(srcIndex).get(dstIdx).dest, visitedNodes);
			}
		}

	}

}
