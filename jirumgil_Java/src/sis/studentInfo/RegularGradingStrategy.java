package sis.studentInfo;

import sis.studentInfo.Student.Grade;

public class RegularGradingStrategy implements GradingStrategy {
	public int getGradePointsFor(Student.Grade grade) {
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
}
