<%@ page contentType="text/html;charset=UTF-8"  %>
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
    <title>All suppliers</title>
</head>
<body>
<div class="container-lg">

    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/show_all_suppliers?theLocale=en_US " type="button"
       class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/show_all_suppliers?theLocale=ru_RU" type="button"
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
                <fmt:message key="list.of.all.suppliers"/>
            </h3>
            <br>

            <table class="table table-info">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="ID"/></th>
                    <th scope="col"><fmt:message key="company.name"/></th>
                    <th scope="col"><fmt:message key="BIN"/></th>
                    <th scope="col"><fmt:message key="Actions"/></th>
                    <th scope="col"><fmt:message key="Actions"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.supplierList}" var="supplier">
                    <tr>
                        <td>${supplier.id}</td>
                        <td>${supplier.companyName}</td>
                        <td>${supplier.bin}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/home/editSupplierButton" method="post">
                                <input type="hidden" name="id" value="${supplier.id}">
                                <input type="hidden" name="companyName" value="${supplier.companyName}">
                                <input type="hidden" name="bin" value="${supplier.bin}">
                                <button type="submit"
                                        class="btn  btn-sm btn-info"><fmt:message key="Edit"/></button>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/home/delete_supplier" method="post">
                                <input type="hidden" name="id" value="${supplier.id}">
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
