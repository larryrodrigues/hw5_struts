package swe642.lrodri18.hw5.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Larry Rodrigues
 */
public class DbConnPool {

  private static DataSource dataSource;
  private static String dbJndiName = "jdbc/ApolloDB";

  static {
    if (dataSource == null) {
      try {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup(dbJndiName);
        if (ds != null) {
          System.out.println("Data Source ds has been initialized...");
        }
        dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + dbJndiName);
        if (dataSource != null) {
          System.out.println("Data Source dataSource has been initialized...");
        }
      } catch (NamingException e) {
        // Handle error that it's not configured in JNDI.
        throw new IllegalStateException(dbJndiName + " is missing in JNDI!", e);
      } catch (Exception e) {
        System.out.println("Excpeption trying to connect to JNDI ds");
        e.printStackTrace();
      }
    }
  }

  public DbConnPool(String jndiname) {
    try {
      dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + jndiname);
    } catch (NamingException e) {
      // Handle error that it's not configured in JNDI.
      throw new IllegalStateException(jndiname + " is missing in JNDI!", e);
    }
  }

  public static Connection getConnection() {
    Connection conn = null;
    try {
      conn = dataSource.getConnection();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }

  public static void freeConnection(java.sql.Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException sqle) {
        System.out.println("DbConnPool.freeConnection SQLException Error");
        System.out.println(sqle);
      } catch (Exception e) {
        System.out.println("DbConnPool.freeConnection Exception Error");
        System.out.println(e);
      }
    }
  }
}
