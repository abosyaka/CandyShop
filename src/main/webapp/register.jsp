<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title>Register</title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>
    <div class="col-6 mx-auto my-5">
        <form action="$user/register" method="post" id="registerForm">
            <div class="form-group">
                <label for="name">
                    <fmt:message key="label.name"/>
                </label>
                <input type="text" class="form-control" name="name" id="name" required>
            </div>
            <div class="form-group">
                <label for="email"><fmt:message key="label.email"/></label>
                <input type="email" class="form-control" name="email" id="email" required>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="label.password" /></label>
                <input type="password" class="form-control" name="password" id="password" required>
            </div>
            <div class="form-group">
                <label for="reNewPass"><fmt:message key="label.password.confirm" /></label>
                <input type="password" class="form-control" name="reNewPass" id="reNewPass" required>
            </div>
            <div class="form-group d-flex justify-content-center">
                <button class="btn btn-success w-50 font-weight-bold"><fmt:message key="button.signup" /></button>
            </div>
        </form>
    </div>
<%@include file="layout/footer.jsp" %>
<script>
    $('#registerForm').validate({
        rules: {
            password: {
                minlength: 3
            },
            reNewPass: {
                minlength: 3,
                equalTo: "#password"
            }
        }
    });
</script>
</body>
</html>

