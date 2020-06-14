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
    <title>Title</title>
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
    <c:when test="${sessionScope.status == 'WEBSITEADMIN' }">
<div align="center">
    <h1>Customer Registration Form</h1>
    <form action="${pageContext.request.contextPath}/home/create_supplier" method="post">
        <table style="with: 80%">
            <tr>
                <td>Company Name</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>BIN</td>
                <td><input type="text" name="bin" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/home/login_admin_button" type="button" class="btn btn-info">Login</a>
    </c:otherwise>
</c:choose>
</body>
</html>
