
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="style.jsp"/>
    <title>Your cabinet</title>
</head>
<body>
<h3>
    This is your cabinet
${sessionScope.customer.firstName} ${sessionScope.customer.email}

</h3>
Welcome to your personal online cabinet.
</body>
</html>
