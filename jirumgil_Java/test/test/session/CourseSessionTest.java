package test.session;

import static util.ReportConstant.NEWLINE;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import sis.report.CourseReport;
import sis.session.CourseSession;
import sis.studentInfo.Student;
import util.DateUtil;

public class CourseSessionTest extends TestCase {
	private CourseSession mSession;
	private Date mStartDate;
	private static final int CREDITS = 3;

	public void setUp() {
		mStartDate = DateUtil.createDate(2003, 1, 6);
		mSession = createCourseSession();
	}

	public void testSessionCreate() {
		assertEquals("ENGL", mSession.getDepartment());
		assertEquals("101", mSession.getNumber());
		assertEquals(0, mSession.getNumberOfStudents());
	}

	public void testEnrollStudents() {
		ArrayList<Student> allStudents = mSession.getAllStudents();

		Student student1 = new Student("Cain DiVoe");
		mSession.enroll(student1);
		assertEquals(CREDITS, student1.getCredits());
		assertEquals(1, mSession.getNumberOfStudents());
		assertEquals(1, allStudents.size());
		assertEquals(student1, allStudents.get(0));

		Student student2 = new Student("Coralee DeVaughn");
		mSession.enroll(student2);
		assertEquals(CREDITS, student2.getCredits());
		assertEquals(2, mSession.getNumberOfStudents());
		assertEquals(2, allStudents.size());
		assertEquals(student1, allStudents.get(0));
		assertEquals(student2, allStudents.get(1));
	}

	public void testCourseDates() {
		Date sixteenWeeksOut = DateUtil.createDate(2003, 4, 25);
		assertEquals(sixteenWeeksOut, mSession.getEndDate());
	}

	public void testCountSessions() {
		CourseSession.resetCount();
		createCourseSession();
		assertEquals(1, CourseSession.getCount());
		createCourseSession();
		assertEquals(2, CourseSession.getCount());
	}
	
	public void testComparable() {
		final Date date = new Date();
		CourseSession sessionA = CourseSession.create("CMSC", "101", date);
		CourseSession sessionB = CourseSession.create("ENGL", "101", date);
		assertTrue(sessionA.compareTo(sessionB) < 0);
		assertTrue(sessionB.compareTo(sessionA) > 0);
		
		CourseSession sessionC = CourseSession.create("CMSC", "101", date);
		assertEquals(0, sessionA.compareTo(sessionC));
		
		CourseSession sessionD = CourseSession.create("CMSC", "210", date);
		assertTrue(sessionC.compareTo(sessionD) < 0);
		assertTrue(sessionD.compareTo(sessionC) > 0);
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
		CourseSession session = CourseSession.create("ENGL", "101", mStartDate);
		session.setNumberOfCredits(CourseSessionTest.CREDITS);
		return session;
	}
}
