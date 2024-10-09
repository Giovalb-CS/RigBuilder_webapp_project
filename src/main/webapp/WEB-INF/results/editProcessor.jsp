<%@ page import="java.util.List" %>
<%@ page import="model.Processor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Processor - RigBuilder</title>
        <link rel="icon" href="${pageContext.request.contextPath}/images/RigBuilder_site_watermark.png" type="image/x-icon">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addForm.css" type="text/css">
        <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body id="top">
        <%@include file="/WEB-INF/results/modules/isAdminLogged.jsp"%>
        <%@include file="/WEB-INF/results/modules/navbar.jsp"%>
        <%@include file="/WEB-INF/results/modules/backtotopbutton.jsp"%>

        <%if (request.getAttribute("formError")!=null){%>
            <div class="messageContainer failure">
                <h3>There was an error with the parameters of the form. Please, try again.</h3>
            </div>
        <%}%>

        <%Processor processor_before = (Processor) request.getAttribute("processor");%>

        <form action="editProcessor" method="post" class="form-container">
            <input type="hidden" name="id" value="<%=processor_before.getId()%>">
            <div class="img-container">
                <img src="" alt="component-image" id="preview-image">
                <textarea name="image_URL" id="image_URL" cols="30" rows="10" placeholder="Insert image URL here..." required><%=processor_before.getImage_URL()%></textarea>
            </div>
            <script src="${pageContext.request.contextPath}/js/updateFormImage.js" type="text/javascript"></script>

            <div class="input-container">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="<%=processor_before.getName()%>" required>
            </div>

            <div class="input-container">
                <label for="rating">Rating</label>
                <input type="number" name="rating" id="rating" step="0.1" min="0" max="5" value="<%=processor_before.getRating()%>" required>
            </div>

            <div class="input-container">
                <label for="price">Price</label>
                <input type="number" name="price" id="price" step="0.1" min="0" value="<%=processor_before.getPrice()%>" required>
            </div>

            <div class="input-container">
                <label for="shop_URL">Product URL</label>
                <textarea name="shop_URL" id="shop_URL" cols="30" rows="10" placeholder="Insert shop URL here..." required><%=processor_before.getShop_URL()%></textarea>
            </div>

            <div class="input-container">
                <label for="tdp">TDP</label>
                <input type="number" name="tdp" id="tdp" step="1" value="<%=processor_before.getTdp()%>" required>
            </div>

            <div class="input-container">
                <label for="socket">Socket</label>
                <input list="socketList" id="socket" name="socket" placeholder="Select or insert a new socket" value="<%=processor_before.getSocket()%>" required>
                <datalist id="socketList">
                    <% List<String> sockets = (List<String>) request.getAttribute("sockets");
                        for (String socket : sockets) { %>
                    <option value="<%= socket %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="ramType">Ram Type</label>
                <input list="ramTypeList" id="ramType" name="ramType" placeholder="Select or insert a new RAM Type" value="<%=processor_before.getRam_type()%>" required>
                <datalist id="ramTypeList">
                    <% List<String> ramTypes = (List<String>) request.getAttribute("ramTypes");
                        for (String ramType : ramTypes) { %>
                    <option value="<%= ramType %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="core">Core count</label>
                <input type="number" name="core" id="core" step="1" value="<%=processor_before.getCore()%>" required>
            </div>

            <div class="input-container">
                <label for="thread">Threads</label>
                <input type="number" name="thread" id="thread" step="1" value="<%=processor_before.getThread()%>" required>
            </div>

            <div class="input-container">
                <label for="clock_base">Base Clock</label>
                <input type="number" name="clock_base" id="clock_base" step="0.1" value="<%=processor_before.getClock_base()%>" required>
            </div>

            <div class="input-container">
                <label for="clock_boost">Boost Clock</label>
                <input type="number" name="clock_boost" id="clock_boost" step="0.1" value="<%=processor_before.getClock_boost()%>" required>
            </div>

            <div class="input-container">
                <label for="cache">Cache</label>
                <input type="number" name="cache" id="cache" step="1" value="<%=processor_before.getCache()%>" required>
            </div>

            <div class="input-container">
                <label for="scale">Scale</label>
                <input type="number" name="scale" id="scale" step="1" value="<%=processor_before.getScale()%>" required>
            </div>

            <div class="input-container">
                <label for="generation">Generation</label>
                <input type="text" name="generation" id="generation" value="<%=processor_before.getGeneration()%>" required>
            </div>

            <div class="input-container">
                <button type="submit"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>
            </div>
        </form>

        <%@include file="/WEB-INF/results/modules/footer.jsp"%>
    </body>
</html>
