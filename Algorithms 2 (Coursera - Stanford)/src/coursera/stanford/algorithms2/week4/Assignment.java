package coursera.stanford.algorithms2.week4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(new FileReader(
				"/Users/Vangie/Desktop/Main/School/Programming/algorithms/Algorithms 2 (Coursera - Stanford)/Week4_input1.txt"))) {
			if (!scanner.hasNext())
				return;

			String[] firstLine = scanner.nextLine().split(" ");
			int vertices = Integer.parseInt(firstLine[0]);
			int edges = Integer.parseInt(firstLine[1]);
			List<Edge> edgeList = new ArrayList<>();

			for (int e = 0; e < edges; e++) {
				String[] line = scanner.nextLine().split(" ");
				int source = Integer.parseInt(line[0]);
				int end = Integer.parseInt(line[1]);
				int length = Integer.parseInt(line[2]);
				edgeList.add(new Edge(length, source, end));
			}

			Johnson graph = new Johnson(vertices, edgeList);
			Integer shortest = graph.process();
			System.out.println("shortest path: "+shortest);
			// -9, -12, -11

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
