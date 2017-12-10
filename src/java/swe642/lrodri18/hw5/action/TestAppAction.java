package swe642.lrodri18.hw5.action;

/**
 * @author Larry Rodrigues
 */
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
