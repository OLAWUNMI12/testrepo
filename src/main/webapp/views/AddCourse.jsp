<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Mason
  Date: 12/24/2019
  Time: 1:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/addCourses" modelAttribute="course" method="Post">
    <label>Course name</label> <form:input path="course_name"/><br><br>
    <label>Course title</label><form:input path="course_title"/><br><br>
    <input type="submit" name="submit" id="">
</form:form>
</body>
</html>
