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
    <jsp:include page="backButtonRestrict.jsp"/>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>

    <title><fmt:message key="add.your.data"/></title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/add_data?theLocale=en_US" type="button" class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/add_data?theLocale=ru_RU" type="button" class="btn btn-info">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>

</div>
<%--todo--%> create if customer back to cust cab or if admin forward to admin cabinet
<div class="container-sm">
    <div class="btn-group-toggle " role="group" aria-label="Basic example">
        <a href="${pageContext.request.contextPath}/home/customerPersonalAccountPage" type="button"
           class="btn btn-info"><fmt:message key="back.to.cabinet"/></a>
    </div>
</div>
<c:choose>
    <c:when test="${sessionScope.status == 'CUSTOMER' ||sessionScope.status == 'WEBSITEADMIN' }">
        <div class="container-sm">
            <div align="center">
                <h1><fmt:message key="add.your.data"/></h1>
                <form action="${pageContext.request.contextPath}/home/add_customer_data" method="post">

                    <table style="align-items: center">
                        <tr>
                            <td><fmt:message key="month"/></td>
                            <td><input type="month" name="month"/></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="add.your.data"/></td>
                            <td><input type="number" name="Gas_data"/></td>
                        </tr>

                        <tr>
                            <td>
                                <select name="id_supplier">
                                    <option value="1"><fmt:message key="gas"/></option>
                                    <option value="2"><fmt:message key="water"/></option>
                                    <option value="3"><fmt:message key="electricity"/></option>

                                </select>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="submit"/>
                </form>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/home/login_button" type="button" class="btn btn-info"><fmt:message
                key="login"/></a>
    </c:otherwise>
</c:choose>
</body>
</html>
