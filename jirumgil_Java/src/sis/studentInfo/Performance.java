package sis.studentInfo;

public class Performance {
	private int[] tests;

	public void setScore(int... tests) {
		this.tests = tests;
	}

	public int get(int testNumber) {
		return tests[testNumber];
	}

	public double average() {
		double total = 0.0;
		for (int score : tests)
			total += score;
		return total / tests.length;
	}
}
