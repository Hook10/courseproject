<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dataDao" class="dao.daoimpl.DataDaoImpl"/>
<jsp:useBean id="data" class="entity.Data"/>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>Edit data</title>
</head>
<body>
<h3>Edit Your Data</h3>
<c:if test="${not empty data}">
    <form method="POST" action="${pageContext.request.contextPath}/home/editCustomersData">
    <input type="hidden" name="data" value="${data.data}"/>
    <table border="0">

    <tr>
    <td>Id</td>
    <td style="color:red;">${data.id}</td>
    </tr>
        <tr>
            <td>Month</td>
            <td><input type="text" name="month" value="${data.month}"/></td>
        </tr>
        <tr>
            <td>Data</td>
            <td><input type="text" name="name" value="${data.data}" /></td>
        </tr>

        <tr>
            <td>id_customer</td>
            <td><input type="text" name="month" value="${data.idCustomer}"/></td>
        </tr>

        <tr>
            <td>id_supplier</td>
            <td><input type="text" name="month" value="${data.idSupplier}"/></td>
        </tr>

    <tr>
    <td colspan = "2">
    <input type="submit" value="Submit"/>
        <a href="${pageContext.request.contextPath}/home/customerPersonalAccountPage">Cancel</a>

    </td>
    </tr>
    </table>
    </form>
</c:if>

</body>
</html>
