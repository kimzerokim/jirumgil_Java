package sis.session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import sis.studentInfo.Student;

abstract public class Session implements Comparable<Session> {
	private String mDepartment;
	private String mNumber;
	private List<Student> mStudents = new ArrayList<Student>();
	private Date mStartDate;
	private int numberOfCredits;

	protected Session(String department, String number, Date startDate) {
		mDepartment = department;
		mNumber = number;
		mStartDate = startDate;
	}

	public int compareTo(Session that) {
		int compare = this.getDepartment().compareTo(that.getDepartment());
		if (compare != 0)
			return compare;
		return this.getNumber().compareTo(that.getNumber());
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public String getDepartment() {
		return mDepartment;
	}

	public String getNumber() {
		return mNumber;
	}

	public int getNumberOfStudents() {
		return mStudents.size();
	}

	public void enroll(Student student) {
		student.addCredits(numberOfCredits);
		mStudents.add(student);
	}

	public Student get(int index) {
		return mStudents.get(index);
	}

	protected Date getStartDate() {
		return mStartDate;
	}

	public ArrayList<Student> getAllStudents() {
		return (ArrayList<Student>) mStudents;
	}

	abstract protected int getSessionLength();

	public Date getEndDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getStartDate());
		final int daysInWeek = 7;
		final int daysFromFridayToMonday = 3;
		int numberOfDays = getSessionLength() * daysInWeek
				- daysFromFridayToMonday;
		calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
		return calendar.getTime();
	}

}
