<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>


<head>
    <jsp:include page="style.jsp"/>

    <title>Create data</title>
</head>
<body>
<div class="container-sm">
<div align="center">
    <h1>Enter Your Data Here</h1>
    <form action="${pageContext.request.contextPath}/home/add_customer_gas_data"  method="post">

        <table style="align-items: center">
            <tr>
                <td>month</td>
                <td><input type="month" name="month" /></td>
            </tr>
            <tr>
                <td>Add Data </td>
                <td><input type="number" name="Gas_data" /></td>
            </tr>

            <tr>
                <td>
                    <select name="id_supplier">
                        <option value="1">Gas</option>
                        <option value="2">Water</option>
                        <option value="3">Electricity</option>

                    </select>
                </td>
            </tr>

<%--            <div class="input-group mb-3">--%>
<%--                <div class="input-group-prepend">--%>
<%--                    <label class="input-group-text" for="inputGroupSelect01">Choose Supplier</label>--%>
<%--                </div>--%>
<%--                <select class="custom-select" id="inputGroupSelect01">--%>
<%--                    <option selected>Choose...</option>--%>
<%--                    <option value="1">Gas</option>--%>
<%--                    <option value="2">Water</option>--%>
<%--                    <option value="3">Electricity</option>--%>
<%--                </select>--%>
<%--            </div>--%>

        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</div>
</body>
</html>
