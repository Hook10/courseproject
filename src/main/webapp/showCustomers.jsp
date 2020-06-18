<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="myLabels" />
<html lang="${theLocale}">
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>All customers</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.status == 'WEBSITEADMIN' }">
        <div class="container-sm">
            <h3>
               Here is all customers list
            </h3>
            <br>

            <table class = "table table-info">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">first_name</th>
                    <th scope="col">surname</th>
                    <th scope="col">email</th>
                    <th scope="col">city</th>
                    <th scope="col">address</th>
                    <th scope="col">iin</th>
                    <th scope="col">Action</th>
                    <th scope="col">Action</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.customerList}" var="customer">
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.firstName}</td>
                        <td>${customer.surname}</td>
                        <td>${customer.email}</td>
                        <td>${customer.city}</td>
                        <td>${customer.address}</td>
                        <td>${customer.iin}</td>

                        <td>
                            <form action="${pageContext.request.contextPath}/home/editCustomerDataButton" method="post">
                                <input type="hidden" name="id_customer" value="${dataList.idCustomer}">
                                <input type="hidden" name="id" value="${dataList.id}">
                                <button type="submit"
                                        class="btn  btn-sm btn-info"><fmt:message key="Edit"/></button>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/home/delete_customer" method="post">
                                <input type="hidden" name="id" value="${customer.id}">
                                <button type="submit"
                                        class="btn  btn-sm btn-info"><fmt:message key="Delete"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container-sm">
            <a href="${pageContext.request.contextPath}/home/login_button" type="button" class="btn btn-info"><fmt:message key="login"/></a>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
