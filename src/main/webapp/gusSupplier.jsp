<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="myLabels"/>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>Gus</title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/login_button?theLocale=en_US" type="button" class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/login_button?theLocale=ru_RU" type="button" class="btn btn-info">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
Gus supplier page. . .


</body>
</html>
