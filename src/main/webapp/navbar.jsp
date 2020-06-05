<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-sm">
    <nav class="navbar navbar-dark bg-primary">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

                <c:if test="${sessionScope.status != 'GUEST' || sessionScope.status == null}">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home/logout">Logout <span class="sr-only">(current)</span></a>
                </li>
                </c:if>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Main Page</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Additional
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/home/show_gas">Gas Page</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/home/show_water">Water Page</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/home/show_electricity">Electricity Page</a>
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