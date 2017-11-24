package coursera.stanford.algorithms2.week4;

public class FloydWarshall extends Graph {
	public FloydWarshall(int[][] graph) {
		super(graph);
	}

	public void process() {
		for (int y = 0; y < graph.length; y++) {
			for (int x = 0; x < graph[y].length; x++) {
				if (x != y && graph[y][x] != Integer.MAX_VALUE) {
					int currentNodeValue = graph[y][x];

					for (int i = 0; i < graph[x].length; i++) {
						System.out.println(y + " " + x + " " + i);
						if (i != x && graph[x][i] != Integer.MAX_VALUE) {
							int newNodeValue = currentNodeValue + graph[x][i];

							if (newNodeValue < graph[y][x]) {
								graph[y][x] = newNodeValue;
							}
						}
					}
				}
			}
		}
	}
}