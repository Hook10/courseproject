<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div align="center">
    <h1>Customer Register Form</h1>
    <form action="<%= request.getContextPath() %>/register" method="post">
        <table style="with: 80%">
            <tr>
                <td>First Name</td>
                <td><input type="text" name="firstName" /></td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><input type="text" name="surname" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" name="email" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>City</td>
                <td><input type="text" name="city" /></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input type="text" name="address" /></td>
            </tr>
            <tr>
                <td>iin</td>
                <td><input type="text" name="iin" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>