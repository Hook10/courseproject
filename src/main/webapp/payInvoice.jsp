<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>Pay page</title>
</head>
<body>
This is Pay page
<c:choose>
    <c:when test="${sessionScope.status == 'CUSTOMER' ||sessionScope.status == 'WEBSITEADMIN' }">
        <div class="container-sm">
        <h3>
        This is your pay page
        </h3>
        <br>
        <table class = "table table-info">
        <thead>
        <tr>
        <th scope="col">IdInvoice</th>
        <th scope="col">idData</th>
        <th scope="col">ID_supplier</th>
        <th scope="col">ID_customer</th>
        <th scope="col">month</th>
        <th scope="col">data</th>
        <th scope="col">cost</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.invoicesList}" var="invoice">
            <tr>
            <td>${invoice.idInvoice}</td>
            <td>${invoice.idData}</td>
            <td>${invoice.idSupplier}</td>
            <td>${invoice.idCustomer}</td>
            <td>${invoice.month}</td>
            <td>${invoice.data}</td>
            <td>${invoice.cost}</td>
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
