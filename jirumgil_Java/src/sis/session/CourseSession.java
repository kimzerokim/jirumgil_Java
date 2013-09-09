package sis.session;

import java.util.ArrayList;

import sis.studentInfo.Student;

public class CourseSession {
	private String mDepartment;
	private String mSessionNumber;
	private ArrayList<Student> mStudentList = new ArrayList<Student>();

	public CourseSession(String department, String number) {
		mDepartment = department;
		mSessionNumber = number;
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

	public ArrayList<Student> getAllStudetns() {
		return mStudentList;
	}
}
