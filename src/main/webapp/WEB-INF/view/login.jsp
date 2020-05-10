<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>login</title>
</head>
<body>
<div align="center">
    <h1>Customer Login Form</h1>
    <form action="login" method="post">
        <table style="with: 100%">
            <tr>
                <td>Email</td>
                <td><input type="text" name="email" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>

        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
