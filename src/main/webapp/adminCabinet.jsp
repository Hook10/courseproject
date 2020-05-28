
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="style.jsp"/>
    <title>AdminCabinet</title>
</head>
<body>
<h3>
    This is your cabinet
   ${sessionScope.admin.login} ${sessionScope.admin.email}
</h3>
    <a href="${pageContext.request.contextPath}/home/create_admin_button" type="button" class="btn btn-secondary">Create admin</a>

</body>
</html>
