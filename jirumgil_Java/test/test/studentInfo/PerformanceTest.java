package test.studentInfo;

import junit.framework.TestCase;
import sis.studentInfo.Performance;

public class PerformanceTest extends TestCase {
	private static final double tolerance = 0.005;

	public void testAverage() {
		Performance performance = new Performance();
		performance.setScore(75, 72, 90, 60);

		assertEquals(74.25, performance.average(), tolerance);
	}

}
