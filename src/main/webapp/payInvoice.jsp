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
        <th scope="col">Action</th>
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
                <td>
                    <form action="${pageContext.request.contextPath}/home/paymentForm" method="post">
                        <input type="hidden" name="id_supplier" value="${invoice.idSupplier}">
                        <input type="hidden" name="id_data" value="${invoice.idData}">
                        <input type="hidden" name="id_customer" value="${invoice.idCustomer}">
                        <input type="hidden" name="month" value="${invoice.month}">
                        <input type="hidden" name="data" value="${invoice.data}">
                        <input type="hidden" name="idInvoice" value="${invoice.idInvoice}">
                        <input type="hidden" name="cost" value="${invoice.cost}">
                        <button type="submit"
                                class="btn  btn-sm btn-info">Pay</button>
                    </form>
                </td>
                </c:forEach>
        </tbody>
            <tr>
                <td>Add Visa Number here </td>
                <td><input type="text" name="visa" minlength="16" maxlength="16" /></td>
            </tr>

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
