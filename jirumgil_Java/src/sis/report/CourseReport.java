package sis.report;

import java.util.ArrayList;
import java.util.Collections;

import static util.ReportConstant.NEWLINE;
import sis.session.CourseSession;

public class CourseReport {
	private ArrayList<CourseSession> mSessions = new ArrayList<CourseSession>();

	public void add(CourseSession session) {
		mSessions.add(session);
	}

	public String text() {
		Collections.sort(mSessions);
		StringBuilder builder = new StringBuilder();
		for (CourseSession session : mSessions) {
			builder.append(session.getDepartment() + " " + session.getNumber()
					+ NEWLINE);
		}
		return builder.toString();
	}
}
