<%@ page import="java.util.List" %>
<%@ page import="model.SSD" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add SSD - RigBuilder</title>
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

        <%SSD ssd_before = (SSD) request.getAttribute("ssd");%>

        <form action="editSsd" method="post" class="form-container">
            <input type="hidden" name="id" value="<%=ssd_before.getId()%>">
            <div class="img-container">
                <img src="" alt="component-image" id="preview-image">
                <textarea name="image_URL" id="image_URL" cols="30" rows="10" placeholder="Insert image URL here..." required><%=ssd_before.getImage_URL()%></textarea>
            </div>
            <script src="${pageContext.request.contextPath}/js/updateFormImage.js" type="text/javascript"></script>

            <div class="input-container">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="<%=ssd_before.getName()%>" required>
            </div>

            <div class="input-container">
                <label for="rating">Rating</label>
                <input type="number" name="rating" id="rating" step="0.1" min="0" max="5" value="<%=ssd_before.getRating()%>" required>
            </div>

            <div class="input-container">
                <label for="price">Price</label>
                <input type="number" name="price" id="price" step="0.1" min="0" value="<%=ssd_before.getPrice()%>" required>
            </div>

            <div class="input-container">
                <label for="shop_URL">Product URL</label>
                <textarea name="shop_URL" id="shop_URL" cols="30" rows="10" placeholder="Insert shop URL here..." required><%=ssd_before.getShop_URL()%></textarea>
            </div>

            <div class="input-container">
                <label for="tdp">TDP</label>
                <input type="number" name="tdp" id="tdp" step="1" value="<%=ssd_before.getTdp()%>" required>
            </div>

            <div class="input-container">
                <label for="pcie_gen">PCIe Gen</label>
                <input list="pcie_genList" id="pcie_gen" name="pcie_gen" placeholder="Select or insert generation" value="<%=ssd_before.getPcie_gen()%>" required>
                <datalist id="pcie_genList">
                    <% List<String> pcie_gens = (List<String>) request.getAttribute("pcie_gens");
                        for (String pcie_gen : pcie_gens) { %>
                    <option value="<%= pcie_gen %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="capacity">Capacity</label>
                <input list="capacityList" id="capacity" name="capacity" placeholder="Select or insert a new capacity" value="<%=ssd_before.getCapacity()%>" required>
                <datalist id="capacityList">
                    <% List<String> capacities = (List<String>) request.getAttribute("capacities");
                        for (String capacity : capacities) { %>
                    <option value="<%= capacity %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="speed_read">Speed Read</label>
                <input type="number" name="speed_read" id="speed_read" step="1" value="<%=ssd_before.getSpeed_read()%>" required>
            </div>

            <div class="input-container">
                <label for="speed_write">Speed Write</label>
                <input type="number" name="speed_write" id="speed_write" step="1" value="<%=ssd_before.getSpeed_write()%>" required>
            </div>

            <div class="input-container">
                <button type="submit"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Add</button>
            </div>
        </form>

        <%@include file="/WEB-INF/results/modules/footer.jsp"%>
    </body>
</html>
