package coursera.stanford.algorithms1.week2;

public class QuickSort {
	
	private static int iterations = 0;

	public void main(String[] args) {

		int[] input = new int[10000];
		for (int i = 1; i <= 10000; i++) {
			int nextValue = 0; // EDIT
			input[i] = nextValue;
		}

		int[] output = partition(input, 0, 9999);

	}

	private int[] partition(int[] A, int l, int r) {
		int p = A[l];
		int i = l + 1;
		for (int j = i; j <= r; j++) {
			if (A[j] < p) {
				int temp = A[j];
				A[j] = A[i];
				A[i] = temp;
				i = i + 1;
			}
		}

		{
			int temp = A[l];
			A[l] = A[i - 1];
			A[i - 1] = temp;
		}
		return A;
	}

}
