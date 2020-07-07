<%@ page contentType="text/html;charset=UTF-8" %>
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
    <title><fmt:message key="pay.page"/></title>
</head>
<body>
<div class="container-lg">

    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/payInvoiceButton?theLocale=en_US " type="button"
       class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/payInvoiceButton?theLocale=ru_RU" type="button"
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
<fmt:message key="pay.page"/>
<c:choose>
    <c:when test="${sessionScope.status == 'CUSTOMER' ||sessionScope.status == 'WEBSITEADMIN' }">
        <div class="container-sm">
            <h3>
                <fmt:message key="pay.page"/>
            </h3>
            <br>
            <table class="table table-info">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="idInvoice"/></th>
                    <th scope="col"><fmt:message key="idData"/></th>
                    <th scope="col"><fmt:message key="ID_supplier"/></th>
                    <th scope="col"><fmt:message key="ID_customer"/></th>
                    <th scope="col"><fmt:message key="month"/></th>
                    <th scope="col"><fmt:message key="data"/></th>
                    <th scope="col"><fmt:message key="cost"/></th>
                    <th scope="col"><fmt:message key="Actions"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.invoicesList}" var="invoice">
                <tr>
                    <td>${invoice.idInvoice}</td>
                    <td>${invoice.idData}</td>
                    <td>${invoice.idSupplier}</td>
                    <td>${invoice.idCustomer}</td>
                    <td>${invoice.month}</td>
                    <td>${invoice.data}</td>
                    <td>${invoice.cost}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/home/paymentForm" method="post">
                            <input type="hidden" name="id_supplier" value="${invoice.idSupplier}">
                            <input type="hidden" name="id_data" value="${invoice.idData}">
                            <input type="hidden" name="id_customer" value="${invoice.idCustomer}">
                            <input type="hidden" name="month" value="${invoice.month}">
                            <input type="hidden" name="data" value="${invoice.data}">
                            <input type="hidden" name="idInvoice" value="${invoice.idInvoice}">
                            <input type="hidden" name="cost" value="${invoice.cost}">
                            <button type="submit"
                                    class="btn  btn-sm btn-info"><fmt:message key="Pay"/></button>
                        </form>
                    </td>
                    </c:forEach>
                </tbody>
                <tr>
                    <td><fmt:message key="Add.Visa.Number.here"/></td>
                    <td><input type="text" name="visa" minlength="16" maxlength="16"/></td>
                </tr>

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
