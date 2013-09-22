package sis.summer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import sis.session.CourseSession;

public class SummerCourseSession extends CourseSession {
	public static SummerCourseSession create(String department, String number,
			Date startDate) {
	return new SummerCourseSession(department, number, startDate);
	}
	
	private SummerCourseSession(String department, String number, Date startDate) {
		super(department, number, startDate);
	}
	
	public Date getEndDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(mStartDate);
		int sessionLength = 8;
		int daysInWeek = 7;
		int daysFromFridatToMonday = 3;
		int numberOfDays = sessionLength * daysInWeek - daysFromFridatToMonday;
		calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
		return calendar.getTime();
	}
}
