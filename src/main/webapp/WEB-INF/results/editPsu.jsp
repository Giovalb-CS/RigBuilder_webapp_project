<%@ page import="java.util.List" %>
<%@ page import="model.PSU" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit PSU - RigBuilder</title>
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

        <%PSU psu_before = (PSU) request.getAttribute("psu");%>

        <form action="editPsu" method="post" class="form-container">
            <input type="hidden" name="id" value="<%=psu_before.getId()%>">
            <div class="img-container">
                <img src="" alt="component-image" id="preview-image">
                <textarea name="image_URL" id="image_URL" cols="30" rows="10" placeholder="Insert image URL here..." required><%=psu_before.getImage_URL()%></textarea>
            </div>
            <script src="${pageContext.request.contextPath}/js/updateFormImage.js" type="text/javascript"></script>

            <div class="input-container">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="<%=psu_before.getName()%>" required>
            </div>

            <div class="input-container">
                <label for="rating">Rating</label>
                <input type="number" name="rating" id="rating" step="0.1" min="0" max="5" value="<%=psu_before.getRating()%>" required>
            </div>

            <div class="input-container">
                <label for="price">Price</label>
                <input type="number" name="price" id="price" step="0.1" min="0" value="<%=psu_before.getPrice()%>" required>
            </div>

            <div class="input-container">
                <label for="shop_URL">Product URL</label>
                <textarea name="shop_URL" id="shop_URL" cols="30" rows="10" placeholder="Insert shop URL here..." required><%=psu_before.getShop_URL()%></textarea>
            </div>

            <div class="input-container">
                <label for="type">Type</label>
                <input list="typeList" id="type" name="type" placeholder="Select or insert a new type" value="<%=psu_before.getType()%>" required>
                <datalist id="typeList">
                    <% List<String> types = (List<String>) request.getAttribute("types");
                        for (String type : types) { %>
                    <option value="<%= type %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="efficiency">Efficiency Type</label>
                <input list="efficiencyList" id="efficiency" name="efficiency" placeholder="Select or insert a new efficiency" value="<%=psu_before.getEfficiency()%>" required>
                <datalist id="efficiencyList">
                    <% List<String> efficiencies = (List<String>) request.getAttribute("efficiencies");
                        for (String efficiency : efficiencies) { %>
                    <option value="<%= efficiency %>">
                            <% } %>
                </datalist>
            </div>

            <div class="input-container">
                <label for="wattage">Wattage</label>
                <input type="number" name="wattage" id="wattage" step="1" value="<%=psu_before.getWattage()%>" required>
            </div>

            <div class="input-container">
                <label for="lenght">Length</label>
                <input type="number" name="lenght" id="lenght" step="1" value="<%=psu_before.getLenght()%>" required>
            </div>

            <div class="input-container">
                <button type="submit"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>
            </div>
        </form>

        <%@include file="/WEB-INF/results/modules/footer.jsp"%>
    </body>
</html>
