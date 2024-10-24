package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Cooler;
import model.CoolerDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DeleteCoolerServlet", value = "/removeCooler")
public class DeleteCoolerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            String id = request.getParameter("id");
            CoolerDAO coolerDAO = new CoolerDAO();
            if (id != null) {
                Cooler cooler = coolerDAO.doRetrieveByID(Integer.parseInt(id));
                coolerDAO.doDelete(Integer.parseInt(id));
                ArrayList<Cooler> coolers = (ArrayList<Cooler>) coolerDAO.doRetrieveAll();
                if (coolers.contains(cooler)) {
                    request.setAttribute("deletedSuccessfully", 1);
                } else request.setAttribute("deleteError", 1);
            }
            response.sendRedirect("coolers");
        }
        else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}