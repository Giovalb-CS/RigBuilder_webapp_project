package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.SSD;
import model.SSDDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DeleteSsdServlet", value = "/removeSsd")
public class DeleteSsdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            String id = request.getParameter("id");
            SSDDAO ssddao = new SSDDAO();
            if (id != null) {
                SSD ssd = ssddao.doRetrieveByID(Integer.parseInt(id));
                ssddao.doDelete(Integer.parseInt(id));
                ArrayList<SSD> ssds = (ArrayList<SSD>) ssddao.doRetrieveAll();
                if (ssds.contains(ssd)) {
                    request.setAttribute("deletedSuccessfully", 1);
                } else request.setAttribute("deleteError", 1);
            }
            response.sendRedirect("ssds");
        } else response.sendRedirect("index.jsp?notLoggedIn=1");
    }
}