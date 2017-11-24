package coursera.stanford.algorithms2.week2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Kruskal's Edge definition
 *
 */
class Edge implements Comparable<Edge> {
	final int src;
	final int dest;
	final int weight;

	public Edge(int src, int dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge another) {
		return this.weight - another.weight;
	}
}

/**
 * 
 * K-clustering class to determine the max spacing
 *
 */
public class KClustering {

	private int vertices; // number of vertices in the graph
	private List<Edge> edges; // input edge list

	public KClustering(int vertices) {
		this.vertices = vertices;
		this.edges = new LinkedList<>();
	}

	public void addEdge(int a, int b, int cost) {
		edges.add(new Edge(a, b, cost));
	}

	public int getMaxSpacing(int clusterCount) throws Exception {
		Collections.sort(edges);

		UnionFind uf = new UnionFind(vertices);
		if (clusterCount > uf.getCount()) {
			throw new Exception("Cluster count is less than input");
		} else {
			for (int i = 0; i < vertices; i++) {
				Edge edge = edges.get(i);

				/*
				 * if parents do not match, consider edge list for MST and union
				 * the two vertex
				 */
				if (!uf.isConnected(edge.src, edge.dest)) {
					if (uf.getCount() == clusterCount) {
						uf.printCluster1();
						return edge.weight;
					}

					int v1 = uf.Find(edge.src); // source parent vertex
					int v2 = uf.Find(edge.dest); // destination parent vertex
					uf.Union(v1, v2);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int CLUSTERS = 4;
		try (Scanner scanner = new Scanner(new FileReader(
				"/Users/Vangie/Desktop/Main/School/Programming/algorithms/Algorithms 2 (Coursera - Stanford)/Week2_input1.txt"))) {
			if (!scanner.hasNext())
				return;

			int nodes = Integer.parseInt(scanner.nextLine());
			KClustering program = new KClustering(nodes);

			while (scanner.hasNext()) {
				String[] line = scanner.nextLine().split(" ");
				program.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
			}

			System.out.println("Max spacing: " + program.getMaxSpacing(CLUSTERS));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}