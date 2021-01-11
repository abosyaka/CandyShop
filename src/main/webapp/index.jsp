
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>


<!DOCTYPE HTML>
<html lang="${cookie['lang'].value}">
<head>
    <%@include file="layout/head.jsp"%>
    <title>LOLKA</title>
<%--    <link rel="stylesheet" type="text/css" href=".././styles/style.css" />--%>
</head>
<body>
<%@include file="layout/navbar.jsp"%>
<h2>${pageContext.request.contextPath}</h2>
<%@include file="layout/footer.jsp"%>
</body>
</html>
