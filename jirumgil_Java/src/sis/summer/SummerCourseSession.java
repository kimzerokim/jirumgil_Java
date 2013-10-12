package sis.summer;

import java.util.Date;

import sis.session.Session;
import sis.studentInfo.Course;

public class SummerCourseSession extends Session {
   public static SummerCourseSession create(Course course, Date startDate) {
      return new SummerCourseSession(course, startDate);
   }

   private SummerCourseSession(Course course, Date startDate) {
      super(course, startDate);
   }

   @Override
   protected int getSessionLength() {
      return 8;
   }
}