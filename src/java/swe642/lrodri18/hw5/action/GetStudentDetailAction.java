package swe642.lrodri18.hw5.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import swe642.lrodri18.hw5.bean.StudentBean;
import swe642.lrodri18.hw5.dao.StudentDAO;

/**
 * @author Larry Rodrigues
 */
public class GetStudentDetailAction extends ActionSupport {
  private StudentBean curStudent = null;
  
  public String execute() throws Exception {
        String retval = null;
        String seqId = ServletActionContext.getRequest().getParameter("id");
        // String seqId = request.getParameter("id");
        int studentSeqId = -1;
        try {
          studentSeqId = Integer.parseInt(seqId);
        } catch (Exception e) {
          e.printStackTrace();
        }

        if (studentSeqId != -1) {
          curStudent = StudentDAO.getStudentBean(studentSeqId);
        }

        if (curStudent != null) {
          //session.setAttribute("student", student);
          //nextJSP = "/WEB-INF/Student.jsp";
          retval = SUCCESS;
        } else {
          //nextJSP = "/WEB-INF/error.jsp";
          retval = ERROR;
        }
    return retval;
  }

  public StudentBean getCurStudent() {
    return curStudent;
  }

  public void setCurStudent(StudentBean curStudent) {
    this.curStudent = curStudent;
  }
  

}
