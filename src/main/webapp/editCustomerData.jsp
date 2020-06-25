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
    <title><fmt:message key="edit.data"/></title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/editCustomerDataButton?theLocale=en_US">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/editCustomerDataButton?theLocale=ru_RU">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
<c:choose>
    <c:when test="${sessionScope.status == 'CUSTOMER' || sessionScope.status == 'WEBSITEADMIN' }">
        <div class="container-lg" align="center">

            <h3><fmt:message key="edit.data"/></h3>
            <br>
            <br>

        </div>

        <div class="container-lg">
            <div class="d-flex justify-content-center h-100">
                <div class="row">
                    <div class="col-sm">

                        <form action="${pageContext.request.contextPath}/home/editCustomerData" method="post">

                            <input type="hidden" name="id" value="${requestScope.id}">
                            <input type="hidden" name="id_customer" value="${requestScope.id_customer}">
                            <table style="align-items: normal">
                                <tr>
                                    <td><fmt:message key="month"/></td>
                                    <td><input type="month" name="month"/></td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="add.your.data"/></td>
                                    <td><input type="number" name="data"/></td>
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
                            <input type="submit" value="Submit"/>
                        </form>

                    </div>

                </div>

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
