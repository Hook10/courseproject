<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="language"/>
<html lang="en">
<head>
    <jsp:include page="style.jsp"/>


    <title>Main Page</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/home/login_button" type="button" class="btn btn-secondary">Login</a>
<a href="${pageContext.request.contextPath}/home/register_button" type="button" class="btn btn-secondary">Registration</a>
<a href="${pageContext.request.contextPath}/home/login_admin_button" type="button" class="btn btn-secondary">Administration</a>

</body>
</html>













<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%--<html lang="en">--%>

<%--<head>--%>
<%--    <jsp:include page="style.jsp"/>--%>
<%--    <title>Home Page</title>--%>
<%--</head>--%>

<%--<html>--%>
<%--<div>--%>
<%--    <body>--%>
<%--    <h2>Hello! Welcome to  </h2>--%>
<%--    <a href="${pageContext.request.contextPath}/home/login_button" type="button" class="btn btn-secondary">Вход</a>--%>
<%--     <br>--%>
<%--    <br>--%>
<%--    <a href="register">Registration</a>--%>
<%--    <br>--%>
<%--    <br>--%>
<%--    <a href="login">asdfasdf</a>--%>
<%--    <br>--%>
<%--    <br>--%>
<%--    <a href="profile.jsp">profile</a>--%>
<%--    </body>--%>
<%--</div>--%>

<%--</html>--%>
