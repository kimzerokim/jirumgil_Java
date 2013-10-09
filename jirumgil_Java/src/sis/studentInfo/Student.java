package sis.studentInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Student {
	public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
	public static final String IN_STATE = "CO";
	private String mName;
	private int mCredits;
	private String mState = "";
	private ArrayList<Grade> grades = new ArrayList<Grade>();
	private GradingStrategy mGradingStrategy = new BasicGradingStrategy();
	private String firstName = "";
	private String middleName = "";
	private String lastName;
	public static final int MAX_NAME_PARTS = 3;
	public static final String TOO_MANY_NAME_PARTS_MSG = "Student name '%s' contains more than %d parts";
	public final static Logger logger = Logger.getLogger(Student.class.getName());
	
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

	public Student(String fullName) {
		mName = fullName;
		mCredits = 0;
		List<String> nameParts = split(fullName);
		final int maximumNumberOfNameParts = 3;
		if (nameParts.size() > maximumNumberOfNameParts) {
			String message = String.format(Student.TOO_MANY_NAME_PARTS_MSG,
					fullName, MAX_NAME_PARTS);
			Student.logger.info(message);
			throw new StudentNameFormatException(message);
		}
		setName(nameParts);
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

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	private void setName(List<String> nameParts) {
		this.lastName = removeLast(nameParts);
		String name = removeLast(nameParts);
		if (nameParts.isEmpty())
			this.firstName = name;
		else {
			this.middleName = name;
			this.firstName = removeLast(nameParts);
		}
	}

	private String removeLast(List<String> list) {
		if (list.isEmpty())
			return "";
		return list.remove(list.size() - 1);
	}

	private List<String> split(String fullName) {
		List<String> results = new ArrayList<String>();
		for (String name : fullName.split(" "))
			results.add(name);
		return results;
	}
}
