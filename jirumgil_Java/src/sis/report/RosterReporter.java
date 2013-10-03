package sis.report;

import static sis.util.ReportConstant.NEWLINE;
import sis.session.CourseSession;
import sis.studentInfo.Student;

public class RosterReporter {
	public static final String ROSTER_REPORT_HEADER = 
			"Student" + NEWLINE + "-" + NEWLINE;
	public static final String ROSTER_REPORT_FOOTER = 
			NEWLINE + "# students = ";
	
	private CourseSession mSession;
	
	public RosterReporter(CourseSession session) {
		mSession = session;
	}
	
	public String getReport() {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append(ROSTER_REPORT_HEADER);
		
		for (Student student : mSession.getAllStudents()) {
			buffer.append(student.getName());
			buffer.append(NEWLINE);
		}
		
		buffer.append(ROSTER_REPORT_FOOTER + mSession.getAllStudents().size() + NEWLINE);
		
		return buffer.toString();
	}
}
