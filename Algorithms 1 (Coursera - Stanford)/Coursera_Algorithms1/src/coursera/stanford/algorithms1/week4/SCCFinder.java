package coursera.stanford.algorithms1.week4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class SCCFinder {

	private Map<Integer, Vertex> graph;
	private Queue<Integer> queue;
	private Map<Integer, Integer> components;

	public void run() {
		try (Scanner scanner = new Scanner(new FileReader(
				"/Users/Vangie/Desktop/Main/School/Coursera/Algorithms 1 (Stanford)/Assignments/Week4_input.txt"))) {
			graph = new TreeMap<>();
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] tokens = line.split(" ");
				Integer v1 = Integer.parseInt(tokens[0]);
				Integer v2 = Integer.parseInt(tokens[1]);
				// if (v1 == v2)
				// continue;
				{
					Vertex value = graph.get(v1);
					if (value == null) {
						value = new Vertex(v1);
					}
					value.addOutgoing(v2);
					graph.put(v1, value);
				}
				{
					Vertex value = graph.get(v2);
					if (value == null) {
						value = new Vertex(v2);
					}
					value.addIncoming(v1);
					graph.put(v2, value);
				}
			}
			System.out.println("Graph size: " + graph.size());

			queue = new LinkedList<>();
			DFS();
			System.out.println("Ordered list size: " + queue.size());

			components = new TreeMap<>();
			while (!queue.isEmpty()) {
				Integer v = queue.remove();
				assignComponent(v, v);
			}

			System.out.println("SCCs found: " + components.size());
			List<Integer> sortedList = new ArrayList<>(components.values());
			Collections.sort(sortedList);
			Collections.reverse(sortedList);
			System.out.println("Top SCCs:");
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(sortedList.get(i));
				} catch (Exception e) {
					System.out.println(0);
				}
			}

		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void DFS() {
		for (Vertex v : graph.values()) {
			visit(v);
		}
	}

	public void visit(Vertex v) {
		if (!v.isVisited()) {
			v.visit();
			for (Integer neighbor : v.getOutgoing()) {
				visit(graph.get(neighbor));
			}
		}
		queue.add(v.getName());
	}

	public void assignComponent(Integer v, Integer root) {

		Vertex vertex = graph.get(v);
		if (vertex.getRoot() == null) {
			vertex.setRoot(root);
			if (components.get(root) == null) {
				components.put(root, 1);
			} else {
				int count = components.get(root);
				components.put(root, count + 1);
			}
			for (Integer neighbor : vertex.getIncoming()) {
				assignComponent(neighbor, root);
			}
		}
	}

	public static void main(String[] args) {
		SCCFinder finder = new SCCFinder();
		finder.run();
	}
}
