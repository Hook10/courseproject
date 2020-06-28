<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="myLabels"/>


<div class="container-sm">
    <nav class="navbar navbar-dark bg-primary">
        <c:choose>
            <c:when test="${sessionScope.status == null || sessionScope.status == 'GUEST'  }">
                <a class="navbar-brand"
                   href="${pageContext.request.contextPath}/"><fmt:message
                        key="home"/></a>
            </c:when>
            <c:when test="${sessionScope.status == 'CUSTOMER'  }">
                <a class="navbar-brand"
                   href="${pageContext.request.contextPath}/home/customerPersonalAccountPage"><fmt:message
                        key="home"/></a>
            </c:when>
            <c:otherwise>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/home/admin_cabinet"><fmt:message
                        key="home"/></a>
            </c:otherwise>
        </c:choose>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home/logout">Logout <span
                            class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${sessionScope.status == null || sessionScope.status == 'GUEST'  }">
                            <a class="navbar-brand"
                               href="${pageContext.request.contextPath}/"><fmt:message
                                    key="home"/></a>
                        </c:when>
                        <c:when test="${sessionScope.status == 'CUSTOMER' }">
                            <a class="navbar-brand"
                               href="${pageContext.request.contextPath}/home/customerPersonalAccountPage"><fmt:message
                                    key="home"/></a>
                        </c:when>
                        <c:otherwise>
                            <a class="navbar-brand"
                               href="${pageContext.request.contextPath}/home/admin_cabinet"><fmt:message
                                    key="home"/></a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Additional
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/home/show_gas">Gas Page</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/home/show_water">Water
                            Page</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/home/show_electricity">Electricity
                            Page</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</div>

