<%@ page import="model.Administrator" %>
<div class="nav_menu">
  <div class="logo-container">
    <div class="extended">
      <img src="${pageContext.request.contextPath}/images/RigBuilder_Logo_horizontal.png" alt="logo">
    </div>
    <div class="short">
      <img src="${pageContext.request.contextPath}/images/RigBuilder_Logo_fan.png" alt="logo">
    </div>
  </div>

  <a href="${pageContext.request.contextPath}/dashboard"><p><i class="fa fa-home" aria-hidden="true"></i>Home</p></a>

  <%
    Administrator administrator = (Administrator) session.getAttribute("administrator");
  %>
  <p id="welcome-p">Welcome, <%=administrator.getEmail().split("@")[0]%></p>

  <a href="${pageContext.request.contextPath}/logout"><p><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</p></a>
</div>