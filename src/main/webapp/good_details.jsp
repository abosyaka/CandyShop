<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <%@include file="layout/head.jsp"%>
</head>
<body>
<%@include file="layout/navbar.jsp"%>
    <div class="container">
        <div class="row mt-5">
            <div class="col-7 item-photo d-flex flex-column">
                <img style="max-width:100%;max-height: 350px;object-fit: cover;" class="img-fluid img-thumbnail" src="${good.pictureUrl}"/>
            </div>
            <div class="col-5" style="border:0px solid gray">
                <h3>${good.name}</h3>
                <h5 style="color:#337ab7"><span>${good.category.name}</span></h5>

                <h6 class="title-price"><fmt:message key="label.price"/></h6>
                <h3 class="mt-0" >${good.price} ₸</h3>

                <div class="section" style="padding-bottom:5px;">
                    <h6 class="font-weight-bold"><fmt:message key="label.storage.period" /></h6>
                    <span>${good.storagePeriod}</span>
                </div>
                <div class="pb-4">
                    <h6 class="font-weight-bold"><fmt:message key="label.ingredients" /></h6>
                    <span>${good.ingredients}</span>
                </div>

                <div class="section" style="padding-bottom:20px;">
                    <form action="/addToCart" method="post">
                        <input type="hidden" name="id" value="${good.id}">
                        <button type="submit" class="btn btn-success">
                            <fmt:message key="button.cart.add" />
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div class="row mt-3">
            <h4 class="font-weight-lighter">${good.description}</h4>
        </div>
    </div>
    <%@include file="layout/footer.jsp"%>
</body>
</html>
