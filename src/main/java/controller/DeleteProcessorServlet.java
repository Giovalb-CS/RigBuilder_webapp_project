package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Processor;
import model.ProcessorDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeleteProcessorServlet", value = "/removeProcessor")
public class DeleteProcessorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            String id = request.getParameter("id");
            ProcessorDAO processorDao = new ProcessorDAO();
            if (id != null) {
                Processor processor = processorDao.doRetrieveByID(Integer.parseInt(id));
                processorDao.doDelete(Integer.parseInt(id));
                ArrayList<Processor> processors = (ArrayList<Processor>) (new ProcessorDAO()).doRetrieveAll();
                if (processors.contains(processor)) {
                    request.setAttribute("deletedSuccessfully", 1);
                } else request.setAttribute("deleteError", 1);
            }
            response.sendRedirect("processors");
        }
        else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}