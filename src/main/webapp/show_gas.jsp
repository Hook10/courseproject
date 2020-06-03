<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="style.jsp"/>
    <title>Gas page</title>
</head>
<body>
<div class="container-sm">
<h3>
    This is your Gas page
</h3>
    <br>

<table class = "table table-info">
    <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">month</th>
            <th scope="col">data</th>
            <th scope="col">ID_customer</th>
            <th scope="col">ID_supplier</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.dataList}" var="dataList">
    <tr>
        <td>${dataList.id}</td>
        <td>${dataList.month}</td>
        <td>${dataList.data}</td>
        <td>${dataList.idCustomer}</td>
        <td>${dataList.idSupplier}</td>
    </tr>
    </c:forEach>
    </tbody>

</table>
</div>
</body>
</html>
