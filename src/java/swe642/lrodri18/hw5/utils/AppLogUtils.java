package swe642.lrodri18.hw5.utils;

import java.sql.SQLException;

public class AppLogUtils {

    public static void logExceptionStackTrace(Exception e) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement elements[] = e.getStackTrace();
        try {
            sb.append(e.getClass());
            sb.append("\r\n");
            for (int i = 0; i < elements.length; i++) {
                sb.append("    at ");
                sb.append(elements[i].getClassName());
                sb.append(".");
                sb.append(elements[i].getMethodName());
                sb.append("(");
                sb.append(elements[i].getFileName());
                sb.append(":");
                sb.append(elements[i].getLineNumber());
                sb.append(")");
                sb.append("\r\n");
            }
            // logger.debug(sb);
            System.out.println(sb);
            if (e instanceof SQLException) {
                logSqlError((SQLException) e);
            }
        } catch (Exception ex) {
            ;
        }
    }

    public static void logSqlError(java.sql.SQLException se) {
        logSqlError(se, "");
    }

    public static void logSqlError(java.sql.SQLException se, String asQuery) {
        StringBuffer sb = new StringBuffer();
        //SQLException next          = se;
        int iErrorCount = 0;

        sb.append("SQL Error:\r\n");
        if (!(asQuery.equals(""))) {
            sb.append("\r\n" + asQuery + "\r\n");
        }
        while (se != null) {
            iErrorCount++;
            sb.append("\r\n" + iErrorCount);
            sb.append(se.getMessage() + "\r\n" + "Error Code: ");
            sb.append(se.getErrorCode() + "\r\n" + "SQL State: ");
            sb.append(se.getSQLState() + "\r\n");
            se = se.getNextException();
        }
        System.out.println(sb);
    }
}
