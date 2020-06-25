<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="myLabels"/>
<html>
<head>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title><fmt:message key="admin.login.page"/></title>
</head>
<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/adminLogin?theLocale=en_US">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/adminLogin?theLocale=ru_RU">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>

<div class="container-lg">
    <div class="d-flex justify-content-center h-100">
        <div class="card-title">
            <div class="col-lg">

                <form action="${pageContext.request.contextPath}/home/adminLogin" method="post">
                    <div class="form-group">
                        <label for="login"> login</label>
                        <input type="text" class="form-control" id="login" aria-describedby="emailHelp" name="login">
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="password"/></label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><fmt:message key="login"/></button>
                    </div>


                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
