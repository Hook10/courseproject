<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>Error</title>
</head>
<body>
<div class="container-sm">
     Warning!!!
     Error <h3>${requestScope.message}</h3>

</div>

</body>
</html>
