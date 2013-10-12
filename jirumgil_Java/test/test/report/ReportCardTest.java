package test.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;
import sis.report.ReportCard;
import sis.studentInfo.Student;

public class ReportCardTest extends TestCase {
	private ReportCard card;

	protected void setUp() {
		card = new ReportCard();
	}

	public void testMessage() {
		assertEquals(ReportCard.A_MESSAGE, card.getMessage(Student.Grade.A));
		assertEquals(ReportCard.B_MESSAGE, card.getMessage(Student.Grade.B));
		assertEquals(ReportCard.C_MESSAGE, card.getMessage(Student.Grade.C));
		assertEquals(ReportCard.D_MESSAGE, card.getMessage(Student.Grade.D));
		assertEquals(ReportCard.F_MESSAGE, card.getMessage(Student.Grade.F));
	}

	public void testKeys() {
		Set<Student.Grade> expectedGrades = EnumSet.allOf(Student.Grade.class);
		Set<Student.Grade> grades = EnumSet.noneOf(Student.Grade.class);
		for (Student.Grade grade : card.getMessages().keySet())
			grades.add(grade);
		assertEquals(expectedGrades, grades);
	}

	public void testValues() {
		List<String> expectedMessages = new ArrayList<String>();
		expectedMessages.add(ReportCard.A_MESSAGE);
		expectedMessages.add(ReportCard.B_MESSAGE);
		expectedMessages.add(ReportCard.C_MESSAGE);
		expectedMessages.add(ReportCard.D_MESSAGE);
		expectedMessages.add(ReportCard.F_MESSAGE);

		Collection<String> messages = card.getMessages().values();
		for (String message : messages)
			assertTrue(expectedMessages.contains(message));
		assertEquals(expectedMessages.size(), messages.size());
	}

	public void testEntries() {
		Set<Entry> entries = new HashSet<Entry>();

		for (Map.Entry<Student.Grade, String> entry : card.getMessages()
				.entrySet())
			entries.add(new Entry(entry.getKey(), entry.getValue()));

		Set<Entry> expectedEntries = new HashSet<Entry>();
		expectedEntries.add(new Entry(Student.Grade.A, ReportCard.A_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.B, ReportCard.B_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.C, ReportCard.C_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.D, ReportCard.D_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.F, ReportCard.F_MESSAGE));

		assertEquals(expectedEntries, entries);
	}

	class Entry {
		private Student.Grade grade;
		private String message;

		Entry(Student.Grade grade, String message) {
			this.grade = grade;
			this.message = message;
		}

		@Override
		public boolean equals(Object object) {
			if (object.getClass() != this.getClass())
				return false;
			Entry that = (Entry) object;
			return this.grade == that.grade
					&& this.message.equals(that.message);
		}

		@Override
		public int hashCode() {
			final int hashMultiplier = 41;
			int result = 7;
			result = result * hashMultiplier + grade.hashCode();
			result = result * hashMultiplier + message.hashCode();
			return result;
		}
	}
}