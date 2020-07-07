<%@ page contentType="text/html; charset=UTF-8" %>
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
    <title><fmt:message key="admin.cabinet"/></title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/admin_cabinet?theLocale=en_US">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/admin_cabinet?theLocale=ru_RU">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
<div class="container-sm">
    <c:choose>
        <c:when test="${sessionScope.status == 'WEBSITEADMIN'}">
            <h3>
                <fmt:message key="admin.cabinet.message"/>
                    ${sessionScope.admin.login}
            </h3>
            <a href="${pageContext.request.contextPath}/home/create_admin_button" type="button"
               class="btn btn-secondary"> <fmt:message key="create.admin"/></a>
            <a href="${pageContext.request.contextPath}/home/create_supplier_button" type="button"
               class="btn btn-secondary"><fmt:message key="create.supplier"/></a>
            <a href="${pageContext.request.contextPath}/home/show_all_customers" type="button" class="btn btn-info">
                <fmt:message key="show.all.customers"/></a>
            <a href="${pageContext.request.contextPath}/home/show_all_suppliers" type="button" class="btn btn-info">
                <fmt:message key="show.all.suppliers"/></a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/home/login_admin_button" type="button"
               class="btn btn-info"><fmt:message key="administration"/></a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
