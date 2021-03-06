<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%@include file="layout/head.jsp"%>
    <title><fmt:message key="button.user.all" /></title>
</head>
<body>
<%@include file="layout/navbar.jsp"%>
<div class="d-flex h-100" id="wrapper">

    <!-- Sidebar -->
    <%@include file="layout/sidebar.jsp" %>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper" class="container-fluid p-0">
        <div class="jumbotron bg-light h-100">
            <div class="row">
                <div class="col-md-4">
                    <h5 class="display-5 font-weight-bold"><fmt:message key="button.order.all" /></h5>
                </div>
            </div>
            <p class="lead"></p>
            <hr class="my-4">
            <div class="row">
                <div class="col-12 table-responsive">
                    <table class="table">
                        <thead>
                        <tr class="text-uppercase">
                            <th scope="col">№</th>
                            <th scope="col"><fmt:message key="label.order.purchaser" /></th>
                            <th scope="col"><fmt:message key="label.order.date" /></th>
                            <th scope="col"><fmt:message key="label.order.status" /></th>
                            <th scope="col" class="text-right"><fmt:message key="label.operations" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td scope="col">${order.id}
                                </td>
                                <td scope="col">${order.user.email}
                                </td>
                                <td scope="col">${order.orderDate}
                                </td>
                                <td scope="col">
                                    <c:if test="${order.status.name.equals('IN PROCESS')}">
                                        <span class="badge badge-info">${order.status.name}</span>
                                    </c:if>
                                    <c:if test="${order.status.name.equals('WAITING')}">
                                        <span class="badge badge-warning">${order.status.name}</span>
                                    </c:if>
                                    <c:if test="${order.status.name.equals('COMPLETED')}">
                                        <span class="badge badge-success">${order.status.name}</span>
                                    </c:if>
                                </td>
                                <td scope="col" class="text-right">
                                    <a href="/admin/order?id=${order.id}" class="btn btn-secondary btn-sm">
                                        <i class="bi bi-pencil-square"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
</div>

<%@include file="layout/footer.jsp"%>
</body>
</html>
