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
    <title>Electricity page</title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/show_electricity?theLocale=en_US" type="button"
       class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/show_electricity?theLocale=ru_RU" type="button"
       class="btn btn-info">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
</div>
<div class="container-sm">
    <div class="btn-group-toggle " role="group" aria-label="Basic example">
        <a href="${pageContext.request.contextPath}/home/customerPersonalAccountPage" type="button"
           class="btn btn-info"><fmt:message key="back.to.cabinet"/></a>
    </div>
</div>
<c:choose>
    <c:when test="${sessionScope.status == 'CUSTOMER' ||sessionScope.status == 'WEBSITEADMIN' }">
        <div class="container-sm">
            <h3>
                <fmt:message key="electric.page.message"/>
            </h3>
            <br>
            <table class="table table-info">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="ID"/></th>
                    <th scope="col"><fmt:message key="month"/></th>
                    <th scope="col"><fmt:message key="data"/></th>
                    <th scope="col"><fmt:message key="ID_customer"/></th>
                    <th scope="col"><fmt:message key="ID_supplier"/></th>
                    <th scope="col"><fmt:message key="Actions"/></th>
                    <th scope="col"><fmt:message key="Actions"/></th>
                    <th scope="col"><fmt:message key="Pay"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.dataList}" var="dataList">
                    <tr>
                        <td>${dataList.id}</td>
                        <td>${dataList.month}</td>
                        <td>${dataList.data}</td>
                        <td>${dataList.idCustomer}</td>
                        <td>${dataList.idSupplier}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/home/editCustomerDataButton" method="post">
                                <input type="hidden" name="id_customer" value="${dataList.idCustomer}">
                                <input type="hidden" name="id" value="${dataList.id}">
                                <button type="submit"
                                        class="btn  btn-sm btn-info"><fmt:message key="Edit"/></button>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/home/delete_data" method="post">
                                <input type="hidden" name="id_supplier" value="${dataList.idSupplier}">
                                <input type="hidden" name="id" value="${dataList.id}">
                                <button type="submit"
                                        class="btn  btn-sm btn-info"><fmt:message key="Delete"/></button>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/home/payInvoiceButton" method="post">
                                <input type="hidden" name="id_supplier" value="${dataList.idSupplier}">
                                <input type="hidden" name="id_customer" value="${dataList.idCustomer}">
                                <input type="hidden" name="month" value="${dataList.month}">
                                <input type="hidden" name="data" value="${dataList.data}">
                                <input type="hidden" name="id_data" value="${dataList.id}">
                                <button type="submit"
                                        class="btn  btn-sm btn-info"><fmt:message key="Pay"/></button>
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
