package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Motherboard;
import model.MotherboardDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DeleteMotherboardServlet", value = "/removeMotherboard")
public class DeleteMotherboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            String id = request.getParameter("id");
            MotherboardDAO motherboardDAO = new MotherboardDAO();
            if (id != null) {
                Motherboard motherboard = motherboardDAO.doRetrieveByID(Integer.parseInt(id));
                motherboardDAO.doDelete(Integer.parseInt(id));
                ArrayList<Motherboard> motherboards = (ArrayList<Motherboard>) motherboardDAO.doRetrieveAll();
                if (motherboards.contains(motherboard)) {
                    request.setAttribute("deletedSuccessfully", 1);
                } else request.setAttribute("deleteError", 1);
            }
            response.sendRedirect("motherboards");
        }
        else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}