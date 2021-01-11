<%--
  Created by IntelliJ IDEA.
  User: Mi Notebook
  Date: 08.01.2021
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-dark">
    <a class="navbar-brand font-weight-bold" href="<c:url value="/"/>">CandyShop
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
            <select name="" id="" class="form-control">
                <option value="">Ru</option>
                <option value="">Eng</option>
            </select>
        </li>
        <li class="nav-link">
            <a href="#" class="font-weight-bold text-white">Login</a>
        </li>
        <li class="nav-link">
            <a href="${pageContext.request.contextPath}register" class="font-weight-bold text-white">Register</a>
        </li>
        <li class="nav-link">
            <a href="#" class="font-weight-bold text-white">HELLO</a>
        </li>
        <li class="nav-link">
            <a href="#" class="font-weight-bold text-white">
                Admin panel
            </a>
        </li>
        <li class="nav-link">
            <a href="#" class="font-weight-bold text-white">Logout</a>
        </li>
    </ul>
</nav>