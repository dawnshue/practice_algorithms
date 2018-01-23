package algorithms.leetcode.q91;

public class DecodeWays {
	public int numDecoding(String s) {
		// Assuming sequence starting with 0 is valid
		if (s == null || s.isEmpty())
			return 0;

		int len = s.length();
		int[] ways = new int[len + 1];
		int[] translated = new int[len];
		ways[0] = 1;
		translated[0] = Integer.parseInt(s.substring(0, 1));
		ways[1] = translated[0] > 0 ? 1 : 0;
		for (int i = 2; i <= len; i++) {
			translated[i - 1] = Integer.parseInt(s.substring(i - 1, i));
			ways[i] = 0;
			if (translated[i - 1] > 0)
				ways[i] = ways[i - 1];
			if (translated[i - 2] * 10 + translated[i - 1] <= 26)
				ways[i] = ways[i] + ways[i - 2];

		}
		return ways[len];
	}
}
