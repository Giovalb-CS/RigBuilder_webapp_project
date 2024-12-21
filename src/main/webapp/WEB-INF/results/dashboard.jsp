<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - RigBuilder</title>
        <link rel="icon" href="${pageContext.request.contextPath}/images/RigBuilder_site_watermark.png" type="image/x-icon">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css" type="text/css">
        <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <%@include file="/WEB-INF/results/modules/isAdminLogged.jsp"%>
        <%@include file="/WEB-INF/results/modules/navbar.jsp"%>

        <%if (request.getAttribute("userCreated")!=null){%>
        <div class="messageContainer success">
            <h3>New user <%=request.getAttribute("newUserID")%>: <%=request.getAttribute("newUserEmail")%> successfully created!</h3>
        </div>
        <%}%>

        <form action="addAdmin" method="get" class="add-admin-container">
            <button type="submit"><i class="fa fa-user-plus" aria-hidden="true"></i> Add a new administrator</button>
        </form>

        <form class="categories">
            <button class="cat-cpu" type="submit" formaction="processors" formmethod="post">
                <img src="${pageContext.request.contextPath}/images/pc-parts-icons/cpu.png" alt="cpu">
                <p>CPUs</p>
            </button>

            <button class="cat-gpu" type="submit" formaction="gpus" formmethod="post">
                <img src="${pageContext.request.contextPath}/images/pc-parts-icons/gpu.png" alt="gpu">
                <p>GPUs</p>
            </button>

            <button class="cat-ram" type="submit" formaction="rams" formmethod="post">
                <img src="${pageContext.request.contextPath}/images/pc-parts-icons/ram.png" alt="ram">
                <p>RAMs</p>
            </button>

            <button class="cat-ssd" type="submit" formaction="ssds" formmethod="post">
                <img src="${pageContext.request.contextPath}/images/pc-parts-icons/ssd.png" alt="ssd">
                <p>SSDs</p>
            </button>

            <button class="cat-mobo" type="submit" formaction="motherboards" formmethod="post">
                <img src="${pageContext.request.contextPath}/images/pc-parts-icons/motherboard.png" alt="motherboard">
                <p>MOBOs</p>
            </button>

            <button class="cat-cooler" type="submit" formaction="coolers" formmethod="post">
                <img src="${pageContext.request.contextPath}/images/pc-parts-icons/cooler.png" alt="cooler">
                <p>Coolers</p>
            </button>

            <button class="cat-psu" type="submit" formaction="psus" formmethod="post">
                <img src="${pageContext.request.contextPath}/images/pc-parts-icons/psu.png" alt="psu">
                <p>PSUs</p>
            </button>

            <button class="cat-casebox" type="submit" formaction="cases" formmethod="post">
                <img src="${pageContext.request.contextPath}/images/pc-parts-icons/casebox.png" alt="casebox">
                <p>Cases</p>
            </button>
        </form>

        <%@include file="/WEB-INF/results/modules/footer.jsp"%>
    </body>
</html>
