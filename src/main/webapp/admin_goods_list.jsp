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
                    <h5 class="display-5 font-weight-bold"><fmt:message key="button.good.all" /></h5>
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
                            <th scope="col"><fmt:message key="label.price" /></th>
                            <th scope="col"><fmt:message key="label.weight" /></th>
                            <th scope="col" class="text-right"><fmt:message key="label.operations" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="good" items="${goods}">
                            <tr>
                                <td scope="col">${good.id}
                                </td>
                                <td scope="col">${good.name}
                                </td>
                                <td scope="col">${good.price}
                                </td>
                                <td scope="col">${good.weight}
                                </td>
                                <td scope="col" class="text-right">
                                    <a href="/admin/good?id=${good.id}" class="btn btn-secondary btn-sm">
                                        <i class="bi bi-pencil-square"></i>
                                    </a>
                                    <button type="button" class="btn btn-outline-danger btn-sm" data-toggle="modal"
                                            data-target="#deleteGoodModal" data-id="${good.id}"><i class="bi bi-trash-fill"></i>
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

<!-- DELETE CATEGORY MODAL -->
<div class="modal fade" id="deleteGoodModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h3 class="text-center font-weight-bold"><fmt:message key="label.delete.confirm" /></h3>
            </div>
            <div class="modal-footer">
                <form action="/admin/good/delete" method="post">
                    <input type="text" name="id" id="lol">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="button.close"/></button>
                    <button type="submit" class="btn btn-danger"><fmt:message key="button.delete"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- END OF MODAL -->

<%@include file="layout/footer.jsp" %>
<script>
    $('#deleteGoodModal').on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget)
        let id = button.data('id')
        const modal = $(this)
        modal.find('#lol').val(id)
    });
</script>
</body>
</html>
