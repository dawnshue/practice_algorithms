package coursera.stanford.algorithms2.week1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Greedy {

	public static final Comparator<Job> DIFFERENCE_COMP = new Comparator<Job>() {
		@Override
		public int compare(Job b, Job a) {
			int diff = a.difference() - b.difference();
			if (diff == 0)
				diff = a.weight - b.weight;
			return diff > 0 ? 1 : -1;
		}
	};

	public static final Comparator<Job> RATIO_COMP = new Comparator<Job>() {
		@Override
		public int compare(Job b, Job a) {
			double diff = a.ratio() - b.ratio();
			return diff > 0 ? 1 : -1;
		}
	};

	public static class Job {
		int length;
		int weight;

		public Job(int weight, int length) {
			this.length = length;
			this.weight = weight;
		}

		public int difference() {
			return weight - length;
		}

		public double ratio() {
			return (double) weight / length;
		}

		@Override
		public String toString() {
			return "Job [" + weight + ", " + length + "]";
		}
	}

	public static long timeSum(Set<Job> jobs) {
		long sum = 0;
		long time = 0;
		for (Job job : jobs) {
			time = time + job.length;
			sum = sum + time * job.weight;
		}
		return sum;
	}

	public static void main(String[] args) {

		Set<Job> diffjobs = new TreeSet<>(DIFFERENCE_COMP);
		Set<Job> ratiojobs = new TreeSet<>(RATIO_COMP);
		try (Scanner scanner = new Scanner(new FileReader(
				"/Users/Vangie/Desktop/Main/School/Programming/algorithms/Algorithms 2 (Coursera - Stanford)/Week1_input1.txt"))) {
			if (!scanner.hasNext())
				return;
			int numjobs = Integer.parseInt(scanner.nextLine());
			for (int i = 0; i < numjobs; i++) {
				Job nextjob = new Job(scanner.nextInt(), scanner.nextInt());
				diffjobs.add(nextjob);
				ratiojobs.add(nextjob);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Difference: " + timeSum(diffjobs)); // 69119377652
		System.out.println("Ratio: " + timeSum(ratiojobs));
	}

}
