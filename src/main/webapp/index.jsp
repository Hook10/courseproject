<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <title>Main Page</title>
</head>

<body>
<div class="container-lg">
    View this page in: <br/>
    <a href="${pageContext.request.contextPath}/home/index?theLocale=en_US" type="button" class="btn btn-info">English (US)</a> |
    <a href="${pageContext.request.contextPath}/home/index?theLocale=ru_RU" type="button" class="btn btn-info">Русский (RU)</a>
    <br/><br/>
    Selected language: ${theLocale}
    <hr>
    <br/><br/>
</div>
<c:choose>
<c:when test="${sessionScope.status == 'GUEST' || sessionScope.status == null}">
<div class="container-sm">
    <a href="${pageContext.request.contextPath}/home/login_button" type="button" class="btn btn-info"><fmt:message
            key="login"/></a>
    <a href="${pageContext.request.contextPath}/home/register_button" type="button" class="btn btn-info"><fmt:message
            key="registration"/></a>
    <a href="${pageContext.request.contextPath}/home/login_admin_button" type="button" class="btn btn-info"><fmt:message
            key="administration"/></a>

    </c:when>
    <c:otherwise>
        <div class="container-sm">
            <a href="${pageContext.request.contextPath}/home/customerPersonalAccountPage" type="button"
               class="btn btn-info"><fmt:message key="personal.cabinet"/></a>
        </div>
    </c:otherwise>
    </c:choose>
<c:choose>
    <c:when test="${sessionScope.theLocale== 'ru_RU'}">
    <p>
        <br>
        Данный сайт создан, что бы  сделать Вашу жизнь еще удобнее.
        Здесь можно внести показания ваших приборов учета воды, газа, электричества и  оплатить счета с помощью
        платежных карт VISA/MasterCard.

    </p>
    <br>
    <br>
    <br>
    <p>
        Надеемся, что Вам понравится наш сервис.<br>
        С уважением,<br>
    &lt;EPAM&gt; systems - делаем Вашу жизнь лучше!

    </p>
    </c:when>
    <c:otherwise>
        <p>
            <br>
            This site was created to make your life even more convenient.
            Here you can enter the readings of your metering devices for water, gas, electricity and pay bills using
            VISA / MasterCard payment cards.
        </p>
        <br>
        <br>
        <br>
        <p>
            We hope you enjoy our service.<br>
            Best regards,<br>
            &lt;EPAM&gt; systems - makes your life better!
        </p>

    </c:otherwise>
</c:choose>
</div>
</body>
</html>



