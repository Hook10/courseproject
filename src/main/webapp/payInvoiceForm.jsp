<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}"
       scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="myLabels" />
<html lang="${theLocale}">
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title><fmt:message key="pay.page"/></title>
</head>
<body>
<div class="container-sm">
<h3>
    <fmt:message key="payment.received.message"/>
</h3>

</div>
</body>
</html>
