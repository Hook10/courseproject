<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    <title><fmt:message key="pay.page"/></title>
</head>
<body>
<div class="container-lg">

    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/payInvoiceButton?theLocale=en_US " type="button"
       class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/payInvoiceButton?theLocale=ru_RU" type="button"
       class="btn btn-info">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>

</div>
<div class="container-sm">
    <div class="btn-group-toggle " role="group" aria-label="Basic example">
        <a href="${pageContext.request.contextPath}/home/customerPersonalAccountPage" type="button"
           class="btn btn-info"><fmt:message key="back.to.cabinet"/></a>
    </div>
</div>
<c:choose>
    <c:when test="${sessionScope.status == 'CUSTOMER' ||sessionScope.status == 'WEBSITEADMIN' }">

        <div class="container-sm">
            <h3>
                <fmt:message key="payment.received.message"/>
            </h3>

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
