package coursera.stanford.algorithms1.week6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sum2 {

	public static void main(String[] args) {

		Map<Long, List<Long>> table = new HashMap<>();
		try (Scanner scanner = new Scanner(new FileReader(
				"/Users/Vangie/Desktop/Main/School/Coursera/Algorithms 1 (Stanford)/Assignments/Week6_input1.txt"))) {
			long i = 0;
			while (scanner.hasNext()) {
				Long line = Long.parseLong(scanner.nextLine());
				List<Long> value = table.get(line);
				if (value == null)
					value = new LinkedList<>();
				value.add(i);
				table.put(line, value);
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Sum2 program = new Sum2();
		System.out.println(program.twoSum(-10000, 10001, table)); //427
	}

	public long twoSum(int min, int max, Map<Long, List<Long>> table) {
		long count = 0;

		for (long i = min; i <= max; i++) {
			if (i % 1000 == 0)
				System.out.println("running... " + i);
			for (Map.Entry<Long, List<Long>> entry : table.entrySet()) {
				Long key = entry.getKey();

				if (((i != 2 * key) || entry.getValue().size() > 1) && table.containsKey(i - key)) {
					count++;
					break;
				}
			}
		}

		return count;
	}

}
