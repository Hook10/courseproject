<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="myLabels" />

<html lang="${theLocale}">

<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>

    <title>Main Page</title>
</head>

<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/index?theLocale=en_US">English (US)</a>  |
    <a href="${pageContext.request.contextPath}/home/index?theLocale=ru_RU">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
<c:choose>
<c:when test="${sessionScope.status == 'GUEST' || sessionScope.status == null}">
<div class="container-sm">


<a href="${pageContext.request.contextPath}/home/login_button" type="button" class="btn btn-info"><fmt:message key="login"/></a>
<a href="${pageContext.request.contextPath}/home/register_button" type="button" class="btn btn-info"><fmt:message key="registration"/></a>
<a href="${pageContext.request.contextPath}/home/login_admin_button" type="button" class="btn btn-info"><fmt:message key="administration"/></a>
    </c:when>
    <c:otherwise>
    <div class="container-sm">
    <a href="${pageContext.request.contextPath}/home/customerPersonalAccountPage" type="button" class="btn btn-info"><fmt:message key="personal.cabinet"/></a>
    </div>
        </c:otherwise>
</c:choose>
</div>
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
