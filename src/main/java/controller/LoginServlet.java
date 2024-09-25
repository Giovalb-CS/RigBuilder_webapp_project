package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Administrator;
import model.AdministratorDAO;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        String address = "";

        AdministratorDAO administratorDAO = new AdministratorDAO();
        Administrator administrator = administratorDAO.doRetrieveByEmailPassword(email, password);

        if (administrator != null) {
            HttpSession session = req.getSession();
            session.setAttribute("administrator", administrator);
            address = "dashboard";
        }
        else {
            address = "index.jsp?loginFailed=1";
        }

        resp.sendRedirect(address);
    }
}
