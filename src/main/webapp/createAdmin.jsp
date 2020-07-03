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
    <jsp:include page="backButtonRestrict.jsp"/>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title><fmt:message key="create.admin"/></title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/create_admin_button?theLocale=en_US" type="button" class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/create_admin_button?theLocale=ru_RU" type="button" class="btn btn-info">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
</div>
<div class="container-sm">
    <div class="btn-group-toggle " role="group" aria-label="Basic example">
        <a href="${pageContext.request.contextPath}/home/admin_cabinet" type="button"
           class="btn btn-info"><fmt:message key="back.to.cabinet"/></a>
    </div>
</div>

<c:choose>
    <c:when test="${sessionScope.status == 'WEBSITEADMIN' }">
        <div align="center">
            <h1><fmt:message key="customer.registration.form"/></h1>
            <form action="${pageContext.request.contextPath}/home/create_admin" method="post">
                <table style="with: 80%">
                    <tr>
                        <td><fmt:message key="login"/></td>
                        <td><input type="text" name="login" required="true"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="password"/></td>
                        <td><input type="password" name="password" required="true"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="ID_supplier"/></td>
                        <td><input type="text" name="supplier_id" required="true"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="email"/></td>
                        <td><input type="text" name="email" required="true"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="company.name"/></td>
                        <td><input type="text" name="company_name" required="true"/></td>
                    </tr>
                </table>
                <input type="submit" value="Submit"/>
            </form>
        </div>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/home/login_admin_button" type="button" class="btn btn-info"><fmt:message key="administration"/></a>
    </c:otherwise>
</c:choose>
</body>
</html>
