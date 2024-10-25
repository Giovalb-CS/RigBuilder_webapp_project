package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddPSUServlet", value = "/addPsu")
public class AddPSUServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            PSUDAO psuDAO = new PSUDAO();
            List<String> types = psuDAO.doRetrieveDistinctTypes();
            List<String> efficiencies = psuDAO.doRetrieveDistinctEfficiencyTypes();
            request.setAttribute("types", types);
            request.setAttribute("efficiencies", efficiencies);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addPsu.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PSUDAO psuDAO = new PSUDAO();
        String name = request.getParameter("name");
        String rating  = request.getParameter("rating");
        String price = request.getParameter("price");
        String shop_URL = request.getParameter("shop_URL");
        String image_URL = request.getParameter("image_URL");
        String type = request.getParameter("type");
        String efficiency = request.getParameter("efficiency");
        String wattage = request.getParameter("wattage");
        String lenght = request.getParameter("lenght");

        if (
                name!=null && !name.isEmpty()
                && rating!=null && !rating.isEmpty()
                && price!=null && !price.isEmpty()
                && shop_URL!=null && !shop_URL.isEmpty()
                && image_URL!=null && !image_URL.isEmpty()
                && type!=null && !type.isEmpty()
                && efficiency!=null && !efficiency.isEmpty()
                && wattage!=null && !wattage.isEmpty()
                && lenght!=null && !lenght.isEmpty()
        ) {
            List<PSU> psus = psuDAO.doRetrieveByName(name);
            if (psus.isEmpty()) {
                PSU psu = new PSU();
                psu.setName(name);
                psu.setRating(Double.parseDouble(rating));
                psu.setPrice(Double.parseDouble(price));
                psu.setShop_URL(shop_URL);
                psu.setImage_URL(image_URL);
                psu.setType(type);
                psu.setEfficiency(efficiency);
                psu.setWattage(Integer.parseInt(wattage));
                psu.setLenght(Integer.parseInt(lenght));

                int id = psuDAO.doSave(psu);

                GestirePSU gestirePSU = new GestirePSU();
                GestirePSUDAO gestirePSUDAO = new GestirePSUDAO();
                Administrator administrator = (Administrator) session.getAttribute("administrator");
                gestirePSU.setIdPSU(id);
                gestirePSU.setIdAdmin(administrator.getId());
                gestirePSUDAO.doSave(gestirePSU);

                response.sendRedirect("psus");
            } else {
                List<String> types = psuDAO.doRetrieveDistinctTypes();
                List<String> efficiencies = psuDAO.doRetrieveDistinctEfficiencyTypes();
                request.setAttribute("types", types);
                request.setAttribute("efficiencies", efficiencies);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addPsu.jsp?alreadyExists=1");
                request.setAttribute("alreadyExists", 1);
                rd.forward(request, response);
            }
        } else {
            List<String> types = psuDAO.doRetrieveDistinctTypes();
            List<String> efficiencies = psuDAO.doRetrieveDistinctEfficiencyTypes();
            request.setAttribute("types", types);
            request.setAttribute("efficiencies", efficiencies);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addPsu.jsp?formError=1");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}