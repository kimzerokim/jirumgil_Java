package test.summer;

import java.util.Date;

import sis.session.Session;
import sis.summer.SummerCourseSession;
import test.session.SessionTest;
import util.DateUtil;

public class SummerCourseSessionTest extends SessionTest {
	public void testEndDate() {
		Date startDate = DateUtil.createDate(2003, 6, 9);
		Session session = SummerCourseSession.create("ENGL", "200",
				startDate);
		Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
		assertEquals(eightWeeksOut, session.getEndDate());
	}
	
	protected Session createSession(String department, String number, Date startDate) {
		return SummerCourseSession.create(department,  number, startDate);
	}
}
