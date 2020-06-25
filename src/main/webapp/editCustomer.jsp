<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="myLabels"/>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>Edit customer</title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/editCustomerButton?theLocale=en_US">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/editCustmoerButton?theLocale=ru_RU">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
<c:choose>
    <c:when test="${sessionScope.status == 'WEBSITEADMIN' }">
        <div align="center">
            <h1><fmt:message key="customer.registration.form"/></h1>
            <form action="${pageContext.request.contextPath}/home/editCustomer" method="post">
                <input type="hidden" name="id" value="${requestScope.id}">

                <table style="with: 80%">
                    <tr>
                        <td><fmt:message key="first.name"/></td>
                        <td><input type="text" name="firstName" value="${requestScope.firstName}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="surname"/></td>
                        <td><input type="text" name="surname" value="${requestScope.surname}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="email"/></td>
                        <td><input type="text" name="email" value="${requestScope.email}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="password"/></td>
                        <td><input type="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="city"/></td>
                        <td><input type="text" name="city" value="${requestScope.city}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="address"/></td>
                        <td><input type="text" name="address" value="${requestScope.address}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="IIN"/></td>
                        <td><input type="text" name="iin" value="${requestScope.iin}"/></td>
                    </tr>
                </table>
                <input type="submit" value="Submit"/>
            </form>
        </div>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/home/login_admin_button" type="button" class="btn btn-info">Administration</a>
    </c:otherwise>
</c:choose>
</body>
</html>
