<%@ page import="model.Cooler" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Cooler - RigBuilder</title>
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

        <%Cooler cooler_before = (Cooler) request.getAttribute("cooler");%>

        <form action="editCooler" method="post" class="form-container">
            <input type="hidden" name="id" value="<%=cooler_before.getId()%>">
            <div class="img-container">
                <img src="" alt="component-image" id="preview-image">
                <textarea name="image_URL" id="image_URL" cols="30" rows="10" placeholder="Insert image URL here..."
                          required><%=cooler_before.getImage_URL()%></textarea>
            </div>
            <script src="${pageContext.request.contextPath}/js/updateFormImage.js" type="text/javascript"></script>

            <div class="input-container">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="<%=cooler_before.getName()%>" required>
            </div>

            <div class="input-container">
                <label for="rating">Rating</label>
                <input type="number" name="rating" id="rating" step="0.1" min="0" max="5" value="<%=cooler_before.getRating()%>" required>
            </div>

            <div class="input-container">
                <label for="price">Price</label>
                <input type="number" name="price" id="price" step="0.1" min="0" value="<%=cooler_before.getPrice()%>" required>
            </div>

            <div class="input-container">
                <label for="shop_URL">Product URL</label>
                <textarea name="shop_URL" id="shop_URL" cols="30" rows="10" placeholder="Insert shop URL here..."
                          required><%=cooler_before.getShop_URL()%></textarea>
            </div>

            <div class="input-container">
                <label for="tdp">TDP</label>
                <input type="number" name="tdp" id="tdp" step="1" value="<%=cooler_before.getTdp()%>" required>
            </div>

            <div class="input-container">
                <label for="socket">Socket</label>
                <input list="socketList" id="socketInput" name="socketInput" placeholder="Select or insert a new socket">
                <datalist id="socketList">
                    <% List<String> sockets = (List<String>) request.getAttribute("sockets");
                        for (String socket : sockets) { %>
                    <option value="<%= socket %>">
                            <% } %>
                </datalist>
                <div id="selectedSocketsContainer"></div>
                <input type="hidden" id="socket" name="socket">
            </div>
            <div id="socketData" data-sockets="<%= cooler_before.getSocket() %>"></div>
            <script src="${pageContext.request.contextPath}/js/loadSocketSelection.js"></script>

            <div class="input-container">
                <label for="rpm">RPM</label>
                <input type="number" name="rpm" id="rpm" step="1" value="<%=cooler_before.getRpm()%>" required>
            </div>

            <div class="input-container">
                <label for="noiseLevel">Noise Level</label>
                <input type="number" name="noiseLevel" id="noiseLevel" step="1" value="<%=cooler_before.getNoise_level()%>" required>
            </div>

            <div class="input-container">
                <label for="radiatorSize">Radiator size</label>
                <input list="radiatorSizeList" id="radiatorSize" name="radiatorSize" placeholder="Select or insert a new radiator size" value="<%= cooler_before.getRadiator_size() != null && cooler_before.getRadiator_size() != 0 ? cooler_before.getRadiator_size() : "" %>">
                <datalist id="radiatorSizeList">
                    <% List<String> radiatorSizes = (List<String>) request.getAttribute("radiatorSizes");
                        for (String size : radiatorSizes) { %>
                    <option value="<%= size %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="coolerHeight">Cooler Height</label>
                <input type="number" name="coolerHeight" id="coolerHeight" step="1" value="<%= cooler_before.getCooler_height() != null && cooler_before.getCooler_height() != 0 ? cooler_before.getCooler_height() : "" %>">
            </div>

            <div class="input-container messageContainer">
                <label for="coolerType">Cooler type is:</label>
                <p id="coolerType"></p>
            </div>

            <script>
            // Passaggio dei valori direttamente al caricamento della pagina
            const existingRadiatorSize = "<%= cooler_before.getRadiator_size() != null && cooler_before.getRadiator_size() != 0 ? cooler_before.getRadiator_size() : "" %>";
            const existingCoolerHeight = "<%= cooler_before.getCooler_height() != null && cooler_before.getCooler_height() != 0 ? cooler_before.getCooler_height() : "" %>";
            </script>
            <script src="${pageContext.request.contextPath}/js/loadCoolerToggleSwitch.js"></script>

            <div class="input-container">
                <button type="submit"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>
            </div>
        </form>

        <%@include file="/WEB-INF/results/modules/footer.jsp"%>
    </body>
</html>
