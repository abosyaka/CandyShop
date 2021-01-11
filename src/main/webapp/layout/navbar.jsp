<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-dark">
    <a class="navbar-brand font-weight-bold" href="${pageContext.request.contextPath}/">CandyShop
    </a>
    <ul class="row">
        <li class="nav-link">
            <a href="#">
                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                <span></span>
                <span ></span>
            </a>
        </li>
        <li class="nav-item dropdown">
<%--            <select name="" id="" class="form-control">--%>
<%--                <option value="">Ru</option>--%>
<%--                <option value="">Eng</option>--%>
<%--            </select>--%>
        <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ЯЗЫКИ</button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <a class="dropdown-item" href="?lang=ru">ru</a>
        <a class="dropdown-item" href="?lang=en">eng</a>
        </div>
        </div>
        </li>
        <li class="nav-link">
            <c:if test="${sessionScope.user == null}">
                <a href="${pageContext.request.contextPath}login" class="font-weight-bold text-white">
                    <fmt:message key="button.login"/>
                </a>
            </c:if>
        </li>
        <li class="nav-link">
            <c:if test="${sessionScope.user == null}">
                <a href="${pageContext.request.contextPath}register" class="font-weight-bold text-white">
                    <fmt:message key="button.register"/>
                </a>
            </c:if>
        </li>
        <li class="nav-link">
            <c:if test="${sessionScope.user != null}">
                <a href="#" class="font-weight-bold text-white">${sessionScope.user.name}</a>
            </c:if>
        </li>
        <li class="nav-link">
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <a href="#" class="font-weight-bold text-white">
                    <fmt:message key="button.admin.panel"/>
                </a>
            </c:if>
        </li>
        <li class="nav-link">
            <c:if test="${sessionScope.user != null}">
                <a href="${pageContext.request.contextPath}logout" class="font-weight-bold text-white">
                    <fmt:message key="button.logout"/>
                </a>
            </c:if>
        </li>
    </ul>
</nav>