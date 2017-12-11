<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>George Mason University Department of Computer Science Prospective Student Survey</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lrhw4_styles.css"/>
    <body>
        <h2 class="top_header">Prospective Student Survey Values</h2>
        <table align="center" width="100%">
            <tr>
                <td align="left">Username:</td>
                <td align="left">Email:</td>
                <td align="left">Name (First and Last):</td>
            </tr>
            <tr>
                <td align="left"><s:property value="curStudent.username"/></td>
                <td align="left"><s:property value="curStudent.email"/></td>
                <td align="left"><s:property value="curStudent.nameFirstlast"/></td>
            </tr>
            <tr>
                <td align="left">Street Address:</td>
                <td align="left">City:</td>
                <td align="left">State:</td>
            </tr>
            <tr>
                <td align="left"><s:property value="curStudent.streetAddress"/></td>
                <td align="left"><s:property value="curStudent.city"/></td>
                <td align="left"><s:property value="curStudent.state"/></td>
            </tr>
            <tr>
                <td align="left">Zip:</td>
                <td align="left">Phone Number:</td>
                <td align="left">Home Page:</td>
            </tr>
            <tr>
                <td align="left"><s:property value="curStudent.zip"/></td>
                <td align="left"><s:property value="curStudent.phoneNumber"/></td>
                <td align="left"><s:property value="curStudent.homepageUrl"/></td>
            </tr>
            <tr>
                <td align="left">Date of Survey:</td>
                <td align="left">High school graduation month:</td>
                <td align="left">High school graduation year:</td>
            </tr>
            <tr>
                <td align="left"><s:property value="curStudent.surveyDate"/></td>
                <td align="left"><s:property value="curStudent.hsGradMonth"/></td>
                <td align="left"><s:property value="curStudent.hsGradYear"/></td>
            </tr>
            <tr>
                <td align="left">Like Best:</td>
                <td align="left">Point of Interest:</td>
                <td align="left">Recommendation Likely:</td>
            </tr>
            <tr>
                <td align="left"><s:property value="curStudent.campusLikes"/></td>
                <td align="left"><s:property value="curStudent.interestInd"/></td>
                <td align="left"><s:property value="curStudent.recLikely"/></td>
            </tr>
            <tr>
                <td align="left">Comments:</td>
            </tr>
            <tr>
                <td align="left"><p><s:property value="curStudent.comments"/></p></td>
            </tr>
        </table>
    </body>
</html>
