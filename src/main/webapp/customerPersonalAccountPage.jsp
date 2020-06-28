<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="myLabels"/>
<html lang="${theLocale}">
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>

    <title>Your cabinet</title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/customerPersonalAccountPage?theLocale=en_US" type="button" class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/customerPersonalAccountPage?theLocale=ru_RU" type="button" class="btn btn-info">Русский (RU)</a>

    Selected language: ${theLocale}

</div>
<c:choose>
    <c:when test="${sessionScope.status == 'CUSTOMER' ||sessionScope.status == 'WEBSITEADMIN' }">
        <div class="container-sm">
            <br>
            <h3>
                <fmt:message key="hello"/>
                <fmt:message key="This.is.your.cabinet"/>
                    ${sessionScope.customer.firstName} <br> <fmt:message
                    key="your.email.is"/>: ${sessionScope.customer.email}

            </h3>
            <br>
            <div class="btn-group-toggle " role="group" aria-label="Basic example">
                <a href="${pageContext.request.contextPath}/home/show_gas" type="button"
                   class="btn btn-info"><fmt:message key="show.gas.data"/></a>
                <a href="${pageContext.request.contextPath}/home/show_water" type="button"
                   class="btn btn-info"><fmt:message key="show.water.data"/></a>
                <a href="${pageContext.request.contextPath}/home/show_electricity" type="button" class="btn btn-info">
                    <fmt:message key="show.electricity.data"/></a>
            </div>
            <br>
            <a href="${pageContext.request.contextPath}/home/add_data" type="button" class="btn btn-info"><fmt:message
                    key="add.your.data"/></a>
        </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container-sm">
            <a href="${pageContext.request.contextPath}/home/login_button" type="button"
               class="btn btn-info"><fmt:message key="login"/></a>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
