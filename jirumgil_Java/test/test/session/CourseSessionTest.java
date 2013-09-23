package test.session;

import static util.ReportConstant.NEWLINE;

import java.util.Date;

import sis.report.CourseReport;
import sis.session.CourseSession;
import sis.session.Session;
import util.DateUtil;

public class CourseSessionTest extends SessionTest {
	private static final int CREDITS = 3;

	public void testSessionCreate() {
		assertEquals("ENGL", mSession.getDepartment());
		assertEquals("101", mSession.getNumber());
		assertEquals(0, mSession.getNumberOfStudents());
	}

	public void testCourseDates() {
		Date mStartDate = DateUtil.createDate(2003, 1, 6);
		Session mSession = createSession("ENGL", "200", mStartDate);
		Date sixteenWeeksOut = DateUtil.createDate(2003, 4, 25);
		assertEquals(sixteenWeeksOut, mSession.getEndDate());
	}

	public void testCountSessions() {
		CourseSession.resetCount();
		createSession("", "", new Date());
		assertEquals(1, CourseSession.getCount());
		createSession("", "", new Date());
		assertEquals(2, CourseSession.getCount());
	}

	public void testReport() {
		final Date date = new Date();
		CourseReport report = new CourseReport();
		report.add(CourseSession.create("ENGL", "101", date));
		report.add(CourseSession.create("CZEC", "200", date));
		report.add(CourseSession.create("ITAL", "410", date));
		report.add(CourseSession.create("CZEC", "220", date));
		report.add(CourseSession.create("ITAL", "330", date));

		assertEquals("CZEC 200" + NEWLINE + "CZEC 220" + NEWLINE + "ENGL 101"
				+ NEWLINE + "ITAL 330" + NEWLINE + "ITAL 410" + NEWLINE,
				report.text());
	}

	private CourseSession createCourseSession() {
		CourseSession session = CourseSession.create("ENGL", "101", startDate);
		session.setNumberOfCredits(CourseSessionTest.CREDITS);
		return session;
	}
	
	protected Session createSession(String department, String number, Date startDate) {
		return CourseSession.create(department, number, startDate);
	}
}
