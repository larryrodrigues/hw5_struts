<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Survey Acknowledgement Page</title>
    </head>
    <body>
        <h1 align="center">Survey Form Submission Complete</h1>
        <p>Thank you for completing the GMU computer science student survey form. Your input has been successfully saved to our database.</p>
        <p>Please <a href="${pageContext.request.contextPath}/controller">click here</a> to complete the survey again.</p>
        <table class="dataproc_table" align="center" width="100%">
            <tr><th align="center">Mean Value of Data</th><th align="center">Standard Deviation of Data</th></tr>
            <tr><td align="center">${dataproc.meanValue}</td><td align="center">${dataproc.standardDeviation}</td></tr>
            <tr><td align="center">Data Set Values:</td><td align="center">${dataproc.dataFieldList}</td></tr>
        </table>
        <table style="border: 1px solid;" width="100%" align="center">
            <c:forEach var="student" items="${allstudents}">
                <tr>
                    <td><a href = "<c:url value = "/controller?id=${student.studentSeqId}&action=getstudent"/>">${student.studentId}</a></td>
                    <td>${student.username}</td>
                    <td>${student.email}</td>
                    <td>${student.nameFirstlast}</td>
                    <td>${student.streetAddress}</td>
                    <td>${student.city}</td>
                    <td>${student.state}</td>
                    <td>${student.zip}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
