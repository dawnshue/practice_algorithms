package coursera.stanford.algorithms2.week3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Knapsack {

	public static class Item {
		int weight;
		int value;

		public Item(int v, int w) {
			weight = w;
			value = v;
		}
	}

	public static class CustomComparator implements Comparator<Item> {
		@Override
		public int compare(Item a, Item b) {
			return a.weight - b.weight;
		}
	}

	public long getValue(List<Item> list, int max) {
		int listsize = list.size();
		Collections.sort(list, new CustomComparator());

		long[][] cache = new long[listsize + 1][max + 1];
		for (int i = 0; i <= max; i++) {
			cache[0][i] = 0;
		}

		for (int i = 1; i <= listsize; i++) {
			Item curr = list.get(i - 1);
			for (int j = 1; j <= max; j++) {

				if (curr.weight > j) {
					cache[i][j] = cache[i - 1][j];
				} else {
					int weight = j - curr.weight;
					long value = curr.value + cache[i][weight];
					cache[i][j] = Math.max(cache[i - 1][j], value);
				}

			}
		}

		return cache[listsize][max];
	}

	public static void main(String[] args) {
		//2493893
		//4243395
		try (Scanner scanner = new Scanner(new FileReader(
				"/Users/Vangie/Desktop/Main/School/Programming/algorithms/Algorithms 2 (Coursera - Stanford)/Week3_input1.txt"))) {
			if (!scanner.hasNext())
				return;

			String[] firstLine = scanner.nextLine().split(" ");
			int max = Integer.parseInt(firstLine[0]);
			int items = Integer.parseInt(firstLine[1]);
			List<Item> list = new ArrayList<>();
			for (int i = 0; i < items; i++) {
				String[] line = scanner.nextLine().split(" ");
				list.add(new Item(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
			}

			Knapsack program = new Knapsack();
			System.out.println(program.getValue(list, max));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
