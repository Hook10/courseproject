<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="myLabels"/>
<html lang="${theLocale}">
<head>
    <jsp:include page="backButtonRestrict.jsp"/>
    <jsp:include page="navbar.jsp"/>
    <jsp:include page="style.jsp"/>
    <title>Login Page</title>
</head>
<body>
<div class="container-lg">

    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/login_button?theLocale=en_US" type="button" class="btn btn-info">English
        (US)</a> |
    <a href="${pageContext.request.contextPath}/home/login_button?theLocale=ru_RU" type="button" class="btn btn-info">Русский
        (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
<div class="container-lg">
    <div class="d-flex justify-content-center h-100">
        <div class="card-title">
            <div class="col-lg">
                <form action="${pageContext.request.contextPath}/home/login" method="post">
                    <div class="form-group">
                        <label for="email"> <fmt:message key="email"/></label>
                        <input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="email">
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

