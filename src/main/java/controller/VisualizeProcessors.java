package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Processor;
import model.ProcessorDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "VisualizeProcessors", value = "/processors")
public class VisualizeProcessors extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            ArrayList<Processor> processors = (ArrayList<Processor>) (new ProcessorDAO()).doRetrieveAll();
            request.setAttribute("processors", processors);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/processors.jsp");
            rd.forward(request, response);
        }
        else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}