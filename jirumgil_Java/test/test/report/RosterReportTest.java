package test.report;

import junit.framework.TestCase;
import sis.report.RosterReporter;
import sis.session.CourseSession;
import sis.studentInfo.Student;
import sis.util.DateUtil;
import static sis.util.ReportConstant.NEWLINE;

public class RosterReportTest extends TestCase {
	public void testRosterReport() {
		CourseSession session = CourseSession.create("ENGL", "101",
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
