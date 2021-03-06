<%@ page contentType="text/html; charset=UTF-8"  %>
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
    <title><fmt:message key="create.supplier"/></title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/create_supplier_button?theLocale=en_US" type="button"
       class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/create_supplier_button?theLocale=ru_RU" type="button"
       class="btn btn-info">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
<c:choose>
    <c:when test="${sessionScope.status == 'WEBSITEADMIN' }">
        <div align="center">
            <h1><fmt:message key="supplier.edit.form.message"/></h1>
            <form action="${pageContext.request.contextPath}/home/editSupplier" method="post">
                <input type="hidden" name="id" value="${requestScope.id}">
                <table style="with: 80%">
                    <tr>
                        <td><fmt:message key="company.name"/></td>
                        <td><input type="text" name="companyName" value="${requestScope.companyName}" required="true"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="BIN"/></td>
                        <td><input type="text" name="bin" value="${requestScope.bin}" required="true"/></td>
                    </tr>
                </table>
                <input type="submit" value="Submit"/>
            </form>
        </div>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/home/login_admin_button" type="button"
           class="btn btn-info"><fmt:message key="login"/></a>
    </c:otherwise>
</c:choose>
</body>
</html>
