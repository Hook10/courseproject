<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="language"/>
<html lang="en">

<head>
    <jsp:include page="style.jsp"/>
    <title>Home Page</title>
</head>

<html>
<div>
    <body>
    <h2>Hello! Welcome to  </h2>
    <a href="login">Login</a>
    <br>
    <br>
    <a href="register">Registration</a>
    <br>
    <br>
    <a href="profile.jsp">profile</a>
    </body>
</div>

</html>
