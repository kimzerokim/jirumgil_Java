package test.studentInfo;

import junit.framework.TestCase;
import sis.studentInfo.HonorsGradingStrategy;
import sis.studentInfo.Student;
import sis.studentInfo.Student.Grade;

public class StudentTest extends TestCase {
	private static final double GRADE_TOLERANCE = 0.05;

	public void testCreate() {
		final String firstStudentName = "Jane Doe";
		Student firstStudent = new Student(firstStudentName);
		assertEquals("Jane", firstStudent.getFirstName());
		assertEquals("Doe", firstStudent.getLastName());
		assertEquals("", firstStudent.getMiddleName());

		final String secondStudentName = "Blow";
		Student secondStudent = new Student(secondStudentName);
		asserEquals("", secondStudent.getFirstName());
		asserEquals("Blow", secondStudent.getLastName());
		asserEquals("", secondStudent.getMiddleName());

		final String thirdStudentName = "Raymond Douglas Davies";
		Student thirdStudent = new Student(thirdStudentName);
		assertEquals("Raymond", thirdStudent.getFirstName());
		assertEquals("Davies", thirdStudent.getLastName());
		assertEquals("Douglas", thirdStudent.getMiddleName());
	}

	public void testStudentStatus() {
		Student student = new Student("a");
		assertEquals(0, student.getCredits());
		assertFalse(student.isFullTime());

		student.addCredits(3);
		assertEquals(3, student.getCredits());
		assertFalse(student.isFullTime());

		student.addCredits(4);
		assertEquals(7, student.getCredits());
		assertFalse(student.isFullTime());

		student.addCredits(5);
		assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME,
				student.getCredits());
		assertTrue(student.isFullTime());
	}

	public void testInState() {
		Student student = new Student("a");
		assertFalse(student.isInState());
		student.setState(Student.IN_STATE);
		assertTrue(student.isInState());
		student.setState("MD");
		assertFalse(student.isInState());
	}

	public void testCalculateGpa() {
		Student student = new Student("A");
		assertGpa(student, 0.0);
		student.addGrade(Grade.A);
		assertGpa(student, 4.0);
		student.addGrade(Grade.B);
		assertGpa(student, 3.5);
		student.addGrade(Grade.C);
		assertGpa(student, 3.0);
		student.addGrade(Grade.D);
		assertGpa(student, 2.5);
	}

	private void assertGpa(Student student, double points) {
		assertEquals(points, student.getGpa(), GRADE_TOLERANCE);
	}

	public void testCalculateHoneorsSudentGpa() {
		assertGpa(createHonorsStudent(), 0.0);
		assertGpa(createHonorsStudent(Student.Grade.A), 5.0);
		assertGpa(createHonorsStudent(Student.Grade.B), 4.0);
		assertGpa(createHonorsStudent(Student.Grade.C), 3.0);
		assertGpa(createHonorsStudent(Student.Grade.D), 2.0);
		assertGpa(createHonorsStudent(Student.Grade.F), 0.0);
	}

	private Student createHonorsStudent(Student.Grade grade) {
		Student student = createHonorsStudent();
		student.addGrade(grade);
		return student;
	}

	private Student createHonorsStudent() {
		Student student = new Student("a");
		student.setGradingStrategy(new HonorsGradingStrategy());
		return student;
	}
}
