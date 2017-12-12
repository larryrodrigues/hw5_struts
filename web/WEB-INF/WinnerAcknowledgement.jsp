<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Survey Acknowledgement Page</title>
    </head>
    <body>
        <h1 align="center">Congratulations Winner!!!!!!!!!!</h1>
        <p>Thank you for completing the GMU computer science student survey form. Your input has been successfully saved to our database.</p>
        <p>Congratulations. You are a raffle winner of two movie tickets. </p>
        <p>Please <a href="${pageContext.request.contextPath}/controller">click here</a> to complete the survey again.</p>
        <table class="dataproc_table" align="center" width="100%">
            <tr><th align="center">Mean Value of Data</th><th align="center">Standard Deviation of Data</th></tr>
            <tr><td align="center">${dataprocBean.meanValue}</td><td align="center">${dataprocBean.standardDeviation}</td></tr>
            <tr><td align="center">Data Set Values:</td><td align="center">${dataprocBean.dataFieldList}</td></tr>
        </table>
        <table style="border: 1px solid;" width="100%" align="center">
            <s:iterator value="allStudentRecs">
                <s:url value="/studentdetail?id=%{studentSeqId}" var="detail" />
                <tr>
                    <td><s:a href="%{detail}"><s:property value="studentId"/></s:a></td>
                    <td><s:property value="username"/></td>
                    <td><s:property value="email"/></td>
                    <td><s:property value="nameFirstlast"/></td>
                    <td><s:property value="streetAddress"/></td>
                    <td><s:property value="city"/></td>
                    <td><s:property value="state"/></td>
                    <td><s:property value="zip"/></td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
