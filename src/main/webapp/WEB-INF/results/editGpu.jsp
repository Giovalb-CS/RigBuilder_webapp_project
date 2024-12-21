<%@ page import="model.GPU" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit GPU - RigBuilder</title>
        <link rel="icon" href="${pageContext.request.contextPath}/images/RigBuilder_site_watermark.png" type="image/x-icon">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addForm.css" type="text/css">
        <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body id="top">
        <%@include file="/WEB-INF/results/modules/isAdminLogged.jsp" %>
        <%@include file="/WEB-INF/results/modules/navbar.jsp" %>
        <%@include file="/WEB-INF/results/modules/backtotopbutton.jsp" %>

        <%if (request.getAttribute("formError") != null) {%>
        <div class="messageContainer failure">
            <h3>There was an error with the parameters of the form. Please, try again.</h3>
        </div>
        <%}%>

        <%GPU gpu_before = (GPU) request.getAttribute("gpu");%>

        <form action="editGpu" method="post" class="form-container">
            <input type="hidden" name="id" value="<%=gpu_before.getId()%>">
            <div class="img-container">
                <img src="" alt="component-image" id="preview-image">
                <textarea name="image_URL" id="image_URL" cols="30" rows="10" placeholder="Insert image URL here..." required><%=gpu_before.getImage_URL()%></textarea>
            </div>
            <script src="${pageContext.request.contextPath}/js/updateFormImage.js" type="text/javascript"></script>

            <div class="input-container">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="<%=gpu_before.getName()%>" required>
            </div>

            <div class="input-container">
                <label for="rating">Rating</label>
                <input type="number" name="rating" id="rating" step="0.1" min="0" max="5" value="<%=gpu_before.getRating()%>" required>
            </div>

            <div class="input-container">
                <label for="price">Price</label>
                <input type="number" name="price" id="price" step="0.1" min="0" value="<%=gpu_before.getPrice()%>" required>
            </div>

            <div class="input-container">
                <label for="shop_URL">Product URL</label>
                <textarea name="shop_URL" id="shop_URL" cols="30" rows="10" placeholder="Insert shop URL here..." required><%=gpu_before.getShop_URL()%></textarea>
            </div>

            <div class="input-container">
                <label for="tdp">TDP</label>
                <input type="number" name="tdp" id="tdp" step="1" value="<%=gpu_before.getTdp()%>" required>
            </div>

            <div class="input-container">
                <label for="memoryType">Memory Type</label>
                <input list="memoryTypeList" id="memoryType" name="memoryType" placeholder="Select or insert a new memory type" value="<%=gpu_before.getMemory()%>" required>
                <datalist id="memoryTypeList">
                    <% List<String> memoryTypes = (List<String>) request.getAttribute("memoryTypes");
                        for (String memoryType : memoryTypes) { %>
                            <option value="<%= memoryType %>">
                        <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="memoryClock">Memory Clock</label>
                <input type="number" name="memoryClock" id="memoryClock" step="1" value="<%=gpu_before.getMemory_clock()%>" required>
            </div>

            <div class="input-container">
                <label for="coreClock">Core Clock</label>
                <input type="number" name="coreClock" id="coreClock" step="1" value="<%=gpu_before.getCore_clock()%>" required>
            </div>

            <div class="input-container">
                <label for="boostClock">Boost Clock</label>
                <input type="number" name="boostClock" id="boostClock" step="1" value="<%=gpu_before.getBoost_clock()%>" required>
            </div>

            <div class="input-container">
                <label for="lenght">Lenght</label>
                <input type="number" name="lenght" id="lenght" step="1" value="<%=gpu_before.getLenght()%>" required>
            </div>

            <div class="input-container">
                <label for="slotWidth">Slot Width</label>
                <input type="number" name="slotWidth" id="slotWidth" step="1" value="<%=gpu_before.getSlot_width()%>" required>
            </div>

            <div class="input-container">
                <label for="powerCable">Power Cable</label>
                <input type="text" name="powerCable" id="powerCable" value="<%=gpu_before.getPower_cable()%>" required>
            </div>

            <div class="input-container">
                <button type="submit"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>
            </div>
        </form>

        <%@include file="/WEB-INF/results/modules/footer.jsp" %>
    </body>
</html>
