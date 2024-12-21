<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Casebox" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String fileName = request.getServletPath().substring(request.getServletPath().lastIndexOf("/")+1).substring(0, request.getServletPath().substring(request.getServletPath().lastIndexOf("/")+1).lastIndexOf("."));
    String fileNameCapitalized = fileName.substring(0, 1).toUpperCase() + fileName.substring(1).toLowerCase();
%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><%=fileNameCapitalized%> - RigBuilder</title>
        <link rel="icon" href="${pageContext.request.contextPath}/images/RigBuilder_site_watermark.png" type="image/x-icon">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/component-list.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css" type="text/css">
        <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/filterToggle.js" type="text/javascript"></script>
    </head>

    <body id="top">
        <%@include file="/WEB-INF/results/modules/isAdminLogged.jsp"%>
        <%@include file="/WEB-INF/results/modules/navbar.jsp"%>
        <%@include file="/WEB-INF/results/modules/backtotopbutton.jsp"%>

        <%ArrayList<Casebox> caseboxes = (ArrayList<Casebox>) request.getAttribute("caseboxes");%>

        <%--
        <%if (request.getAttribute("addedSuccessfully")!=null) {%>
            <div class="messageContainer success">
                <h3>Added successfully!</h3>
            </div>
        <%}%>
        <%if (request.getAttribute("editedSuccessfully")!=null) {%>
            <div class="messageContainer success">
                <h3>Edited successfully!</h3>
            </div>
        <%}%>
        <%if (request.getAttribute("deletedSuccessfully")!=null) {%>
        <div class="messageContainer success">
            <h3>Edited successfully!</h3>
        </div>
        <%}%>
        <%if (request.getAttribute("deleteError")!=null) {%>
        <div class="messageContainer failure">
            <h3>An error occurred while trying to delete the component. Please, try again.</h3>
        </div>
        <%}%>
        --%>

        <form class="add-button-container">
            <button type="submit" formmethod="get" formaction="addCase"><i class="fa fa-plus-square-o" aria-hidden="true"></i>Add <%=fileName.substring(0, fileName.length()-1)%></button>
        </form>

        <%if (caseboxes == null || caseboxes.isEmpty()){%>
        <div class="no-items-container">
            <p>No <%=fileName%> found in the database or no components with selected filter found. Add some.</p>
            <form class="filter-reset-button-container" action="cases" method="get">
                <button type="submit"><i class="fa fa-refresh" aria-hidden="true"></i>Refresh</button>
            </form>
        </div>
        <%} else {%>
        <div class="filters-toggle">
            <button id="toggle-filters">Show Filters</button>
        </div>

        <div class="filters-container">
            <!-- Sezione filtri singoli -->
            <div class="main-filters-container">
                <p>Single filters</p>

                <!-- Campo ID -->
                <form action="cases" method="get" class="filter-item">
                    <label for="id">ID:</label>
                    <input type="number" id="id" name="id">
                    <button type="submit">Filter by ID</button>
                </form>

                <!-- Campo Nome -->
                <form action="cases" method="get" class="filter-item">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name">
                    <button type="submit">Filter by Name</button>
                </form>

                <!-- Rating Asc/Desc -->
                <form action="cases" method="get" class="filter-item">
                    <label>Rating:</label>
                    <button type="submit" name="ratingSort" value="asc">Asc</button>
                    <button type="submit" name="ratingSort" value="desc">Desc</button>
                </form>

                <!-- Prezzo Asc/Desc -->
                <form action="cases" method="get" class="filter-item">
                    <label>Price:</label>
                    <button type="submit" name="priceSort" value="asc">Asc</button>
                    <button type="submit" name="priceSort" value="desc">Desc</button>
                </form>

                <!-- Prezzo tra X e Y -->
                <form action="cases" method="get" class="filter-item">
                    <label for="minPrice">Min Price:</label>
                    <input type="number" id="minPrice" name="minPrice" step="0.01" required>
                    <label for="maxPrice">Max Price:</label>
                    <input type="number" id="maxPrice" name="maxPrice" step="0.01" required>
                    <button type="submit">Filter by Price Range</button>
                </form>

                <form action="cases" method="get" class="filter-item">
                    <label for="minCoolerHeight">Min Cooler Height:</label>
                    <input type="number" id="minCoolerHeight" name="minCoolerHeight" step="1" required>
                    <button type="submit">Filter by cooler height</button>
                </form>

                <form action="cases" method="get" class="filter-item">
                    <label for="minRadiatorSize">Radiator Size:</label>
                    <select id="minRadiatorSize" name="minRadiatorSize">
                        <option value="">All</option>
                        <% List<String> radiatorSizes = (List<String>) request.getAttribute("radiatorSizes");
                            for (String radiatorSize : radiatorSizes) { %>
                        <option value="<%= radiatorSize %>"><%= radiatorSize %></option>
                        <% } %>
                    </select>
                    <button type="submit">Filter by radiator size</button>
                </form>

                <form action="cases" method="get" class="filter-item">
                    <label for="minGPULength">Min GPU Length:</label>
                    <input type="number" id="minGPULength" name="minGPULength" step="1" required>
                    <button type="submit">Filter by GPU length</button>
                </form>

                <form action="cases" method="get" class="filter-item">
                    <label for="formFactor">Form Factor:</label>
                    <select id="formFactor" name="formFactor">
                        <option value="">All</option>
                        <% List<String> formFactors = (List<String>) request.getAttribute("formFactors");
                            for (String formFactor : formFactors) { %>
                        <option value="<%= formFactor %>"><%= formFactor %></option>
                        <% } %>
                    </select>
                    <button type="submit">Filter by form factor</button>
                </form>

                <form action="cases" method="get" class="filter-item">
                    <label for="minPSULength">Min PSU Length:</label>
                    <input type="number" id="minPSULength" name="minPSULength" step="1" required>
                    <button type="submit">Filter by PSU length</button>
                </form>

                <form action="cases" method="get" class="filter-item">
                    <label for="minPCIeSlots">Min PCIe Slots:</label>
                    <input type="number" id="minPCIeSlots" name="minPCIeSlots" step="1" required>
                    <button type="submit">Filter by PCIe slots</button>
                </form>
            </div>

            <!-- Sezione filtri compositi -->
            <div class="composite-filter-container">
                <p>Composite Filter</p>
                <form action="cases" method="get">
                    <!-- Prezzo tra X e Y -->
                    <label for="compositeMinPrice">Min Price:</label>
                    <input type="number" id="compositeMinPrice" name="compositeMinPrice" step="0.01">
                    <label for="compositeMaxPrice">Max Price:</label>
                    <input type="number" id="compositeMaxPrice" name="compositeMaxPrice" step="0.01">

                    <!-- Rating tra X e Y -->
                    <label for="compositeMinRating">Min Rating:</label>
                    <input type="number" id="compositeMinRating" name="compositeMinRating" step="0.1" min="0" max="5">
                    <label for="compositeMaxRating">Max Rating:</label>
                    <input type="number" id="compositeMaxRating" name="compositeMaxRating" step="0.1" min="0" max="5">

                    <label for="compositeminCoolerHeight">Min Cooler Height:</label>
                    <input type="number" id="compositeminCoolerHeight" name="compositeminCoolerHeight" step="1">

                    <label for="compositeminRadiatorSize">Min Radiator Size:</label>
                    <select id="compositeminRadiatorSize" name="compositeminRadiatorSize">
                        <option value="">All</option>
                        <% for (String radiatorSize : radiatorSizes) { %>
                        <option value="<%= radiatorSize %>"><%= radiatorSize %></option>
                        <% } %>
                    </select>

                    <label for="compositeminGPULength">Min GPU Length:</label>
                    <input type="number" id="compositeminGPULength" name="compositeminGPULength" step="1">

                    <label for="compositeFormFactor">Form Factor:</label>
                    <select id="compositeFormFactor" name="compositeFormFactor">
                        <option value="">All</option>
                        <% for (String formFactor : formFactors) { %>
                        <option value="<%= formFactor %>"><%= formFactor %></option>
                        <% } %>
                    </select>

                    <label for="compositeminPSULength">Min PSU Length:</label>
                    <input type="number" id="compositeminPSULength" name="compositeminPSULength" step="1">

                    <label for="compositeMinPCIeSlots">Min PCIe Slots:</label>
                    <input type="number" id="compositeMinPCIeSlots" name="compositeMinPCIeSlots" step="1">

                    <button type="submit">Apply Composite Filter</button>
                </form>
            </div>

            <!-- Tasto Reset -->
            <form class="filter-reset-button-container" action="cases" method="get">
                <button type="submit"><i class="fa fa-refresh" aria-hidden="true"></i>Reset</button>
            </form>
        </div>

        <div class="list-container">
            <%for (Casebox casebox : caseboxes){%>
            <div class="component">
                <img src="<%=casebox.getImage_URL()%>" alt="<%=casebox.getName()%>">

                <div class="component-info-container">
                    <%
                        String componentName = casebox.getName();
                        if (componentName.length() > 60) {
                            componentName = componentName.substring(0, 57) + "...";
                        }
                    %>
                    <div class="group-container name-container">
                        <p class="group-label">Name</p>
                        <p class="group-content"><%=componentName%></p>
                    </div>
                    <div class="group-container">
                        <p class="group-label">ID</p>
                        <p class="group-content"><%=casebox.getId()%></p>
                    </div>
                    <div class="group-container rating-container">
                        <p class="group-label">Rating</p>
                        <p class="group-content"><i class="fa fa-star" aria-hidden="true"></i><%=casebox.getRating()%></p>
                    </div>
                    <div class="group-container">
                        <p class="group-label">Price</p>
                        <p class="group-content">€<%=casebox.getPrice()%></p>
                    </div>
                    <div class="group-container">
                        <p class="group-label">Shop URL</p>
                        <a href="<%=casebox.getShop_URL()%>"><p class="group-content">Link</p></a>
                    </div>
                    <div class="group-container">
                        <p class="group-label">Max Cooler Height</p>
                        <p class="group-content"><%=casebox.getMax_cooler_height()%>mm</p>
                    </div>
                    <div class="group-container">
                        <p class="group-label">Radiator Size</p>
                        <p class="group-content"><%=casebox.getRadiator_size()%>mm</p>
                    </div>
                    <div class="group-container">
                        <p class="group-label">Max GPU Length</p>
                        <p class="group-content"><%=casebox.getGpu_lenght()%>mm</p>
                    </div>
                    <div class="group-container">
                        <p class="group-label">Form Factor</p>
                        <p class="group-content"><%=casebox.getForm_factor()%></p>
                    </div>
                    <div class="group-container">
                        <p class="group-label">Max PSU Length</p>
                        <p class="group-content"><%=casebox.getPsu_lenght()%>mm</p>
                    </div>
                    <div class="group-container">
                        <p class="group-label">PCIe Slots</p>
                        <p class="group-content"><%=casebox.getPcie_slots()%></p>
                    </div>
                </div>

                <form class="component-buttons-container">
                    <input type="hidden" name="id" value="<%=casebox.getId()%>">
                    <button type="submit" formmethod="get" formaction="editCase"><i class="fa fa-pencil" aria-hidden="true"></i>Edit</button>
                    <button type="button" class="remove-button" data-id="<%=casebox.getId()%>" data-filename="<%=fileName%>"><i class="fa fa-times-circle-o" aria-hidden="true"></i>Remove</button>
                </form>
            </div>
            <%}%>
        </div>

        <div id="remove-popup" class="popup">
            <div class="popup-content">
                <h2></h2>
                <form id="remove-form" action="removeCase" method="post">
                    <input type="hidden" id="remove-id" name="id" value="">
                    <div class="popup-buttons">
                        <button type="submit" class="popup-btn">Remove</button>
                        <button type="button" class="popup-btn cancel-remove">Back</button>
                    </div>
                </form>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/removePopup.js" type="text/javascript"></script>
        <%}%>

        <%@include file="/WEB-INF/results/modules/footer.jsp"%>
    </body>
</html>
