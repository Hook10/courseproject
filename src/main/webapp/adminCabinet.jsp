<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>AdminCabinet</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.status == 'WEBSITEADMIN'}">
<h3>
    This is your cabinet
   ${sessionScope.admin.login}
</h3>

    <a href="${pageContext.request.contextPath}/home/create_admin_button" type="button" class="btn btn-secondary">Create admin</a>
    <a href="${pageContext.request.contextPath}/home/create_supplier_button" type="button" class="btn btn-secondary">Create supplier</a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/home/login_admin_button" type="button" class="btn btn-info">Administration</a>
    </c:otherwise>
</c:choose>
</body>
</html>
