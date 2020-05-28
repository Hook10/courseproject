<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="style.jsp"/>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h1>Customer Registration Form</h1>
    <form action="${pageContext.request.contextPath}/home/create_supplier" method="post">
        <table style="with: 80%">
            <tr>
                <td>Company Name</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>BIN</td>
                <td><input type="text" name="bin" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
