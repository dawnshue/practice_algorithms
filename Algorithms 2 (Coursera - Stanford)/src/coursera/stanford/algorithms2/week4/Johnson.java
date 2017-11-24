package coursera.stanford.algorithms2.week4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Johnson {
	private boolean negativeCycle;
	private final int vertices;
	private final List<Edge> edges;

	private List<Edge> shiftedEdges;

	private Integer[][] graph;
	private Integer[][] predecessor;

	public Johnson(int v, List<Edge> e) {
		this.vertices = v;
		this.edges = e;
		this.negativeCycle = false;
	}

	public boolean hasNegativeCycle() {
		return negativeCycle;
	}

	public Integer process() {
		Integer[] prices = johnsonBellmanFord(vertices, edges);
		shiftedEdges = getShiftedEdges(prices, edges);
		if (!negativeCycle)
			return null;

		Integer shortest = null;
		for (int i = 0; i < vertices; i++) {
			int current = dijkstra(i);
			if (shortest == null || current < shortest)
				shortest = current;
		}
		return shortest;
	}

	// Algorithms: https://brilliant.org/wiki/dijkstras-short-path-finder/
	private int dijkstra(int source) {
		Integer[] dist = new Integer[vertices];
		dist[source] = 0;

		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		for (int i = 0; i < vertices; i++)
			if (i != source)
				queue.add(i);

		Set<Integer> visited = new HashSet<>();

		while (!queue.isEmpty()) {
			Integer vertex = queue.remove();
			for (Edge e : shiftedEdges) {
				if (e.source == vertex) {
					int alt = dist[vertex] + e.length;
					if (alt < dist[e.end])
						dist[e.end] = alt;
				}
			}
		}

		int shortest = dist[0];
		for (int i = 0; i < dist.length; i++) {
			if (dist[i] < shortest)
				shortest = dist[i];
		}
		return shortest;
	}

	public static List<Edge> getShiftedEdges(Integer[] prices, List<Edge> edges) {
		List<Edge> shifted = new ArrayList<>();
		for (Edge e : edges) {
			int length = e.length + prices[e.end] - prices[e.source];
			shifted.add(new Edge(length, e.source, e.end));
		}
		return shifted;
	}

	// Run Bellman Ford for single source shortest path from new vertex "0"
	// https://www.youtube.com/watch?v=-mOEd_3gTK0
	private Integer[] johnsonBellmanFord(int vertices, List<Edge> edges) {
		Integer[] shortest = new Integer[vertices + 1];
		Integer[] parent = new Integer[vertices + 1];
		shortest[0] = 0;

		// if end-distance > source-distance + edge-weight
		// then update end-distance & set parent to source vertex
		for (int i = 0; i < vertices; i++) {
			// vertex 0 has an edge to all other vertices of weight 0
			// source-vertex = 0
			for (int endV = 1; endV <= vertices; endV++) {
				int sourceV = 0;
				int sum = shortest[sourceV] + 0;
				if (shortest[endV] == null || shortest[endV] > sum) {
					shortest[endV] = sum;
					parent[endV] = sourceV;
				}
			}

			// run on original list of edges
			for (Edge e : edges) {
				int sourceV = e.source;
				int endV = e.end;
				Integer sum = (shortest[sourceV] == null) ? null : shortest[sourceV] + e.length;
				if (sum != null && (shortest[endV] == null || shortest[endV] > sum)) {
					shortest[endV] = sum;
					parent[endV] = sourceV;
				}
			}
		}

		// one more cycle to detect if there is a negative cycle
		for (Edge e : edges) {
			int sourceV = e.source;
			int endV = e.end;
			Integer sum = (shortest[sourceV] == null) ? null : shortest[sourceV] + e.length;
			if (sum != null && shortest[endV] != null && shortest[endV] > sum) {
				negativeCycle = true;
			}
		}

		return shortest;
	}
	/*
	 * private void initialize() { for (int i = 0; i < vertices; i++) {
	 * graph[i][i] = 0; } }
	 * 
	 * public void setEdge(int source, int end, int length) { graph[source -
	 * 1][end - 1] = length; }
	 */
}
