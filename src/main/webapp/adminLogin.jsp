<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}"
       scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="myLabels" />
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title><fmt:message key="admin.login.page"/></title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/adminLogin?theLocale=en_US">English (US)</a>  |
    <a href="${pageContext.request.contextPath}/home/adminLogin?theLocale=ru_RU">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
<form action="${pageContext.request.contextPath}/home/adminLogin" method="post">
    <table style="with: 50%">

        <tr>
            <td><fmt:message key="login"/></td>
            <td><input type="text" name="login" /></td>
        </tr>
        <tr>
            <td><fmt:message key="password"/></td>
            <td><input type="password" name="password" /></td>
        </tr>
    </table>
    <input type="submit" value="Login" /></form>
</body>
</html>
