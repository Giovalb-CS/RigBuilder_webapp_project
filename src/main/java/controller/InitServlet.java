package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "InitServlet", value = "/init", loadOnStartup = 0)
public class InitServlet extends HttpServlet {
    public void init() throws ServletException {
        super.init();
    }
}