package sis.session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import sis.studentInfo.Student;

public class CourseSession {
	private String mDepartment;
	private String mSessionNumber;
	private ArrayList<Student> mStudentList = new ArrayList<Student>();
	private Date mStartDate;
	public static int count = 0;

	private CourseSession(String department, String number, Date startDate) {
		mDepartment = department;
		mSessionNumber = number;
		mStartDate = startDate;
	}
	
	public static CourseSession create(String department, String number, Date startDate) {
		incrementCount();
		return new CourseSession(department, number, startDate);
	}

	public String getDepartment() {
		return mDepartment;
	}

	public String getNumber() {
		return mSessionNumber;
	}

	public int getNumberOfStudents() {
		return mStudentList.size();
	}

	public void enroll(Student student) {
		mStudentList.add(student);
	}

	public ArrayList<Student> getAllStudents() {
		return mStudentList;
	}

	public Student get(int index) {
		return mStudentList.get(index);
	}

	public Date getEndDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(mStartDate);
		int numberOfDays = 16 * 7 - 3;
		calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
		return calendar.getTime();
	}

	public Date getStartDate() {
		return mStartDate;
	}

	public static int getCount() {
		return count;
	}

	public static void resetCount() {
		count = 0;		
	}
	
	public static void incrementCount() {
		count++;
	}
}
