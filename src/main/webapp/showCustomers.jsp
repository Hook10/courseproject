<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="myLabels"/>
<html lang="${theLocale}">
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>All customers</title>
</head>
<body>
<div class="container-lg">

    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/show_all_customers?theLocale=en_US " type="button"
       class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/show_all_customers?theLocale=ru_RU" type="button"
       class="btn btn-info">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
</div>
<div class="container-sm">
    <div class="btn-group-toggle " role="group" aria-label="Basic example">
        <a href="${pageContext.request.contextPath}/home/admin_cabinet" type="button"
           class="btn btn-info"><fmt:message key="back.to.cabinet"/></a>
    </div>
</div>
<c:choose>
    <c:when test="${sessionScope.status == 'WEBSITEADMIN' }">
        <div class="container-sm">
            <h3>
                <fmt:message key="list.of.all.customers"/>
            </h3>
            <br>

            <table class="table table-info">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="ID"/></th>
                    <th scope="col"><fmt:message key="first.name"/></th>
                    <th scope="col"><fmt:message key="surname"/></th>
                    <th scope="col"><fmt:message key="email"/></th>
                    <th scope="col"><fmt:message key="city"/></th>
                    <th scope="col"><fmt:message key="address"/></th>
                    <th scope="col"><fmt:message key="IIN"/></th>
                    <th scope="col"><fmt:message key="Actions"/></th>
                    <th scope="col"><fmt:message key="Actions"/></th>

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
                            <form action="${pageContext.request.contextPath}/home/editCustomerButton" method="post">
                                <input type="hidden" name="id" value="${customer.id}">
                                <input type="hidden" name="firstName" value="${customer.firstName}">
                                <input type="hidden" name="surname" value="${customer.surname}">
                                <input type="hidden" name="email" value="${customer.email}">
                                <input type="hidden" name="password" value="${customer.password}">
                                <input type="hidden" name="city" value="${customer.city}">
                                <input type="hidden" name="address" value="${customer.address}">
                                <input type="hidden" name="iin" value="${customer.iin}">
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
            <a href="${pageContext.request.contextPath}/home/login_button" type="button"
               class="btn btn-info"><fmt:message key="login"/></a>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
