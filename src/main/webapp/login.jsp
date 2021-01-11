<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Login</title>
    <%@include file="layout/head.jsp" %>

</head>
<body>
<%@include file="layout/navbar.jsp"%>>
<div class="col-6 mx-auto my-5">
    <form action="${pageContext.request.contextPath}user/login" method="post">
        <div class="form-group">
            <label for="email"><fmt:message key="label.email"/></label>
            <input type="text" class="form-control" name="email" id="email">
        </div>
        <div class="form-group">
            <label for="pass"><fmt:message key="label.password"/> </label>
            <input type="password" class="form-control" name="password" id="pass">
        </div>
        <div class="form-group d-flex justify-content-center">
            <button class="btn btn-success w-50 font-weight-bold"><fmt:message key="button.login"/></button>
        </div>
    </form>
</div>
<jsp:include page="layout/footer.jsp"/>
</body>
</html>
