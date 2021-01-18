<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title>Title</title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>

<div class="container">
    <a href="/orders" class="btn btn-info ml-3 mt-3 font-weight-bold">
        <i class="bi bi-arrow-bar-left font-weight-bold"></i>
        <fmt:message key="button.back.list"/> </a>
    <div class="card shopping-cart">
        <div class="card-header bg-dark text-light">
            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
            <span><fmt:message key="button.order"/></span>
        </div>
        <div class="card-body">
            <!-- PRODUCT -->
            <c:forEach var="detail" items="${orderDetails}">
                <div class="row mb-3 border-bottom pb-3">
                    <div class="col-12 col-sm-12 col-md-2 text-center">
                        <img style="object-fit: contain" class="img-responsive" src="${detail.good.pictureUrl}"
                             alt="prewiew" width="120" height="80">
                    </div>
                    <div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                        <a href="/good?id=${detail.good.id}"><h4 class="product-name"><strong
                                style="color: black">${detail.good.name}</strong>
                        </h4></a>
                        <h4>
                            <small>${detail.good.description}</small>
                        </h4>
                    </div>
                    <div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                        <div class="col-3 col-sm-3 col-md-6 text-md-right" style="padding-top: 5px">
                            <h6><strong>${detail.good.price} ₸<span class="text-muted"></span></strong></h6>
                        </div>
                        <div class="col-4 col-sm-4 col-md-4">
                            <div class="quantity">
                                <input type="number" step="1" max="99" min="1" value="${detail.count}"
                                       title="Qty"
                                       class="qty"
                                       size="4" readonly>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <!-- END PRODUCT -->

        </div>

        <div class="card-footer">
            <div class="pull-right" style="margin: 10px">
                <div class="pull-right" style="margin: 5px">
                    <span><fmt:message key="label.cart.total"/></span>: <b>${totalPrice} ₸</b>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="layout/footer.jsp" %>
</body>
</html>
