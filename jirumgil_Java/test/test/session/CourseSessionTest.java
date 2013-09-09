package test.session;

import java.util.ArrayList;

import junit.framework.TestCase;
import sis.session.CourseSession;
import sis.studentInfo.Student;

public class CourseSessionTest extends TestCase {
	private CourseSession mSession;

	public void setUp() {
		mSession = new CourseSession("ENGL", "101");
	}

	public void testCreate() {
		assertEquals("ENGL", mSession.getDepartment());
		assertEquals("101", mSession.getNumber());
		assertEquals(0, mSession.getNumberOfStudents());
	}

	public void testEnrollStudents() {
		ArrayList<Student> allStudents = mSession.getAllStudetns();

		Student student1 = new Student("Cain DiVoe");
		mSession.enroll(student1);
		assertEquals(1, mSession.getNumberOfStudents());
		assertEquals(1, allStudents.size());
		assertEquals(student1, allStudents.get(0));

		Student student2 = new Student("Coralee DeVaughn");
		mSession.enroll(student2);
		assertEquals(2, mSession.getNumberOfStudents());
		assertEquals(2, allStudents.size());
		assertEquals(student1, allStudents.get(0));
		assertEquals(student2, allStudents.get(1));
	}
}
