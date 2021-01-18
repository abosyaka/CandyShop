<jsp:useBean id="good" scope="request" type="com.epam.candy.entity.Good"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title>edit order</title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>
<a href="/admin/goods" class="btn btn-info ml-3 mt-3 font-weight-bold">
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
    <form action="/admin/good/edit" class="w-100" method="post">
        <div class="form-group">
            <label for="id">ID</label>
            <input class="form-control" type="text" readonly name="id" value="${good.id}" id="id">
        </div>
        <div class="form-group">
            <label for="name"><fmt:message key="label.name"/></label>
            <input class="form-control" type="text" name="name" value="${good.name}" id="name">
        </div>
        <div class="form-group">
            <label for="description"><fmt:message key="label.description" /></label>
            <textarea class="form-control" type="text" name="description" id="description">${good.description}</textarea>
        </div>
        <div class="form-group">
            <label for="ingredients"><fmt:message key="label.ingredients"/></label>
            <textarea class="form-control" type="text" name="ingredients" id="ingredients">${good.ingredients}</textarea>
        </div>
        <div class="form-group">
            <label for="category"><fmt:message key="button.category.all"/> </label>
            <select class="form-control" name="category" id="category">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}"
                            <c:if test="${category.equals(good.category)}">selected</c:if>>${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="price"><fmt:message key="label.price"/></label>
            <input class="form-control" type="number" min="1" name="price" value="${good.price}" id="price">
        </div>
        <div class="form-group">
            <label for="weight"><fmt:message key="label.weight"/></label>
            <input class="form-control" type="number" min="0.1" name="weight" value="${good.weight}" id="weight" step="0.01">
        </div>
        <div class="form-group">
            <label for="storagePeriod"><fmt:message key="label.storage.period"/></label>
            <input class="form-control" type="number" min="1" name="storagePeriod" value="${good.storagePeriod}" id="storagePeriod">
        </div>
        <div class="form-group d-flex justify-content-center" style="height: 200px">
            <img src="/image/upload/${good.pictureUrl}" alt="" class="img-fluid img-thumbnail h-100" style="object-fit: contain">
        </div>
        <div class="form-group d-flex justify-content-center">
            <button class="btn btn-success font-weight-bold w-50"><fmt:message key="button.save" /></button>
        </div>
    </form>

    <form action="/image/upload" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${good.id}">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <button class="btn btn-outline-secondary" type="submit" id="inputGroupFileAddon03"><fmt:message key="button.upload" /></button>
            </div>
            <div class="custom-file">
                <input name="file" type="file" class="custom-file-input" id="inputGroupFile03" aria-describedby="inputGroupFileAddon03" required>
                <label class="custom-file-label" for="inputGroupFile03"><fmt:message key="button.file.browse"/> </label>
            </div>
        </div>
    </form>
</div>
<%@include file="layout/footer.jsp" %>
</body>
</html>
