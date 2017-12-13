/**
 * @author Larry Rodrigues
 * SWE 642 Fall 2017
 * HW5 Struts
 * 
 * Class SurveySubmitAction provides an execute method to handle for 
 * "surveysubmit" url action mapping in struts.xml.
 * 
 * This class provides getter/setters for StudentBean, DataBean, and allStudentRecs.
 * The StudentBean is exposed to the hw5_survey.jsp form where the properties of input
 * fields are mapped to the StudentBean.  The studentBean instance is passed to the STudentDao class
 * for persistence to the oracle database.  The DataBean instance dataprocBean is exposed to allow storage 
 * of the mean value data.  The bean instance values are exposed to the SimpleAckowledgement.jsp and WinnerAckowledgement.jsp 
 * files for results display.  This action also queries the StudentDAO class for all 
 * student records which are listed in the acknowledgement jsp files.
 */
package swe642.lrodri18.hw5.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import swe642.lrodri18.hw5.bean.DataBean;
import swe642.lrodri18.hw5.bean.StudentBean;
import swe642.lrodri18.hw5.dao.StudentDAO;
import swe642.lrodri18.hw5.utils.AppConst;

public class SurveySubmitAction extends ActionSupport implements AppConst {

  private static final long serialVersionUID = 1L;
  private DataBean dataprocBean;
  private StudentBean studentBean;
  private ArrayList allStudentRecs = null;

  public String execute() throws Exception {
    String retval = ERROR;
    
    if( studentBean != null ){
      System.out.println("[SurveySubmitAction] studentBean: "+studentBean.toString());
      //call Service class to store studentBean's state in database
      StudentDAO.storeStudentBean(studentBean);
    }else {
      System.out.println("[SurveySubmitAction] Student Bean is NULL.");
    }
    
    if( dataprocBean != null ){
      System.out.println("[SurveySubmitAction] dataproc: "+dataprocBean.toString());
        if (dataprocBean.getMeanValue() > 90) {
          // nextJSP = "/WEB-INF/WinnerAcknowledgement.jsp";
          retval = WINNER_SUCCESS;
        } else {
          retval = SIMPLE_SUCCESS;
          // nextJSP = "/WEB-INF/SimpleAcknowledgement.jsp";
        }
    } else {
      System.out.println("[SurveySubmitAction] dataproc: NULL");
      retval = ERROR;
    }
    allStudentRecs = StudentDAO.getAllStudentRecords();
    System.out.println("[SurveySubmitAction] execute: allStudentRecs size: " + allStudentRecs.size());
    return retval;
  }

  public DataBean getDataprocBean() {
    System.out.println("[SurveySubmitAction] getDataprocBean: " + dataprocBean );
    return dataprocBean;
  }
  
  public void setDataprocBean(DataBean data) {
     this.dataprocBean = data;
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
