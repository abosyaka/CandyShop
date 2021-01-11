<%--
  Created by IntelliJ IDEA.
  User: Mi Notebook
  Date: 10.01.2021
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title>Register</title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>
    <div class="col-6 mx-auto my-5">
        <form action="${pageContext.request.contextPath}user/register" method="post">
            <div class="form-group">
                <label for="name">First name</label>
                <input type="text" class="form-control" name="name" id="name">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" name="email" id="email">
            </div>
            <div class="form-group">
                <label for="pass">Password</label>
                <input type="password" class="form-control" name="password" id="pass">
            </div>
            <div class="form-group">
                <label for="repass">Confirm Password</label>
                <input type="password" class="form-control" name="repassword" id="repass">
            </div>
            <div class="form-group d-flex justify-content-center">
                <button class="btn btn-success w-50 font-weight-bold">Submit</button>
            </div>
        </form>
    </div>
<%@include file="layout/footer.jsp" %>
</body>
</html>

