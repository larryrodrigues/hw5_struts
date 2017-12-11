package swe642.lrodri18.hw5.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import swe642.lrodri18.hw5.bean.DataBean;
import swe642.lrodri18.hw5.bean.StudentBean;
import swe642.lrodri18.hw5.dao.StudentDAO;

public class SurveySubmitAction extends ActionSupport {

  private static final long serialVersionUID = 1L;
  private DataBean dataproc;
  private StudentBean studentBean;
  private ArrayList allStudentRecs = null;

  public String execute() throws Exception {
    
    if( studentBean != null ){
      System.out.println("[SurveySubmitAction] studentBean: "+studentBean.toString());
      //call Service class to store studentBean's state in database
      StudentDAO.storeStudentBean(studentBean);
    }else {
      System.out.println("[SurveySubmitAction] Student Bean is NULL.");
    }
    allStudentRecs = StudentDAO.getAllStudentRecords();
    System.out.println("[SurveySubmitAction] execute: allStudentRecs size: " + allStudentRecs.size());
    return SUCCESS;
  }

  public DataBean getDataproc() {
    return dataproc;
  }
  
  public StudentBean getStudentBean() {
    return studentBean;
  }

  public void setStudentBean(StudentBean student) {
    studentBean = student;
  }

  public ArrayList getAllStudentRecs() {
    return allStudentRecs;
  }
}
