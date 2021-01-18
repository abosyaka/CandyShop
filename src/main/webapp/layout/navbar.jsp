<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-dark">
    <a class="navbar-brand font-weight-bold" href="/">CandyShop
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
            <form action="${pageContext.request.contextPath}" method="post">
                <select name="lang" id="" class="form-control" onchange="this.form.submit()">
                    <option value="ru" <c:if test="${current.equals('ru')}">selected</c:if>>Ru</option>
                    <option value="en" <c:if test="${current.equals('en')}">selected</c:if>>Eng</option>
                </select>
            </form>
        </li>
        <li class="nav-link">
            <a href="/cart" class="font-weight-bold text-white"><i class="bi bi-cart4"></i></a>
        </li>
        <li class="nav-link">
            <c:if test="${sessionScope.user == null}">
                <a href="/login" class="font-weight-bold text-white">
                    <fmt:message key="button.login"/>
                </a>
            </c:if>
        </li>
        <li class="nav-link">
            <c:if test="${sessionScope.user == null}">
                <a href="/register" class="font-weight-bold text-white">
                    <fmt:message key="button.register"/>
                </a>
            </c:if>
        </li>
        <li class="nav-link">
            <c:if test="${sessionScope.user.role.name.equals('ROLE_ADMIN')}">
                <a href="/admin/users" class="font-weight-bold text-white">
                    <fmt:message key="button.admin.panel"/>
                </a>
            </c:if>
        </li>
        <li class="nav-link">
            <c:if test="${sessionScope.user != null}">
                <a href="/orders" class="font-weight-bold text-white">
                <fmt:message key="button.order.all"/>
                </a>
            </c:if>
        </li>
        <li class="nav-link">
            <c:if test="${sessionScope.user != null}">
                <a href="/logout" class="font-weight-bold text-white">
                    <fmt:message key="button.logout"/>
                </a>
            </c:if>
        </li>
    </ul>
</nav>