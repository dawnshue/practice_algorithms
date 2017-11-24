package coursera.stanford.algorithms2.week1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Prim {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(new FileReader(
				"/Users/Vangie/Desktop/Main/School/Programming/algorithms/Algorithms 2 (Coursera - Stanford)/Week1_input2.txt"))) {
			if (!scanner.hasNext())
				return;
			int nodes = scanner.nextInt();
			int edges = scanner.nextInt();
			Integer[][] graph = new Integer[nodes][nodes];
			Map<Integer, Vertex> vertices = new HashMap<>();
			for (int e = 0; e < edges; e++) {
				int a = scanner.nextInt() - 1;
				int b = scanner.nextInt() - 1;
				int cost = scanner.nextInt();
				graph[a][b] = cost;
				graph[b][a] = cost;
				{
					Vertex v = vertices.get(a);
					if (v == null) {
						v = new Vertex(a);
					}
					v.addEdge(b);
					vertices.put(a, v);
				}
				{
					Vertex v = vertices.get(b);
					if (v == null) {
						v = new Vertex(b);
					}
					v.addEdge(a);
					vertices.put(b, v);
				}
			}
			Prim prim = new Prim(graph, vertices);
			// -3612829

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static class Vertex {
		int name;
		Integer parent;
		boolean included;
		Set<Integer> edges = new HashSet<>();

		public Vertex(int name) {
			this.name = name;
			this.parent = null;
			this.included = false;
		}

		public void addEdge(int b) {
			edges.add(b);
		}
	}

	public Prim(Integer[][] graph, Map<Integer, Vertex> vertices) {

		int V = graph.length;
		Integer[] parent = new Integer[V];
		Integer[] key = new Integer[V];
		boolean[] mstSet = new boolean[V];

		key[0] = 0;
		int count = 0;
		do {
			int u = minKey(key, mstSet);
			mstSet[u] = true;
			for (int v = 0; v < V; v++) {
				if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
					parent[v] = u;
					key[v] = graph[u][v];
				}
			}

			count++;
		} while (count < V);

		printMST(parent, graph);
	}

	public int minKey(Integer[] key, boolean[] mstSet) {
		Integer min = null;
		Integer min_index = null;
		for (int i = 0; i < key.length; i++) {
			if (min == null || min_index == null || (!mstSet[i] && key[i] != null && key[i] < min)) {
				min = key[i];
				min_index = i;
			}
		}
		return min_index;
	}

	public void printMST(Integer[] parent, Integer[][] graph) {
		int sum = 0;
		for (int i = 1; i < graph.length; i++) {
			sum = sum + graph[i][parent[i]];
		}
		System.out.println("MST sum: " + sum);
	}

}
