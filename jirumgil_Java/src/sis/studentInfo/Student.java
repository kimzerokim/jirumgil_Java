package sis.studentInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Student {
   public enum Grade {
      A(4),
      B(3),
      C(2),
      D(1),
      F(0);

      private int points;

      Grade(int points) {
         this.points = points;
      }

      int getPoints() {
         return points;
      }
   }

   public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
   public static final String IN_STATE = "CO";
   public static final int MAX_NAME_PARTS = 3;
   public static final String TOO_MANY_NAME_PARTS_MSG =
      "Student name '%s' contains more than %d parts";

   public final static Logger logger =
      Logger.getLogger(Student.class.getName());

   private String id;
   private String name;
   private String firstName = "";
   private String middleName = "";
   private String lastName;
   private int credits;
   private String state = "";
   private List<Grade> grades = new ArrayList<Grade>();
   private GradingStrategy gradingStrategy =
      new BasicGradingStrategy();
   private List<Integer> charges = new ArrayList<Integer>();

   public Student(String fullName) {
      this.name = fullName;
      credits = 0;
      List<String> nameParts = split(fullName);
      if (nameParts.size() > MAX_NAME_PARTS) {
         String message =
            String.format(Student.TOO_MANY_NAME_PARTS_MSG,
                          fullName, MAX_NAME_PARTS);
         Student.logger.info(message);
         throw new StudentNameFormatException(message);
      }
      setName(nameParts);
   }

   private List<String> split(String fullName) {
      List<String> results = new ArrayList<String>();
      for (String name: fullName.split(" "))
         results.add(name);
      return results;
   }

   private void setName(List<String> nameParts) {
      this.lastName = removeLast(nameParts);
      String name = removeLast(nameParts);
      if (nameParts.isEmpty())
         this.firstName = name;
      else {
         this.middleName = name;
         this.firstName = removeLast(nameParts);
      }
   }

   private String removeLast(List<String> list) {
      if (list.isEmpty())
         return "";
      return list.remove(list.size() - 1);
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getMiddleName() {
      return middleName;
   }

   public String getLastName() {
      return lastName;
   }

   public boolean isFullTime() {
      return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
   }

   public int getCredits() {
      return credits;
   }

   public void addCredits(int credits) {
      this.credits += credits;
   }

   public boolean isInState() {
      return state.equals(Student.IN_STATE);
   }

   public void setState(String state) {
      this.state = state;
   }

   public void addGrade(Grade grade) {
      grades.add(grade);
   }

   public double getGpa() {
      if (grades.isEmpty())
         return 0.0;
      double total = 0.0;
      for (Grade grade: grades)
         total += gradingStrategy.getGradePointsFor(grade);
      return total / grades.size();
   }

   public void setGradingStrategy(GradingStrategy gradingStrategy) {
      this.gradingStrategy = gradingStrategy;
   }

   public void addCharge(int charge) {
      charges.add(charge);
   }

   public int totalCharges() {
      int total = 0;
      for (int charge: charges)
         total += charge;
      return total;
   }
}