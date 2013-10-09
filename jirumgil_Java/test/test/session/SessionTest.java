package test.session;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import sis.session.CourseSession;
import sis.session.Session;
import sis.studentInfo.SessionException;
import sis.studentInfo.Student;
import sis.util.DateUtil;

abstract public class SessionTest extends TestCase {
	protected Session mSession;
	protected Date startDate;
	public static final int CREDITS = 3;

	public void setUp() {
		startDate = DateUtil.createDate(2003, 1, 6);
		mSession = createSession("ENGL", "101", startDate);
		mSession.setNumberOfCredits(CREDITS);
	}

	abstract protected Session createSession(String department, String number, Date startDate);

	public void testEnrollStudents() {
		ArrayList<Student> allStudents = (ArrayList<Student>) mSession
				.getAllStudents();
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
	
	public void testIterate() {
		enrollStudents(mSession);
		
		List<Student> results = new ArrayList<Student>();
		for (Student student : mSession)
			results.add(student);
		
		assertEquals(mSession.getAllStudents(), results);
	}
	
	private void enrollStudents(Session session) {
		session.enroll(new Student("1"));
		session.enroll(new Student("2"));
		session.enroll(new Student("3"));
	}
	
	public void testSessionUrl() throws SessionException {
		final String url = "http://course.langrsoft.com/cmsc300";
		mSession.setUrl(url);
		assertEquals(url, mSession.getUrl().toString());
	}
	
	public void testInvalidSessionUrl() {
		final String url = "http://course.langrsoft.com/cmsc300";
		try {
			mSession.setUrl(url);
			//fail("expected exception due to invalid protocol in URL");
		}
		catch (SessionException expectedException){
			Throwable cause = expectedException.getCause();
			assertEquals(MalformedURLException.class, cause.getClass());
		}
	}
}

