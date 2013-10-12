package test.summer;

import java.util.Date;

import sis.session.Session;
import sis.studentInfo.Course;
import sis.summer.SummerCourseSession;
import sis.util.DateUtil;
import test.session.SessionTest;

public class SummerCourseSessionTest extends SessionTest {
   public void testEndDate() {
      Date startDate = DateUtil.createDate(2003, 6, 9);
      Session session =
         createSession(new Course("ENGL", "200"), startDate);
      Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
      assertEquals(eightWeeksOut, session.getEndDate());
   }

   protected Session createSession(Course course, Date date) {
      return SummerCourseSession.create(course, date);
   }
}