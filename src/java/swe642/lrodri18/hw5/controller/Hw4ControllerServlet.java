package swe642.lrodri18.hw5.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swe642.lrodri18.hw5.bean.DataBean;
import swe642.lrodri18.hw5.dao.DbConnPool;
import swe642.lrodri18.hw5.bean.StudentBean;
import swe642.lrodri18.hw5.dao.StudentDAO;

/**
 * @author Larry Rodrigues
 */
@WebServlet("/controller")
public class Hw4ControllerServlet extends HttpServlet {

  /**
   * this life-cycle method is invoked when this servlet is first accessed by the client
   */
  @Override
  public void init(ServletConfig config) {
    System.out.println("[Hw4ControllerServlet] initialization.");
    Connection c = null;
    try {
      c = DbConnPool.getConnection();
      if (c != null) {
        System.out.println("[Hw4ControllerServlet] Database Connection Pool Initialized.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DbConnPool.freeConnection(c);
    }

  }

  /**
   * handles HTTP GET request
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
    System.out.println("[Hw4ControllerServlet] doGet.");
    HttpSession session = request.getSession();
    String action = request.getParameter("action");
    String nextJSP = "/WEB-INF/hw4_survey.jsp"; // default

    System.out.println("[Hw4ControllerServlet] doGet Action: { " + action + " }");
    if (action != null && !action.trim().equals("")) {
      action = action.trim().toLowerCase();
      if (action.equals("survey-form")) {
        nextJSP = "/WEB-INF/hw4_survey.jsp";
      } else if (action.equals("getstudent")) {
        String seqId = request.getParameter("id");
        int studentSeqId = -1;
        try {
          studentSeqId = Integer.parseInt(seqId);
        } catch (Exception e) {
          e.printStackTrace();
        }

        StudentBean student = null;
        if (studentSeqId != -1) {
          student = StudentDAO.getStudentBean(studentSeqId);
        }

        if (student != null) {
          session.setAttribute("student", student);
          nextJSP = "/WEB-INF/Student.jsp";
        } else {
          nextJSP = "/WEB-INF/error.jsp";
        }
      }
    } else {
      // action is null or empty
      nextJSP = "/WEB-INF/hw4_survey.jsp";
    }

    try {
      RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * handles HTTP POST request
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
    System.out.println("[Hw4ControllerServlet] doPost.");
    HttpSession session = request.getSession();
    String action = request.getParameter("action");
    String nextJSP = "/WEB-INF/hw4_survey.jsp"; // default
    System.out.println("[Controller] Action: { " + action + " }");
    if (action != null && !action.trim().equals("")) {
      action = action.trim().toLowerCase();

      if (action.equals("survey-form")) {
        nextJSP = "/WEB_INF/hw4_survey.jsp";
      } else if (action.equals("store-survey")) {
        System.out.println("[Controller] store-student");
        int studentSeqId = -1;
        String seqId = request.getParameter("seqId");
        String studentId = request.getParameter("studentid");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String nameFirstlast = request.getParameter("namefirstlast");
        String streetAddress = request.getParameter("streetaddress");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");
        String phoneNumber = request.getParameter("phonenumber");
        String homepageUrl = request.getParameter("homepageurl");
        String dateOfSurvey = request.getParameter("surveydate");
        String hsGradMonth = request.getParameter("hsgradmonth");
        String hsGradYear = request.getParameter("gradyear");

        String[] likes = request.getParameterValues("campuslike");
        StringBuilder campusLikeData = new StringBuilder("");
        int index = 0;
        for (String like : likes) {
          index++;
          campusLikeData.append(like);
          if (index < likes.length) {
            campusLikeData.append(",");
          }
        }

        String recLikely = request.getParameter("likelyrecommend");
        String interestInd = request.getParameter("interestind");
        String comments = request.getParameter("comments");
        
        String movieId = request.getParameter("movieid");
        String movieRating = request.getParameter("movierating");
        String averageMaxData = request.getParameter("avg_max_data");
        
        DataBean dataBean = new DataBean(averageMaxData);

        if (seqId != null && !seqId.trim().equals("")) {
          try {
            studentSeqId = Integer.parseInt(seqId);
          } catch (Exception e) {
            studentSeqId = -1;
          }
        } else {
          studentSeqId = -1;
        }
        System.out.println("[Controller] Seq Id: " + seqId);
        System.out.println("[Controller] Student Seq Id: " + studentSeqId);
        System.out.println("[Controller] UserName: " + username);
        System.out.println("[Controller] campusLike: " + campusLikeData.toString());
        StudentBean student = new StudentBean();
        student.setStudentSeqId(studentSeqId);
        student.setStudentId(studentId);
        student.setUsername(username);
        student.setEmail(email);
        student.setNameFirstlast(nameFirstlast);
        student.setStreetAddress(streetAddress);
        student.setCity(city);
        student.setState(state);
        student.setZip(zipcode);
        student.setPhoneNumber(phoneNumber);
        student.setHomepageUrl(homepageUrl);
        student.setSurveyDate(dateOfSurvey);
        student.setHsGradMonth(hsGradMonth);
        student.setHsGradYear(hsGradYear);

        student.setCampusLikes(campusLikeData.toString());
        student.setRecLikely(recLikely);
        student.setInterestInd(interestInd);
        student.setComments(comments);
        
        StudentDAO.storeStudentBean(student);

        ArrayList allStudentRecs = StudentDAO.getAllStudentRecords();
        session.setAttribute("allstudents", allStudentRecs);
        session.setAttribute("dataproc", dataBean);
        if (dataBean.getMeanValue() > 90) {
          nextJSP = "/WEB-INF/WinnerAcknowledgement.jsp";
        } else {
          nextJSP = "/WEB-INF/SimpleAcknowledgement.jsp";
        }
      }
    } else {
      System.out.println("[Controller] Error with action.");
      nextJSP = "error.jsp";
    }
    try {
      RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * this life-cycle method is invoked when the application or the server is shutting down
   */
  @Override
  public void destroy() {
    System.out.println("[Hw4ControllerServlet] Servlet is being destroyed");
  }
}
