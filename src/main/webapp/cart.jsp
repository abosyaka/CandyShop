<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title><fmt:message key="label.cart" /></title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>
<div class="container mt-5">
    <div class="card shopping-cart">
        <div class="card-header bg-dark text-light">
            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
            <span><fmt:message key="label.cart"/></span>
            <a href="/" class="btn btn-outline-info btn-sm pull-right ml-2"><fmt:message
                    key="button.cart.continue"/></a>
            <div class="clearfix"></div>
        </div>
        <div class="card-body">
            <c:if test="${sessionScope.cart.isEmpty()}">
                <div class="row justify-content-center">
                    <h2 class="font-weight-bold text-center"><fmt:message key="label.cart.empty"/></h2>
                </div>
            </c:if>
            <!-- PRODUCT -->
            <c:forEach var="good" items="${sessionScope.cart.keySet()}">
                <div class="row mb-3 border-bottom pb-3">
                    <div class="col-12 col-sm-12 col-md-2 text-center">
                        <img style="object-fit: contain" class="img-responsive" src="/image/upload/${good.pictureUrl}"
                             alt="prewiew" width="120" height="80">
                    </div>
                    <div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                        <a href="/good?id=${good.id}"><h4 class="product-name"><strong
                                style="color: black">${good.name}</strong>
                        </h4></a>
                        <h4>
                            <small>${good.description}</small>
                        </h4>
                    </div>
                    <div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                        <div class="col-3 col-sm-3 col-md-6 text-md-right" style="padding-top: 5px">
                            <h6><strong>${good.price} ₸<span class="text-muted"></span></strong></h6>
                        </div>
                        <div class="col-4 col-sm-4 col-md-4">
                            <div class="quantity">
                                <form action="/addToCart" method="post" class="m-0">
                                    <input type="hidden" value="${good.id}" name="id">
                                    <input type="submit" value="+" class="plus">
                                </form>
                                <input type="number" step="1" max="99" min="1" value="${sessionScope.cart.get(good)}"
                                       title="Qty"
                                       class="qty"
                                       size="4" readonly>
                                <form action="/deleteFromCart" method="post" class="m-0">
                                    <input type="hidden" value="true" name="isOne">
                                    <input type="hidden" value="${good.id}" name="id">
                                    <input type="submit" value="-" class="minus">
                                </form>
                            </div>
                        </div>
                        <div class="col-2 col-sm-2 col-md-2 text-right">
                            <form action="/deleteFromCart" method="post">
                                <input type="hidden" value="${good.id}" name="id">
                                <button type="submit" class="btn btn-outline-danger btn-xs">
                                    <i class="bi bi-trash-fill"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <!-- END PRODUCT -->

        </div>

        <c:if test="${!sessionScope.cart.isEmpty()}">
            <div class="card-footer">
                <div class="pull-right d-flex justify-content-center" style="margin: 10px">
                    <button class="btn btn-success pull-right w-50 mx-auto" data-toggle="modal"
                            data-target="#payModal"><fmt:message key="button.cart.checkout"/></button>
                    <div class="pull-right" style="margin: 5px">
                        <span><fmt:message key="label.cart.total"/></span>: <b>${totalPrice} ₸</b>
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <!--MODAL-->
    <div class="modal fade" id="payModal" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="/purchase" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="inputAddress2"><fmt:message key="label.name.full" /></label>
                            <input type="text" class="form-control" id="inputAddress2" required>
                        </div>
                        <div class="form-group">
                            <label for="inputAddress2"><fmt:message key="label.card" /></label>
                            <input type="text" class="form-control" required>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputEmail4"><fmt:message key="label.date.expiration" /></label>
                                <input type="month" class="form-control" id="inputEmail4" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputPassword4">CVV</label>
                                <input type="text" class="form-control" id="inputPassword4" required>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="button.close" /></button>
                        <button type="submit" class="btn btn-danger"><fmt:message key="button.send" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--MODAL-->
</div>

<%@include file="layout/footer.jsp" %>
</body>
</html>
