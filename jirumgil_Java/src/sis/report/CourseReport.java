package sis.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sis.session.Session;

public class CourseReport {
   private List<Session> sessions =
      new ArrayList<Session>();

   public void add(Session session) {
      sessions.add(session);
   }

   public String text() {
      Collections.sort(sessions);
      StringBuilder builder = new StringBuilder();
      for (Session session: sessions)
         builder.append(
            String.format("%s %s%n",
               session.getDepartment(), session.getNumber()));
      return builder.toString();
   }
}