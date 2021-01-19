<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
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
                    <h5 class="display-5 font-weight-bold"><fmt:message key="button.user.all"/></h5>
                </div>
                <div class="col-md-2 ml-auto offset-3">
                    <button class="btn btn-secondary offset-5" data-toggle="modal" data-target="#addUserModal"
                            data-command="add">
                        <fmt:message key="button.add"/>
                    </button>

                </div>
            </div>
            <p class="lead"></p>
            <hr class="my-4">
            <div class="row">
                <c:if test="${param.add ne null}">
                    <c:if test="${param.add.equals('success')}">
                        <div class="alert alert-success"><fmt:message key="label.add.success" /></div>
                    </c:if>
                    <c:if test="${param.add.equals('fail')}">
                        <div class="alert alert-danger"><fmt:message key="label.add.fail" /></div>
                    </c:if>
                </c:if>
                <c:if test="${param.delete ne null}">
                    <c:if test="${param.delete.equals('success')}">
                        <div class="alert alert-success"><fmt:message key="label.delete.success" /></div>
                    </c:if>
                    <c:if test="${param.delete.equals('fail')}">
                        <div class="alert alert-danger"><fmt:message key="label.delete.fail" /></div>
                    </c:if>
                </c:if>
                <div class="col-12 table-responsive">
                    <table class="table">
                        <thead>
                        <tr class="text-uppercase">
                            <th scope="col">№</th>
                            <th scope="col"><fmt:message key="label.name"/></th>
                            <th scope="col"><fmt:message key="label.email" /></th>
                            <th scope="col"><fmt:message key="label.role" /></th>
                            <th scope="col" class="text-right"><fmt:message key="label.operations" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td scope="col">${user.id}</td>
                                <td scope="col">${user.name}
                                </td>
                                <td scope="col">${user.email}
                                </td>
                                <td scope="col">${user.role.name}
                                </td>
                                <td scope="col" class="text-right">
                                    <a href="/admin/user?id=${user.id}" class="btn btn-secondary btn-sm">
                                        <i class="bi bi-pencil-square"></i>
                                    </a>
                                    <button type="button" class="btn btn-outline-danger btn-sm" data-toggle="modal"
                                            data-target="#deleteUserModal" data-id="${user.id}"><i class="bi bi-trash-fill"></i>
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

<!-- DELETE USER MODAL -->
<div class="modal fade" id="deleteUserModal" tabindex="-1" aria-labelledby="exampleModalLabel"
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
                <h3 class="text-center font-weight-bold"><fmt:message key="label.delete.confirm"/></h3>
            </div>
            <div class="modal-footer">
                <form action="/admin/user/delete" method="post">
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

<!-- ADD USER MODAL -->
<div class="modal fade" id="addUserModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/admin/user/add" method="post" id="addUserForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name"><fmt:message key="label.name"/> </label>
                        <input type="text" name="name" id="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="email"><fmt:message key="label.email"/> </label>
                        <input type="email" name="email" id="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="label.password"/></label>
                        <input class="form-control" type="password" name="password" id="password" required>
                    </div>
                    <div class="form-group">
                        <label for="reNewPass"><fmt:message key="label.password.confirm"/></label>
                        <input class="form-control" type="password" name="reNewPass" id="reNewPass" required>
                    </div>
                    <div class="form-group">
                        <label for="role"><fmt:message key="button.role.all"/> </label>
                        <select class="form-control" name="role" id="role">
                            <c:forEach var="role" items="${roles}">
                                <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="button.close"/></button>
                    <button type="submit" class="btn btn-info"><fmt:message key="button.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- END OF MODAL -->

<%@include file="layout/footer.jsp"%>

<script>
    $('#deleteUserModal').on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget)
        let id = button.data('id')
        const modal = $(this)
        modal.find('#lol').val(id)
    });

    $('#addUserForm').validate({
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
