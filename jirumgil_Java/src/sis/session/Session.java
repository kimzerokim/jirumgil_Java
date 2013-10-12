package sis.session;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import sis.studentInfo.Course;
import sis.studentInfo.SessionException;
import sis.studentInfo.Student;

abstract public class Session
      implements Comparable<Session>, Iterable<Student> {
   private static int count;
   private Course course;
   private List<Student> students = new ArrayList<Student>();
   private Date startDate;
   private int numberOfCredits;
   private URL url;

   protected Session(Course course, Date startDate) {
      this.course = course;
      this.startDate = startDate;
   }

   public int compareTo(Session that) {
      int compare =
         this.getDepartment().compareTo(that.getDepartment());
      if (compare != 0)
         return compare;
      return this.getNumber().compareTo(that.getNumber());
   }

   public Iterator<Student> iterator() {
      return students.iterator();
   }

   public void setNumberOfCredits(int numberOfCredits) {
      this.numberOfCredits = numberOfCredits;
   }

   public String getDepartment() {
      return course.getDepartment();
   }

   public String getNumber() {
      return course.getNumber();
   }

   public void setUrl(String urlString) throws SessionException {
      try {
         this.url = new URL(urlString);
      }
      catch (MalformedURLException e) {
         log(e);
         throw new SessionException(e);
      }
   }

public static Student findByLastName(String lastName)
   throws RuntimeException {
   java.sql.Connection dbConnection = null;
   try {
      dbConnection = getConnection();
      return lookup(dbConnection, lastName);
   }
   catch (java.sql.SQLException e) {
      throw new RuntimeException(e.getMessage());
   }
   finally {
      close(dbConnection);
   }
}

private static java.sql.Connection getConnection()
      throws java.sql.SQLException {
   return null;
}
private static Student lookup(java.sql.Connection c, String n) {
   return null;
}
private static void close(java.sql.Connection c) {
}


   private void log(Exception e) {
    // logging code.  See [[ Chapter xx ]] for more info on logging.
    // for now, this method is empty
   }

   public URL getUrl() {
      return url;
   }

   public int getNumberOfStudents() {
      return students.size();
   }

   public void enroll(Student student) {
      student.addCredits(numberOfCredits);
      students.add(student);
   }

   public Student get(int index) {
      return students.get(index);
   }

   public Date getStartDate() {
      return startDate;
   }

   public List<Student> getAllStudents() {
      return students;
   }

   abstract protected int getSessionLength();

   public Date getEndDate() {
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.setTime(getStartDate());
      final int daysInWeek = 7;
      final int daysFromFridayToMonday = 3;
      int numberOfDays =
         getSessionLength() * daysInWeek - daysFromFridayToMonday;
      calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
      return calendar.getTime();
   }

   public double averageGpaForPartTimeStudents() {
      double total = 0.0;
      int count = 0;

      for (Iterator<Student> it = students.iterator();
           it.hasNext(); ) {
         Student student = it.next();
         if (student.isFullTime())
            continue;
         count++;
         total += student.getGpa();
      }
      if (count == 0) return 0.0;
      return total / count;
   }
}