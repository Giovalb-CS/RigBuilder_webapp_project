package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.GPU;
import model.GPUDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DeleteGPUServlet", value = "/removeGpu")
public class DeleteGPUServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            String id = request.getParameter("id");
            GPUDAO gpuDAO = new GPUDAO();
            if (id != null) {
                GPU gpu = gpuDAO.doRetrieveByID(Integer.parseInt(id));
                gpuDAO.doDelete(Integer.parseInt(id));
                ArrayList<GPU> gpus = (ArrayList<GPU>) gpuDAO.doRetrieveAll();
                if (gpus.contains(gpu)) {
                    request.setAttribute("deletedSuccessfully", 1);
                } else request.setAttribute("deleteError", 1);
            }
            response.sendRedirect("gpus");
        }
        else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}