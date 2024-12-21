<%
  if (session.getAttribute("administrator") == null){
    response.sendRedirect("index.jsp?notLoggedIn=1");
  }
%>
