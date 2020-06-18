<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}"
       scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="myLabels" />
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <meta charset="UTF-8">
    <title><fmt:message key="customer.registration.form"/></title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/register_button?theLocale=en_US">English (US)</a>  |
    <a href="${pageContext.request.contextPath}/home/register_button?theLocale=ru_RU">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
<div align="center">
    <h1><fmt:message key="customer.registration.form"/></h1>
    <form action="${pageContext.request.contextPath}/home/register" method="post">
        <table style="with: 80%">
            <tr>
                <td><fmt:message key="first.name"/></td>
                <td><input type="text" name="firstName" /></td>
            </tr>
            <tr>
                <td><fmt:message key="surname"/></td>
                <td><input type="text" name="surname" /></td>
            </tr>
            <tr>
                <td><fmt:message key="email"/></td>
                <td><input type="text" name="email" /></td>
            </tr>
            <tr>
                <td><fmt:message key="password"/></td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td><fmt:message key="city"/></td>
                <td><input type="text" name="city" /></td>
            </tr>
            <tr>
                <td><fmt:message key="address"/></td>
                <td><input type="text" name="address" /></td>
            </tr>
            <tr>
                <td><fmt:message key="IIN"/></td>
                <td><input type="text" name="iin" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>