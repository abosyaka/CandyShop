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
                    <h5 class="display-5 font-weight-bold"><fmt:message key="button.category.all" /></h5>
                </div>
                <div class="col-md-2 ml-auto offset-3">
                    <button class="btn btn-secondary offset-5" data-toggle="modal" data-target="#modalLanguage"
                            data-command="add">
                        <fmt:message key="button.add"/>
                    </button>

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
                            <th scope="col"><fmt:message key="label.name" /></th>
                            <th scope="col" class="text-right"><fmt:message key="label.operations"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="category" items="${categories}">
                            <tr>
                                <td scope="col">${category.id}
                                </td>
                                <td scope="col">${category.name}
                                </td>
                                <td scope="col" class="text-right">
                                    <button class="btn btn-secondary btn-sm">
                                        <i class="bi bi-pencil-square"></i>
                                    </button>
                                    <button type="button" class="btn btn-outline-danger btn-sm" data-toggle="modal"
                                            data-target="#deleteModal"><i class="bi bi-trash-fill"></i>
                                    </button>
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
