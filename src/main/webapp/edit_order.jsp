<jsp:useBean id="order" scope="request" type="com.epam.candy.entity.Order"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title>edit order</title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>
<a href="/admin/orders" class="btn btn-info ml-3 mt-3 font-weight-bold">
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
    <form action="/admin/order/edit" class="w-100" method="post">
        <div class="form-group">
            <label for="id">ID</label>
            <input class="form-control" type="text" readonly name="id" value="${order.id}" id="id">
        </div>
        <div class="form-group">
            <label for="user_id"><fmt:message key="label.order.purchaser"/></label>
            <input class="form-control" type="text" readonly name="user" value="${order.user.email}" id="user_id">
        </div>
        <div class="form-group">
            <label for="status_id"><fmt:message key="label.order.status"/> </label>
            <select class="form-control" name="status" value="${order.id}" id="status_id">
                <c:forEach var="status" items="${statuses}">
                    <option value="${status.id}"
                            <c:if test="${status.equals(order.status)}">selected</c:if>>${status.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="order_date"><fmt:message key="label.order.date" /></label>
            <input class="form-control" type="date" readonly name="orderDate" value="${order.orderDate}" id="order_date">
        </div>
        <c:forEach var="detail" items="${orderDetails}" varStatus="loop">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label><fmt:message key="label.good" /></label>
                    <input class="form-control" type="text" readonly name="good${loop.index}}" value="${detail.good.name}">
                </div>
                <div class="form-group col-md-6">
                    <label><fmt:message key="label.count" /></label>
                    <input class="form-control" type="number" name="count${loop.index}" value="${detail.count}" min="0">
                </div>
            </div>
        </c:forEach>
        <div class="form-group d-flex justify-content-center">
            <button class="btn btn-success font-weight-bold w-50"><fmt:message key="button.save" /></button>
        </div>
    </form>
</div>
<%@include file="layout/footer.jsp" %>
</body>
</html>
