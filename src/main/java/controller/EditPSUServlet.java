package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.PSU;
import model.PSUDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditPSUServlet", value = "/editPsu")
public class EditPSUServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            PSUDAO psuDAO = new PSUDAO();
            List<String> types = psuDAO.doRetrieveDistinctTypes();
            List<String> efficiencies = psuDAO.doRetrieveDistinctEfficiencyTypes();
            PSU psu = psuDAO.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("psu", psu);
            request.setAttribute("types", types);
            request.setAttribute("efficiencies", efficiencies);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editPsu.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PSUDAO psuDAO = new PSUDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        PSU psu_after = new PSU();

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
            psu_after.setId(id);
            psu_after.setName(name);
            psu_after.setRating(Double.parseDouble(rating));
            psu_after.setPrice(Double.parseDouble(price));
            psu_after.setShop_URL(shop_URL);
            psu_after.setImage_URL(image_URL);
            psu_after.setType(type);
            psu_after.setEfficiency(efficiency);
            psu_after.setWattage(Integer.parseInt(wattage));
            psu_after.setLenght(Integer.parseInt(lenght));

            psuDAO.doModify(psu_after);

            response.sendRedirect("psus");
        } else {
            List<String> types = psuDAO.doRetrieveDistinctTypes();
            List<String> efficiencies = psuDAO.doRetrieveDistinctEfficiencyTypes();
            PSU psu = psuDAO.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("psu", psu);
            request.setAttribute("types", types);
            request.setAttribute("efficiencies", efficiencies);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editPsu.jsp?formError=1");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}