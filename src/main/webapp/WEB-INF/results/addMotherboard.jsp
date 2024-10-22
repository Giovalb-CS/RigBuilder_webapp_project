<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Motherboard - RigBuilder</title>
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

        <%if (request.getAttribute("alreadyExists")!=null) {%>
        <div class="messageContainer failure">
            <h3>The component you tried to add already exists.</h3>
        </div>
        <%}%>
        <%if (request.getAttribute("formError")!=null){%>
        <div class="messageContainer failure">
            <h3>There was an error with the parameters of the form. Please, try again.</h3>
        </div>
        <%}%>

        <form action="addMotherboard" method="post" class="form-container">

            <div class="img-container">
                <img src="" alt="component-image" id="preview-image">
                <textarea name="image_URL" id="image_URL" cols="30" rows="10" placeholder="Insert image URL here..." required></textarea>
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
                <textarea name="shop_URL" id="shop_URL" cols="30" rows="10" placeholder="Insert shop URL here..." required></textarea>
            </div>

            <div class="input-container">
                <label for="tdp">TDP</label>
                <input type="number" name="tdp" id="tdp" step="1" placeholder="∽80W max" required>
            </div>

            <div class="input-container">
                <label for="socket">Socket</label>
                <input list="socketList" id="socket" name="socket" placeholder="Select or insert a new socket" required>
                <datalist id="socketList">
                    <% List<String> sockets = (List<String>) request.getAttribute("sockets");
                        for (String socket : sockets) { %>
                    <option value="<%= socket %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="chipset">Chipset</label>
                <input list="chipsetList" id="chipset" name="chipset" placeholder="Select or insert a new chipset" required>
                <datalist id="chipsetList">
                    <% List<String> chipsets = (List<String>) request.getAttribute("chipsets");
                        for (String chipset : chipsets) { %>
                    <option value="<%= chipset %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="ramType">RAM Type</label>
                <input list="ramTypeList" id="ramType" name="ramType" placeholder="Select or insert a new RAM Type" required>
                <datalist id="ramTypeList">
                    <% List<String> ramTypes = (List<String>) request.getAttribute("ramTypes");
                        for (String ramType : ramTypes) { %>
                    <option value="<%= ramType %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="ramMaxSpeed">RAM max speed</label>
                <input type="number" name="ramMaxSpeed" id="ramMaxSpeed" step=1" min="0" required>
            </div>

            <div class="input-container">
                <label for="ramSlots">RAM slots</label>
                <input type="number" name="ramSlots" id="ramSlots" step=1" min="0" required>
            </div>

            <div class="input-container">
                <label for="ramMaxCapacity">RAM max capacity</label>
                <input type="number" name="ramMaxCapacity" id="ramMaxCapacity" step=1" min="0" required>
            </div>

            <div class="input-container">
                <label for="pciex16slots">PCIe x16 slots</label>
                <input type="number" name="pciex16slots" id="pciex16slots" step=1" min="0" required>
            </div>

            <div class="input-container">
                <label for="pciex1slots">PCIe x1 slots</label>
                <input type="number" name="pciex1slots" id="pciex1slots" step=1" min="0" required>
            </div>

            <div class="input-container">
                <label for="m2slots">NVMe M.2 slots</label>
                <input type="number" name="m2slots" id="m2slots" step=1" min="0" required>
            </div>

            <div class="input-container">
                <label for="sataslots">SATA slots</label>
                <input type="number" name="sataslots" id="sataslots" step=1" min="0" required>
            </div>

            <div class="input-container">
                <label for="lanType">LAN Type</label>
                <input list="lanTypeList" id="lanType" name="lanType" placeholder="Select or insert a new LAN Type" required>
                <datalist id="lanTypeList">
                    <% List<String> lanTypes = (List<String>) request.getAttribute("lanTypes");
                        for (String lanType : lanTypes) { %>
                    <option value="<%= lanType %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="wifiType">Wi-Fi Type</label>
                <input list="wifiTypeList" id="wifiType" name="wifiType" placeholder="Select or insert a new Wi-Fi Type" required>
                <datalist id="wifiTypeList">
                    <% List<String> wifiTypes = (List<String>) request.getAttribute("wifiTypes");
                        for (String wifiType : wifiTypes) { %>
                    <option value="<%= wifiType %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="formFactor">Form Factor</label>
                <input list="formFactorList" id="formFactor" name="formFactor" placeholder="Select or insert a new form factor" required>
                <datalist id="formFactorList">
                    <% List<String> formFactors = (List<String>) request.getAttribute("formFactors");
                        for (String formFactor : formFactors) { %>
                    <option value="<%= formFactor %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <button type="submit"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Add</button>
            </div>
        </form>

        <%@include file="/WEB-INF/results/modules/footer.jsp"%>
    </body>
</html>
