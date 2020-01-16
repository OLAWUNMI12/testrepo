<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mason
  Date: 12/22/2019
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>

    </style>
    <title>Title</title>
    <spring:url value="/resources/CSS/HomeStyle.css" var="HomeStyle"/>
    <link href="${HomeStyle}" rel="stylesheet" type="text/css">
</head>
<body>
<p>SPRING FRAMEWORK PRACTICE WITH RESOURCES</p>
<form:form action="/retrieve" modelAttribute="student" method="get">
    Username <form:input path="username"/><br><br>
    password <form:input path="password"/><br><br>
    <input type="submit" value="submit">
</form:form>
</body>
</html>
