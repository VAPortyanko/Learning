package test;

import java.util.Arrays;

public class Xbonacci {

	public double[] tribonacci(double[] s, int n) {

		double[] result = Arrays.copyOf(s, n);

		if (n < 4) {
			return result;
		}

		for (int i = 3; i < n; i++) {
			result[i] = result[i - 1] + result[i - 2] + result[i - 3];
		}

		return result;
	}
}
