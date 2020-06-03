
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="style.jsp"/>

    <title>Your cabinet</title>
</head>

<div class="container-sm">
<br>
<h3>
    This is your cabinet
${sessionScope.customer.firstName} Your email is:  ${sessionScope.customer.email}

</h3>
<br>
    <div class="btn-group-toggle " role="group" aria-label="Basic example">
    <a href="${pageContext.request.contextPath}/home/show_gas" type="button" class="btn btn-info">Show Gas data</a>
    <a href="${pageContext.request.contextPath}/home/add_gas_data" type="button" class="btn btn-info">Add Gas data</a>
    </div>

</div>
</body>
</html>
