/**
 * @author Larry Rodrigues
 * SWE 642 Fall 2017
 * HW5 Struts
 * 
 * Class TestAppAction extends ActionSupport to provides an execute method to handle for 
 * "testapp" url action mapping in struts.xml.
 * This method is simple used to verify struts functionality.
 */
package swe642.lrodri18.hw5.action;

import com.opensymphony.xwork2.ActionSupport;

public class TestAppAction extends ActionSupport {

  private String name;

  public String execute() throws Exception {
    if (name != null && !name.equals("")) {
      if ("SECRET".equals(name)) {
        return SUCCESS;
      } else {
        return ERROR;
      }
    } else {
      System.out.println("NAME IS NULL");
      return ERROR;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
