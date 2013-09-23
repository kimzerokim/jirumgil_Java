package test.report;

import static util.ReportConstant.NEWLINE;

import java.util.Date;

import junit.framework.TestCase;
import sis.report.CourseReport;
import sis.session.CourseSession;

public class CourseReportTest extends TestCase {
	public void testReport() {
		final Date date = new Date();
		CourseReport report = new CourseReport();
		report.add(CourseSession.create("ENGL", "101", date));
		report.add(CourseSession.create("CZEC", "200", date));
		report.add(CourseSession.create("ITAL", "410", date));

		assertEquals("CZEC 200" + NEWLINE + "ENGL 101" + NEWLINE + "ITAL 410"
				+ NEWLINE, report.text());
	}
}
