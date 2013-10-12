package sis.session;

import java.util.Date;

import sis.studentInfo.Course;

public class CourseSession extends Session {
   private static int count;

   public static CourseSession create(Course course, Date startDate) {
      return new CourseSession(course, startDate);
   }

   protected CourseSession(Course course, Date startDate) {
      super(course, startDate);
      CourseSession.incrementCount();
   }

   static private void incrementCount() {
      ++count;
   }

   public static void resetCount() {
      count = 0;
   }

   public static int getCount() {
      return count;
   }

   protected int getSessionLength() {
      return 16;
   }
}