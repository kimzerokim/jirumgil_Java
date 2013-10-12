package test.report;

import static sis.util.ReportConstant.NEWLINE;
import junit.framework.TestCase;
import sis.report.RosterReporter;
import sis.session.CourseSession;
import sis.session.Session;
import sis.studentInfo.Course;
import sis.studentInfo.Student;
import sis.util.DateUtil;

public class RosterReporterTest extends TestCase {
	public void testRosterReport() {
		Session session = CourseSession.create(new Course("ENGL", "101"),
				DateUtil.createDate(2003, 1, 6));

		session.enroll(new Student("A"));
		session.enroll(new Student("B"));

		String rosterReport = new RosterReporter(session).getReport();
		assertEquals(
				RosterReporter.ROSTER_REPORT_HEADER + "A" + NEWLINE + "B"
						+ NEWLINE + RosterReporter.ROSTER_REPORT_FOOTER + "2"
						+ NEWLINE, rosterReport);
	}
}