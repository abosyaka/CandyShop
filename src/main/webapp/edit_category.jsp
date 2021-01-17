<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title>edit category</title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>
<a href="/admin/categories" class="btn btn-info ml-3 mt-3 font-weight-bold">
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
    <form action="/admin/category/edit" class="w-100" method="post">
        <div class="form-group">
            <label for="id">ID</label>
            <input class="form-control" type="text" readonly name="id" value="${category.id}" id="id">
        </div>
        <div class="form-group">
            <label for="category"><fmt:message key="label.name"/></label>
            <input class="form-control" type="text" name="name" value="${category.name}" id="category">
        </div>
        <div class="form-group d-flex justify-content-center">
            <button class="btn btn-success font-weight-bold w-50"><fmt:message key="button.save" /></button>
        </div>
    </form>
</div>
<%@include file="layout/footer.jsp" %>
</body>
</html>
