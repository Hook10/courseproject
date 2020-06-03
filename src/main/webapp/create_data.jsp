<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>


<head>
    <jsp:include page="style.jsp"/>

    <title>Create data</title>
</head>
<body>

<div align="center">
    <h1>Enter Gas Data Form</h1>
    <form action="${pageContext.request.contextPath}/home/add_customer_gas_data"  method="post">

        <table style="with: 80%">
            <tr>
                <td>month</td>
                <td><input type="month" name="month" /></td>
            </tr>
            <tr>
                <td>Gas data</td>
                <td><input type="number" name="Gas_data" /></td>
            </tr>


        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
