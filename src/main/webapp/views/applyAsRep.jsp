<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Mason
  Date: 12/24/2019
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${courses}
<form action="/applyAsRep" method="post">
    course title <input type="text" name="title"/>
    <input type="submit" value="submit">
</form>
</body>
</html>
