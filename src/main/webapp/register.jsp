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
                <label for="name">
                    <fmt:message key="label.name"/>
                </label>
                <input type="text" class="form-control" name="name" id="name">
            </div>
            <div class="form-group">
                <label for="email"><fmt:message key="label.email"/></label>
                <input type="text" class="form-control" name="email" id="email">
            </div>
            <div class="form-group">
                <label for="pass"><fmt:message key="label.password" /></label>
                <input type="password" class="form-control" name="password" id="pass">
            </div>
            <div class="form-group">
                <label for="repass"><fmt:message key="label.password.confirm" /></label>
                <input type="password" class="form-control" name="repassword" id="repass">
            </div>
            <div class="form-group d-flex justify-content-center">
                <button class="btn btn-success w-50 font-weight-bold"><fmt:message key="button.signup" /></button>
            </div>
        </form>
    </div>
<%@include file="layout/footer.jsp" %>
</body>
</html>

