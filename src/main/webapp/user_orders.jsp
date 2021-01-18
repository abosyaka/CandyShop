<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title>Title</title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>

<div class="container">
    <c:forEach var="order" items="${orders}">
        <div class="jumbotron mt-4">
            <h3 class="display-4"><fmt:message key="button.order"/> №${order.id}
            </h3>
            <p class="lead"><fmt:message key="label.order.status"/> : <c:if
                    test="${order.status.name.equals('IN PROCESS')}">
                <span class="badge badge-info">${order.status.name}</span>
            </c:if>
                <c:if test="${order.status.name.equals('WAITING')}">
                    <span class="badge badge-warning">${order.status.name}</span>
                </c:if>
                <c:if test="${order.status.name.equals('COMPLETED')}">
                    <span class="badge badge-success">${order.status.name}</span>
                </c:if></p>
            <hr class="my-4">
            <p><fmt:message key="label.order.date"/> : ${order.orderDate}</p>
            <a class="btn btn-info" href="/order?id=${order.id}" role="button"><fmt:message key="button.expand"/> </a>
        </div>
    </c:forEach>
</div>

<%@include file="layout/footer.jsp" %>
</body>
</html>
