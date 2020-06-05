<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>Title</title>
</head>
<body>
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
