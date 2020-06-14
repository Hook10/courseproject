<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}"
       scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="myLabels" />
<html>


<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>

    <title>Create data</title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/login_button?theLocale=en_US">English (US)</a>  |
    <a href="${pageContext.request.contextPath}/home/login_button?theLocale=ru_RU">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
<c:choose>
    <c:when test="${sessionScope.status == 'CUSTOMER' ||sessionScope.status == 'WEBSITEADMIN' }">
<div class="container-sm">
<div align="center">
    <h1>Enter Your Data Here</h1>
    <form action="${pageContext.request.contextPath}/home/add_customer_data"  method="post">

        <table style="align-items: center">
            <tr>
                <td>month</td>
                <td><input type="month" name="month" /></td>
            </tr>
            <tr>
                <td>Add Data </td>
                <td><input type="number" name="Gas_data" /></td>
            </tr>

            <tr>
                <td>
                    <select name="id_supplier">
                        <option value="1">Gas</option>
                        <option value="2">Water</option>
                        <option value="3">Electricity</option>

                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</div>
    </c:when>
<c:otherwise>
    <a href="${pageContext.request.contextPath}/home/login_button" type="button" class="btn btn-info">Login</a>
</c:otherwise>
</c:choose>
</body>
</html>
