package test.session;

import java.util.Date;

import sis.session.CourseSession;
import sis.session.Session;
import sis.studentInfo.Course;
import sis.util.DateUtil;

public class CourseSessionTest extends SessionTest {
   public void testCourseDates() {
      Date startDate = DateUtil.createDate(2003, 1, 6);
      Session session = createSession(createCourse(), startDate);
      Date sixteenWeeksOut = DateUtil.createDate(2003, 4, 25);
      assertEquals(sixteenWeeksOut, session.getEndDate());
   }

   public void testCount() {
      CourseSession.resetCount();
      createSession(createCourse(), new Date());
      assertEquals(1, CourseSession.getCount());
      createSession(createCourse(), new Date());
      assertEquals(2, CourseSession.getCount());
   }

   private Course createCourse() {
      return new Course("ENGL", "101");
   }

   protected Session createSession(Course course, Date date) {
      return CourseSession.create(course, date);
   }
}