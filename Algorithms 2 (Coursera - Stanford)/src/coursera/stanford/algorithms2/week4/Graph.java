package coursera.stanford.algorithms2.week4;

public class Graph {
	protected int[][] graph;

	public Graph(int[][] graph) {
		this.graph = graph;
	}

	public void initialize() {
		for (int y = 0; y < graph.length; y++) {
			for (int x = 0; x < graph.length; x++) {
				graph[y][x] = Integer.MAX_VALUE;
			}
		}
	}

	@Override
	public String toString() {
		String output = "";

		for (int y = 0; y < graph.length; y++) {
			for (int x = 0; x < graph[y].length; x++) {
				if (graph[y][x] != Integer.MAX_VALUE) {
					output += String.format("%3d", graph[y][x]);
				} else {
					output += String.format("%3s", "∞");
				}
			}

			output += "\n";
		}

		return output;
	}

	public int[][] getGraph() {
		return graph;
	}

	public void setGraph(int[][] graph) {
		this.graph = graph;
	}
}