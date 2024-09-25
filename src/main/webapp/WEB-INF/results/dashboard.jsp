<%@ page import="model.Administrator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - RigBuilder</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" type="text/css">
        <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <%
        Administrator administrator = (Administrator) session.getAttribute("administrator");
        %>
        Welcome, <%=administrator.getEmail().split("@")[0]%>


        <%@include file="/WEB-INF/results/modules/footer.jsp"%>

    </body>
</html>
