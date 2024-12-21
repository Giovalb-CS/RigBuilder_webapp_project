package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.PSU;
import model.PSUDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DeletePSUServlet", value = "/removePsu")
public class DeletePSUServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            String id = request.getParameter("id");
            PSUDAO psuDAO = new PSUDAO();
            if (id != null) {
                PSU psu = psuDAO.doRetrieveByID(Integer.parseInt(id));
                psuDAO.doDelete(Integer.parseInt(id));
                ArrayList<PSU> psus = (ArrayList<PSU>) psuDAO.doRetrieveAll();
                if (psus.contains(psu)) {
                    request.setAttribute("deletedSuccessfully", 1);
                } else request.setAttribute("deleteError", 1);
            }
            response.sendRedirect("psus");
        }
        else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}