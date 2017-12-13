/**
 * @author Larry Rodrigues
 * SWE 642 Fall 2017
 * HW5 Struts
 * 
 * Class StudentDAO is used to execute database interactivity 
 * for student records.
 * The class uses AppLogUTils to provide better stacktrace information
 * from SQLException instances.
 */
package swe642.lrodri18.hw5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import swe642.lrodri18.hw5.bean.StudentBean;
import swe642.lrodri18.hw5.utils.AppLogUtils;

public class StudentDAO {

  private static synchronized int getNextId() {
    int retval = -1;

    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    boolean useOracle = true;

    try {
      c = DbConnPool.getConnection();
      if (useOracle) {
        ps = c.prepareStatement("select STUDENT_SEQ.NEXTVAL from dual");
        rs = ps.executeQuery();
        if (rs.next()) {
          retval = rs.getInt(1);
        } else {
          retval = -1;
        }
      } else {
        ps = c.prepareStatement("insert into student_seq (seq_gen_txt) values ('nextval');");
        ps.executeUpdate();
        ps.close();

        ps = c.prepareStatement("select max(nextval) from student_seq ");
        rs = ps.executeQuery();
        if (rs.next()) {
          retval = rs.getInt(1);
        } else {
          retval = -1;
        }
      }
      rs.close();
      ps.close();
    } catch (SQLException sqle) {
      AppLogUtils.logSqlError(sqle);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (Exception e) {;
        }
      }
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {;
        }
      }
      if (c != null) {
        DbConnPool.freeConnection(c);
      }
    }

    return retval;
  }

  public static StudentBean getStudentBean(int studentSeqId) {
    StudentBean o = null;

    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      c = DbConnPool.getConnection();
      ps = c.prepareStatement("SELECT student_seq_id, student_id, username, email, name_firstlast, street_address, city, state, zip, phone_number, homepage, survey_date, hs_grad_year, hs_grad_month, liked_best, interest_ind,  rec_likely, comments FROM student WHERE student_seq_id = ?");
      ps.setInt(1,studentSeqId);
      rs = ps.executeQuery();
      if (rs.next()) {
        o = new StudentBean();
        o.setStudentSeqId(rs.getInt("student_seq_id"));
        o.setStudentId(rs.getString("student_id"));
        o.setUsername(rs.getString("username"));
        o.setEmail(rs.getString("email"));
        o.setNameFirstlast(rs.getString("name_firstlast"));
        o.setStreetAddress(rs.getString("street_address"));
        o.setCity(rs.getString("city"));
        o.setState(rs.getString("state"));
        o.setZip(rs.getString("zip"));
        o.setPhoneNumber(rs.getString("phone_number"));
        o.setHomepageUrl(rs.getString("homepage"));
        o.setSurveyDate(rs.getString("survey_date"));
        o.setHsGradYear(rs.getString("hs_grad_year"));
        o.setHsGradYear(rs.getString("hs_grad_month"));
        o.setCampusLikesFromStr(rs.getString("liked_best"));
        o.setInterestInd(rs.getString("interest_ind"));
        o.setRecLikely(rs.getString("rec_likely"));
        o.setComments(rs.getString("comments"));        
      }
    } catch (SQLException sqle) {
      AppLogUtils.logSqlError(sqle);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (Exception e) {;
        }
      }
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {;
        }
      }
      if (c != null) {
        DbConnPool.freeConnection(c);
      }
    }
    
    return o;
  }

  public static ArrayList<StudentBean> getAllStudentRecords() {
    ArrayList<StudentBean> list = new ArrayList<StudentBean>();
    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      c = DbConnPool.getConnection();
      ps = c.prepareStatement("SELECT student_seq_id, student_id, username, email, name_firstlast, street_address, city, state, zip, phone_number, homepage, survey_date, hs_grad_year, hs_grad_month, liked_best, interest_ind,  rec_likely, comments FROM student ORDER BY student_seq_id ASC");
      rs = ps.executeQuery();
      while (rs.next()) {
        StudentBean o = new StudentBean();
        o.setStudentSeqId(rs.getInt("student_seq_id"));
        o.setStudentId(rs.getString("student_id"));
        o.setUsername(rs.getString("username"));
        o.setEmail(rs.getString("email"));
        o.setNameFirstlast(rs.getString("name_firstlast"));
        o.setStreetAddress(rs.getString("street_address"));
        o.setCity(rs.getString("city"));
        o.setState(rs.getString("state"));
        o.setZip(rs.getString("zip"));
        o.setPhoneNumber(rs.getString("phone_number"));
        o.setHomepageUrl(rs.getString("homepage"));
        o.setSurveyDate(rs.getString("survey_date"));
        o.setHsGradYear(rs.getString("hs_grad_year"));
        o.setHsGradYear(rs.getString("hs_grad_month"));
        o.setCampusLikesFromStr(rs.getString("liked_best"));
        o.setInterestInd(rs.getString("interest_ind"));
        o.setRecLikely(rs.getString("rec_likely"));
        o.setComments(rs.getString("comments"));
        
        list.add(o);
      }
    } catch (SQLException sqle) {
      AppLogUtils.logSqlError(sqle);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (Exception e) {;
        }
      }
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {;
        }
      }
      if (c != null) {
        DbConnPool.freeConnection(c);
      }
    }

    return list;
  }

  public static boolean storeStudentBean(StudentBean newData) {
    boolean retval = false;

    if (newData != null) {
      System.out.println("[] Data To Store : " + newData.toString());
      Connection c = null;
      PreparedStatement ps = null;
      try {
        c = DbConnPool.getConnection();
        if (newData.getStudentSeqId() >= 0) {
          // Update record
        } else {
          // new record
          int newId = getNextId();
          //                                                       1        2           3       4     5                 6             7     8       9   10             11         12            13           14              15        16            17                                         
          System.out.println("[] New Seq Id : " + newId);
          ps = c.prepareStatement("INSERT INTO student (student_seq_id, student_id, username, email, name_firstlast, street_address, city, state, zip, phone_number, homepage, survey_date, hs_grad_year, hs_grad_month, liked_best, interest_ind,  rec_likely, comments ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
          ps.setInt(1, newId);
          ps.setString(2, newData.getStudentId());
          ps.setString(3, newData.getUsername());
          ps.setString(4, newData.getEmail());
          ps.setString(5, newData.getNameFirstlast());
          ps.setString(6, newData.getStreetAddress());
          ps.setString(7, newData.getCity());
          ps.setString(8, newData.getState());
          ps.setString(9, newData.getZip());
          ps.setString(10, newData.getPhoneNumber());
          ps.setString(11, newData.getHomepageUrl());
          ps.setString(12, newData.getSurveyDate());
          ps.setString(13, newData.getHsGradYear());
          ps.setString(14, newData.getHsGradMonth());
          ps.setString(15, newData.getCampusLikesAsStr());
          ps.setString(16, newData.getInterestInd());
          ps.setString(17, newData.getRecLikely());
          ps.setString(18, newData.getComments());
          ps.executeUpdate();
          ps.close();
        }
      } catch (SQLException sqle) {
        AppLogUtils.logSqlError(sqle);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (ps != null) {
          try {
            ps.close();
          } catch (Exception e) {;
          }
        }
        if (c != null) {
          DbConnPool.freeConnection(c);
        }
      }

    } else {
      System.out.println("[StudentDAO] Null Data Passed to Store.");
    }

    return retval;
  }
}
