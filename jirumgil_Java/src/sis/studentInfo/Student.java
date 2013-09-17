package sis.studentInfo;

public class Student {
	public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
	public static final String IN_STATE = "CO";
	private String mName;
	private int mCredits;
	private String mState = "";

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
}
