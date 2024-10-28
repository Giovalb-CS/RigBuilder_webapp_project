package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Administrator;
import model.AdministratorDAO;

import java.io.IOException;

@WebServlet(name = "AddAdminServlet", value = "/addAdmin")
public class AddAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addAdmin.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdministratorDAO administratorDAO = new AdministratorDAO();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmEmail = request.getParameter("confirm-email");
        String confirmPassword = request.getParameter("confirm-password");

        if (
                email != null && !email.isEmpty()
                && password != null && !password.isEmpty()
                && confirmEmail != null && !confirmEmail.isEmpty()
                && confirmPassword != null && !confirmPassword.isEmpty()
        ) {
            Administrator administrator = administratorDAO.doRetrieveByEmail(email);
            if (administrator == null) {
                if (email.equals(confirmEmail)) {
                    if (password.equals(confirmPassword)) {
                        Administrator newAdministrator = new Administrator();
                        newAdministrator.setEmail(email);
                        newAdministrator.setPwd(password);
                        int id = administratorDAO.doSave(newAdministrator);

                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/dashboard.jsp?userCreated=1");
                        request.setAttribute("userCreated", 1);
                        request.setAttribute("newUserID", id);
                        request.setAttribute("newUserEmail", newAdministrator.getEmail());
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addAdmin.jsp?passwordError=1");
                        request.setAttribute("passwordError", 1);
                        rd.forward(request, response);
                    }
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addAdmin.jsp?emailError=1");
                    request.setAttribute("emailError", 1);
                    rd.forward(request, response);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addAdmin.jsp?alreadyExists=1");
                request.setAttribute("alreadyExists", 1);
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addAdmin.jsp?formError=1");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}