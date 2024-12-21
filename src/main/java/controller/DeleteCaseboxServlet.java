package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Casebox;
import model.CaseboxDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DeleteCaseboxServlet", value = "/removeCase")
public class DeleteCaseboxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            String id = request.getParameter("id");
            CaseboxDAO caseboxDAO = new CaseboxDAO();
            if (id != null) {
                Casebox casebox = caseboxDAO.doRetrieveByID(Integer.parseInt(id));
                caseboxDAO.doDelete(Integer.parseInt(id));
                ArrayList<Casebox> caseboxes = (ArrayList<Casebox>) caseboxDAO.doRetrieveAll();
                if (caseboxes.contains(casebox)) {
                    request.setAttribute("deletedSuccessfully", 1);
                } else request.setAttribute("deleteError", 1);
            }
            response.sendRedirect("cases");
        }
        else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}