<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title><fmt:message key="button.user.all"/></title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>
<div class="d-flex h-100" id="wrapper">

    <!-- Sidebar -->
    <%@include file="layout/sidebar.jsp" %>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper" class="container-fluid p-0">
        <div class="jumbotron bg-light h-100">
            <div class="row">
                <div class="col-md-4">
                    <h5 class="display-5 font-weight-bold"><fmt:message key="button.good.all"/></h5>
                </div>
                <div class="col-md-2 ml-auto offset-3">
                    <button class="btn btn-secondary offset-5" data-toggle="modal" data-target="#addGoodModal"
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
                            <th scope="col"><fmt:message key="label.price"/></th>
                            <th scope="col"><fmt:message key="label.weight"/></th>
                            <th scope="col" class="text-right"><fmt:message key="label.operations"/></th>
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
                                            data-target="#deleteGoodModal" data-id="${good.id}"><i
                                            class="bi bi-trash-fill"></i>
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

<!-- DELETE GOOD MODAL -->
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
                <h3 class="text-center font-weight-bold"><fmt:message key="label.delete.confirm"/></h3>
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

<!-- ADD GOOD MODAL -->
<div class="modal fade" id="addGoodModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/admin/good/add" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name"><fmt:message key="label.name"/> </label>
                        <input type="text" name="name" id="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="description"><fmt:message key="label.description"/> </label>
                        <input type="text" name="description" id="description" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="ingredients"><fmt:message key="label.ingredients"/></label>
                        <textarea class="form-control" type="text" name="ingredients" id="ingredients" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="category"><fmt:message key="button.category.all"/> </label>
                        <select class="form-control" name="category" id="category">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="price"><fmt:message key="label.price"/></label>
                        <input class="form-control" type="number" min="1" name="price" id="price" required>
                    </div>
                    <div class="form-group">
                        <label for="weight"><fmt:message key="label.weight"/></label>
                        <input class="form-control" type="number" min="0.1" name="weight" id="weight" step="0.01" required>
                    </div>
                    <div class="form-group">
                        <label for="storagePeriod"><fmt:message key="label.storage.period"/></label>
                        <input class="form-control" type="number" min="1" name="storagePeriod" id="storagePeriod" required>
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
