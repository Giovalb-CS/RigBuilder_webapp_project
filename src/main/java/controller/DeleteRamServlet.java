package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RAM;
import model.RAMDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DeleteRamServlet", value = "/removeRam")
public class DeleteRamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            String id = request.getParameter("id");
            RAMDAO ramDAO = new RAMDAO();
            if (id != null) {
                RAM ram = ramDAO.doRetrieveByID(Integer.parseInt(id));
                ramDAO.doDelete(Integer.parseInt(id));
                ArrayList<RAM> rams = (ArrayList<RAM>) ramDAO.doRetrieveAll();
                if (rams.contains(ram)) {
                    request.setAttribute("deletedSuccessfully", 1);
                } else request.setAttribute("deleteError", 1);
            }
            response.sendRedirect("rams");
        }
        else response.sendRedirect("index.jsp?notLoggedIn=1");
    }
}