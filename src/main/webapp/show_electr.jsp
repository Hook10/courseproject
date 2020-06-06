<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>Electricity page</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.status == 'CUSTOMER' ||sessionScope.status == 'WEBSITEADMIN' }">
<div class="container-sm">
    <h3>
        This is your Electricity page
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
            <th scope="col">Actions</th>
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
                <td>
                    <a href="${pageContext.request.contextPath}/home/add_customer_gas_data?action=EDIT&id=${dataList.id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
    </c:when>
    <c:otherwise>
        <div class="container-sm">
            <a href="${pageContext.request.contextPath}/home/login_button" type="button" class="btn btn-info">Login</a>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>
