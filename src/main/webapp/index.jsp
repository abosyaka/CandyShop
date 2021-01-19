<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <%@include file="layout/head.jsp" %>
    <title>LOLKA</title>
</head>
<body>
<%@include file="layout/navbar.jsp" %>
    <div class="container">
        <div class="row mt-5">
            <div class="d-flex flex-column col col-md-3">
                <div class="list-group">
                    <a href="/" class="list-group-item list-group-item-action active" style="background-color: crimson;">
                        <span><fmt:message key="button.category.all" /></span>
                    </a>
                    <c:forEach var="category" items="${categories}">
                        <a href="/?category=${category.id}" class="list-group-item list-group-item-action">
                            <span class="ml-2">${category.name}</span>
                        </a>
                    </c:forEach>
                </div>
            </div>


            <div class="col col-md-9">
                <form action="/" method="get" class="form-inline col-12 p-0">
                    <div class="form-group w-100">
                        <input class="form-control col-9" type="text" name="search">
                        <button class="btn btn-info col-2 ml-auto" type="submit"><fmt:message key="button.search" /></button>
                    </div>
                </form>

                <div class="row row-cols-1 row-cols-md-3 mt-5">
                    <c:forEach var="good" items="${goods}">
                        <div class="col mb-4">
                            <div class="card">
                                <img src="/image/upload/${good.pictureUrl}" class="card-img-top w-100 good-card-img" alt="image">
                                <div class="card-body">
                                    <a style="color: inherit" href="/good?id=${good.id}"><h5
                                            class="card-title">${good.name}</h5></a>
                                    <p class="card-text font-weight-bold">${good.price} ₸</p>
                                    <p class="card-text">${good.description}</p>
                                </div>
                                <div class="card-footer">
                                    <i class="bi bi-alarm"></i> ${good.storagePeriod}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
<%@include file="layout/footer.jsp" %>
</body>
</html>
