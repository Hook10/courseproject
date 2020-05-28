<%--
  Created by IntelliJ IDEA.
  User: sapas
  Date: 28.05.2020
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h1>Customer Registration Form</h1>
    <form action="${pageContext.request.contextPath}/home/create_admin" method="post">
        <table style="with: 80%">
            <tr>
                <td>Login</td>
                <td><input type="text" name="login" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>Supplier Id</td>
                <td><input type="text" name="supplier_id" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" name="email" /></td>
            </tr>
            <tr>
                <td>Company name</td>
                <td><input type="text" name="company_name" /></td>
            </tr>



        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
