<%-- 
    Document   : error
    Created on : Nov 21, 2017, 7:27:09 PM
    Author     : Larry Rodrigues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Survey Error Page</title>
    </head>
    <body>
        <h1>Survey Form Submission Error</h1>
        <p>There was an error processing the GMU survey form.</p>
        <p>Please <a href="${pageContext.request.contextPath}/controller">click here</a> to complete the survey again.</p>
    </body>
</html>
