package sis.studentInfo;

import java.util.ArrayList;

public class Student {
	public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
	public static final String IN_STATE = "CO";
	private String mName;
	private int mCredits;
	private String mState = "";
	private ArrayList<Grade> grades = new ArrayList<Grade>();
	private GradingStrategy mGradingStrategy = new BasicGradingStrategy();

	public enum Grade {
		A(4), B(3), C(2), D(1), F(0);

		private int points;

		Grade(int points) {
			this.points = points;
		}

		int getPoints() {
			return points;
		}
	};

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
			total += mGradingStrategy.getGradePointsFor(grade);
		}
		return total / grades.size();
	}

	// parameter에서 초기화를 하여 사용하는 방법등이 있을 수 있다.
	public void setGradingStrategy(GradingStrategy gradingStrategy) {
		mGradingStrategy = gradingStrategy;
	}

}
