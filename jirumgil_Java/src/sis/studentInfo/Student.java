package sis.studentInfo;

import java.util.ArrayList;

public class Student {
	public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
	public static final String IN_STATE = "CO";
	private String mName;
	private int mCredits;
	private String mState = "";
	private ArrayList<Grade> grades = new ArrayList<Grade>();

	public enum Grade {
		A, B, C, D, F
	};

	private boolean isHonors = false;
	private boolean isSenatorsSon = false;

	public Student(String name) {
		mName = name;
		mCredits = 0;
	}

	public String getName() {
		return mName;
	}

	public boolean isFullTime() {
		return mCredits >= CREDITS_REQUIRED_FOR_FULL_TIME;
	}

	public int getCredits() {
		return mCredits;
	}

	public void addCredits(int credits) {
		mCredits += credits;
	}

	public boolean isInState() {
		return mState.equals(Student.IN_STATE);
	}

	public void setState(String state) {
		mState = state;
	}

	public void addGrade(Grade grade) {
		grades.add(grade);
	}

	public double getGpa() {
		if (grades.isEmpty())
			return 0.0;
		double total = 0.0;
		for (Grade grade : grades) {
			total += gradePointsFor(grade);
		}
		return total / grades.size();
	}

	private double gradePointsFor(Grade grade) {
		if (isSenatorsSon) {
			if (grade.equals(Grade.A))
				return 4;
			if (grade.equals(Grade.B))
				return 4;
			if (grade.equals(Grade.C))
				return 4;
			if (grade.equals(Grade.D))
				return 4;
			return 3;
		} else {
			double points = basicGradePointsFor(grade);
			if (isHonors)
				if (points > 0)
					points += 1;
			return points;
		}
	}

	private int basicGradePointsFor(Grade grade) {
		if (grade.equals(Grade.A))
			return 4;
		if (grade.equals(Grade.B))
			return 3;
		if (grade.equals(Grade.C))
			return 2;
		if (grade.equals(Grade.D))
			return 1;
		return 0;
	}

	public void setHonors() {
		isHonors = true;
	}
}
