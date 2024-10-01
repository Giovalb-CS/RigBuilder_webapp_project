<%@ page import="model.Processor" %>
<%@ page import="java.util.ArrayList" %>
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
    </head>

    <body>
        <%@include file="/WEB-INF/results/modules/isAdminLogged.jsp"%>
        <%@include file="/WEB-INF/results/modules/navbar.jsp"%>

        <%ArrayList<Processor> processors = (ArrayList<Processor>) request.getAttribute("processors");%>

        <form class="add-button-container">
            <button type="submit" formmethod="post" formaction=""><i class="fa fa-plus-square-o" aria-hidden="true"></i>Add <%=fileName.substring(0, fileName.length()-1)%></button>
        </form>

        <%if (processors.isEmpty() || processors == null){%>
            <div class="no-items-container">
                <p>No <%=fileName%> found in the database. Add some.</p>
            </div>
        <%} else {%>
            <div class="filter-container"></div>

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
                            <div class="group-container">
                                <p class="group-label">Rating</p>
                                <p class="group-content"><%=processor.getRating()%></p>
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
