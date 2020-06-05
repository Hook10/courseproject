<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>Create Admin</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.status == 'WEBSITEADMIN' }">
<div align="center">
    <h1>Customer Registration Form</h1>
    <form action="${pageContext.request.contextPath}/home/create_admin" method="post">
        <table style="with: 80%">
            <tr>
                <td>Login</td>
                <td><input type="text" name="login" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>Supplier Id</td>
                <td><input type="text" name="supplier_id" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" name="email" /></td>
            </tr>
            <tr>
                <td>Company name</td>
                <td><input type="text" name="company_name" /></td>
            </tr>



        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/home/login_admin_button" type="button" class="btn btn-info">Administration</a>
    </c:otherwise>
</c:choose>
</body>
</html>
