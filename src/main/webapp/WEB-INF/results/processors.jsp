<%@ page import="model.Processor" %>
<%@ page import="java.util.ArrayList" %>
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
        <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/filterToggle.js" type="text/javascript"></script>
    </head>

    <body>
        <%@include file="/WEB-INF/results/modules/isAdminLogged.jsp"%>
        <%@include file="/WEB-INF/results/modules/navbar.jsp"%>

        <%ArrayList<Processor> processors = (ArrayList<Processor>) request.getAttribute("processors");%>

        <%if (request.getAttribute("addedSuccessfully")!=null) {%>
            <div class="messageContainer success">
                <h3>Added successfully!</h3>
            </div>
        <%}%>

        <form class="add-button-container">
            <button type="submit" formmethod="get" formaction="addProcessor"><i class="fa fa-plus-square-o" aria-hidden="true"></i>Add <%=fileName.substring(0, fileName.length()-1)%></button>
        </form>

        <%if (processors == null || processors.isEmpty()){%>
            <div class="no-items-container">
                <p>No <%=fileName%> found in the database or no components with selected filter found. Add some.</p>
                <form class="filter-reset-button-container" action="processors" method="get">
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
                    <form action="processors" method="get" class="filter-item">
                        <label for="id">ID:</label>
                        <input type="number" id="id" name="id">
                        <button type="submit">Filter by ID</button>
                    </form>

                    <!-- Campo Nome -->
                    <form action="processors" method="get" class="filter-item">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name">
                        <button type="submit">Filter by Name</button>
                    </form>

                    <!-- Rating Asc/Desc -->
                    <form action="processors" method="get" class="filter-item">
                        <label>Rating:</label>
                        <button type="submit" name="ratingSort" value="asc">Asc</button>
                        <button type="submit" name="ratingSort" value="desc">Desc</button>
                    </form>

                    <!-- Prezzo Asc/Desc -->
                    <form action="processors" method="get" class="filter-item">
                        <label>Price:</label>
                        <button type="submit" name="priceSort" value="asc">Asc</button>
                        <button type="submit" name="priceSort" value="desc">Desc</button>
                    </form>

                    <!-- Prezzo tra X e Y -->
                    <form action="processors" method="get" class="filter-item">
                        <label for="minPrice">Min Price:</label>
                        <input type="number" id="minPrice" name="minPrice" step="0.01" required>
                        <label for="maxPrice">Max Price:</label>
                        <input type="number" id="maxPrice" name="maxPrice" step="0.01" required>
                        <button type="submit">Filter by Price Range</button>
                    </form>

                    <!-- Select per Socket -->
                    <form action="processors" method="get" class="filter-item">
                        <label for="socket">Socket:</label>
                        <select id="socket" name="socket">
                            <option value="">All</option>
                            <% List<String> sockets = (List<String>) request.getAttribute("sockets");
                                for (String socket : sockets) { %>
                            <option value="<%= socket %>"><%= socket %></option>
                            <% } %>
                        </select>
                        <button type="submit">Filter by Socket</button>
                    </form>

                    <!-- Select per Tipo RAM -->
                    <form action="processors" method="get" class="filter-item">
                        <label for="ramType">RAM Type:</label>
                        <select id="ramType" name="ramType">
                            <option value="">All</option>
                            <% List<String> ramTypes = (List<String>) request.getAttribute("ramTypes");
                                for (String ramType : ramTypes) { %>
                            <option value="<%= ramType %>"><%= ramType %></option>
                            <% } %>
                        </select>
                        <button type="submit">Filter by RAM Type</button>
                    </form>
                </div>

                <!-- Sezione filtri compositi -->
                <div class="composite-filter-container">
                    <p>Composite Filter</p>
                    <form action="processors" method="get">
                        <!-- Prezzo tra X e Y -->
                        <label for="compositeMinPrice">Min Price:</label>
                        <input type="number" id="compositeMinPrice" name="compositeMinPrice" step="0.01">
                        <label for="compositeMaxPrice">Max Price:</label>
                        <input type="number" id="compositeMaxPrice" name="compositeMaxPrice" step="0.01">

                        <!-- Select per Socket -->
                        <label for="compositeSocket">Socket:</label>
                        <select id="compositeSocket" name="compositeSocket">
                            <option value="">All</option>
                            <% for (String socket : sockets) { %>
                            <option value="<%= socket %>"><%= socket %></option>
                            <% } %>
                        </select>

                        <!-- Select per Tipo RAM -->
                        <label for="compositeRamType">RAM Type:</label>
                        <select id="compositeRamType" name="compositeRamType">
                            <option value="">All</option>
                            <% for (String ramType : ramTypes) { %>
                            <option value="<%= ramType %>"><%= ramType %></option>
                            <% } %>
                        </select>

                        <!-- Rating tra X e Y -->
                        <label for="compositeMinRating">Min Rating:</label>
                        <input type="number" id="compositeMinRating" name="compositeMinRating" step="0.1" min="0" max="5">
                        <label for="compositeMaxRating">Max Rating:</label>
                        <input type="number" id="compositeMaxRating" name="compositeMaxRating" step="0.1" min="0" max="5">

                        <button type="submit">Apply Composite Filter</button>
                    </form>
                </div>

                <!-- Tasto Reset -->
                <form class="filter-reset-button-container" action="processors" method="get">
                    <button type="submit"><i class="fa fa-refresh" aria-hidden="true"></i>Reset</button>
                </form>
            </div>

            <div class="list-container">
                    <%for (Processor processor : processors){%>
                        <div class="component">
                            <img src="<%=processor.getImage_URL()%>" alt="<%=processor.getName()%>">

                            <div class="component-info-container">
                                <%
                                  String componentName = processor.getName();
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
                                    <p class="group-content"><%=processor.getId()%></p>
                                </div>
                                <div class="group-container rating-container">
                                    <p class="group-label">Rating</p>
                                    <p class="group-content"><i class="fa fa-star" aria-hidden="true"></i><%=processor.getRating()%></p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Price</p>
                                    <p class="group-content">€<%=processor.getPrice()%></p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Shop URL</p>
                                    <a href="<%=processor.getShop_URL()%>"><p class="group-content">Link</p></a>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">TDP</p>
                                    <p class="group-content"><%=processor.getTdp()%>W</p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Socket</p>
                                    <p class="group-content"><%=processor.getSocket()%></p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Ram Type</p>
                                    <p class="group-content"><%=processor.getRam_type()%></p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Core Count</p>
                                    <p class="group-content"><%=processor.getCore()%></p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Threads</p>
                                    <p class="group-content"><%=processor.getThread()%></p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Base Clock</p>
                                    <p class="group-content"><%=processor.getClock_base()%>GHz</p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Boost Clock</p>
                                    <p class="group-content"><%=processor.getClock_boost()%>GHz</p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Cache</p>
                                    <p class="group-content"><%=processor.getCache()%>MB</p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Scale</p>
                                    <p class="group-content"><%=processor.getScale()%>nm</p>
                                </div>
                                <div class="group-container">
                                    <p class="group-label">Generation</p>
                                    <p class="group-content"><%=processor.getGeneration()%></p>
                                </div>
                            </div>

                            <form class="component-buttons-container">
                                <button type="submit" formmethod="post" formaction=""><i class="fa fa-pencil" aria-hidden="true"></i>Edit</button>
                                <button type="submit" formmethod="post" formaction=""><i class="fa fa-times-circle-o" aria-hidden="true"></i>Remove</button>
                            </form>
                        </div>
                    <%}%>
                </div>
        <%}%>

        <%@include file="/WEB-INF/results/modules/footer.jsp"%>
    </body>
</html>
