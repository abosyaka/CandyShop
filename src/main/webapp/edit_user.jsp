<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title>edit order</title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>
<a href="/admin/users" class="btn btn-info ml-3 mt-3 font-weight-bold">
    <i class="bi bi-arrow-bar-left font-weight-bold"></i>
    <fmt:message key="button.back.list"/> </a>
<div class="col-6 mx-auto mt-5">
    <c:if test="${param.edit != null}">
        <c:if test="${param.edit.equals('success')}">
            <div class="alert alert-success"><fmt:message key="label.edit.success" /></div>
        </c:if>
        <c:if test="${param.edit.equals('fail')}">
            <div class="alert alert-danger"><fmt:message key="label.edit.fail" /></div>
        </c:if>
    </c:if>
    <form action="/admin/user/edit" class="w-100" method="post" id="editUserForm">
        <div class="form-group">
            <label for="id">ID</label>
            <input class="form-control" type="text" readonly name="id" value="${user.id}" id="id">
        </div>
        <div class="form-group">
            <label for="email"><fmt:message key="label.email"/></label>
            <input class="form-control" type="email" readonly name="email" value="${user.email}" id="email">
        </div>
        <div class="form-group">
            <label for="name"><fmt:message key="label.name" /></label>
            <input class="form-control" type="text" name="name" value="${user.name}" id="name">
        </div>
        <div class="form-group">
            <label for="password"><fmt:message key="label.password" /></label>
            <input class="form-control" type="text" readonly name="password" value="${user.password}" id="password">
        </div>
        <div class="form-group">
            <label for="newPass"><fmt:message key="label.password" /></label>
            <input class="form-control" type="password" name="newPass" id="newPass">
        </div>
        <div class="form-group">
            <label for="reNewPass"><fmt:message key="label.password.confirm" /></label>
            <input class="form-control" type="password" name="reNewPass" id="reNewPass">
        </div>
        <div class="form-group">
            <label for="role_id"><fmt:message key="label.role"/> </label>
            <select class="form-control" name="role" id="role_id">
                <c:forEach var="role" items="${roles}">
                    <option value="${role.id}"
                            <c:if test="${role.equals(user.role)}">selected</c:if>>${role.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group d-flex justify-content-center">
            <button class="btn btn-success font-weight-bold w-50"><fmt:message key="button.save" /></button>
        </div>
    </form>
</div>
<%@include file="layout/footer.jsp" %>

<script>
    $('#editUserForm').validate({
        rules: {
            newPass: {
                minlength: 3
            },
            reNewPass: {
                minlength: 3,
                equalTo: "#newPass"
            }
        }
    });
</script>
</body>
</html>
