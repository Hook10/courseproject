<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}"
       scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="src.main.resources.myLabels.properties" />
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>Login Page</title>


</head>
<body>
View this page in: <br/>
<a href="login.jsp?theLocale=en_US">English (US)</a>  |
<a href="login.jsp?theLocale=ru_RU">Русский (RU)</a>
<br/><br/>
Selected language: ${theLocale}
<hr>
<br/><br/>


<div class="container-lg">
    <div class="d-flex justify-content-center h-100">
        <div class="card-title">
            <div class="col-lg">
                <form action="${pageContext.request.contextPath}/home/login" method="post">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" name="password">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>


<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
<%--         pageEncoding="ISO-8859-1"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>

<%--<div>--%>
<%--<jsp:include page="navbar.jsp"/>--%>
<%--<head>--%>

<%--    <jsp:include page="style.jsp"/>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
<%--    <title>Customer Login Form</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form action="${pageContext.request.contextPath}/home/login" method="post">--%>
<%--    <table style="with: 50%">--%>

<%--        <tr>--%>
<%--            <td>Email</td>--%>
<%--            <td><input type="text" name="email" /></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Password</td>--%>
<%--            <td><input type="password" name="password" /></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--    <input type="submit" value="Login" /></form>--%>

<%--</body>--%>

<%--</html>--%>