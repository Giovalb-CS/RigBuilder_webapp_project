<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Case - RigBuilder</title>
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

        <%if (request.getAttribute("alreadyExists") != null) {%>
        <div class="messageContainer failure">
            <h3>The component you tried to add already exists.</h3>
        </div>
        <%}%>
        <%if (request.getAttribute("formError") != null) {%>
        <div class="messageContainer failure">
            <h3>There was an error with the parameters of the form. Please, try again.</h3>
        </div>
        <%}%>

        <form action="addCase" method="post" class="form-container">

            <div class="img-container">
                <img src="" alt="component-image" id="preview-image">
                <textarea name="image_URL" id="image_URL" cols="30" rows="10" placeholder="Insert image URL here..."
                          required></textarea>
            </div>
            <script src="${pageContext.request.contextPath}/js/updateFormImage.js" type="text/javascript"></script>

            <div class="input-container">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" required>
            </div>

            <div class="input-container">
                <label for="rating">Rating</label>
                <input type="number" name="rating" id="rating" step="0.1" min="0" max="5" required>
            </div>

            <div class="input-container">
                <label for="price">Price</label>
                <input type="number" name="price" id="price" step="0.1" min="0" required>
            </div>

            <div class="input-container">
                <label for="shop_URL">Product URL</label>
                <textarea name="shop_URL" id="shop_URL" cols="30" rows="10" placeholder="Insert shop URL here..."
                          required></textarea>
            </div>

            <div class="input-container">
                <label for="maxCoolerHeight">Max Cooler Height</label>
                <input type="number" name="maxCoolerHeight" id="maxCoolerHeight" step="1" required>
            </div>

            <div class="input-container">
                <label for="radiatorSize">Radiator size</label>
                <input list="radiatorSizeList" id="radiatorSize" name="radiatorSize" placeholder="Select or insert a new radiator size" required>
                <datalist id="radiatorSizeList">
                    <% List<String> radiatorSizes = (List<String>) request.getAttribute("radiatorSizes");
                        for (String size : radiatorSizes) { %>
                    <option value="<%= size %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="gpuLenght">GPU Length</label>
                <input type="number" name="gpuLenght" id="gpuLenght" step="1" required>
            </div>

            <div class="input-container">
                <label for="formFactor">Form Factor</label>
                <input list="formFactorList" id="formFactorInput" name="formFactorInput" placeholder="Select or insert a new form factor">
                <datalist id="formFactorList">
                    <% List<String> formFactors = (List<String>) request.getAttribute("formFactors");
                        for (String formFactor : formFactors) { %>
                    <option value="<%= formFactor %>">
                            <% } %>
                </datalist>
                <div id="selectedFormFactorsContainer"><p>If on mobile, when typed a new form factor, click here to add it!</p></div>
                <input type="hidden" id="formFactor" name="formFactor">
            </div>
            <script src="${pageContext.request.contextPath}/js/formFactorSelection.js"></script>

            <div class="input-container">
                <label for="psuLenght">PSU Length</label>
                <input type="number" name="psuLenght" id="psuLenght" step="1" required>
            </div>

            <div class="input-container">
                <label for="pcieSlots">PCIe slots</label>
                <input type="number" name="pcieSlots" id="pcieSlots" step="1" required>
            </div>

            <div class="input-container">
                <button type="submit"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Add</button>
            </div>
        </form>

        <%@include file="/WEB-INF/results/modules/footer.jsp" %>
    </body>
</html>
