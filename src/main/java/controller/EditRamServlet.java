package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RAM;
import model.RAMDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditRamServlet", value = "/editRam")
public class EditRamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            RAMDAO ramDAO = new RAMDAO();
            List<String> ramTypes = ramDAO.doRetrieveDistinctRamTypes();
            RAM ram = ramDAO.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("ram", ram);
            request.setAttribute("ramTypes", ramTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editRam.jsp");
            rd.forward(request, response);
        }
        else response.sendRedirect("index.jsp?notLoggedIn=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RAMDAO ramDAO = new RAMDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        RAM ram_before = ramDAO.doRetrieveByID(id);
        RAM ram_after = new RAM();

        String name = request.getParameter("name");
        String rating  = request.getParameter("rating");
        String price = request.getParameter("price");
        String shop_URL = request.getParameter("shop_URL");
        String image_URL = request.getParameter("image_URL");
        String tdp = request.getParameter("tdp");
        String ramType = request.getParameter("ramType");
        String clock = request.getParameter("clock");

        if (
                name!=null && !name.isEmpty()
                && rating!=null && !rating.isEmpty()
                && price!=null && !price.isEmpty()
                && shop_URL!=null && !shop_URL.isEmpty()
                && image_URL!=null && !image_URL.isEmpty()
                && tdp!=null && !tdp.isEmpty()
                && ramType!=null && !ramType.isEmpty()
                && clock!=null && !clock.isEmpty()
        ) {
            ram_after.setId(id);
            ram_after.setName(name);
            ram_after.setRating(Double.parseDouble(rating));
            ram_after.setPrice(Double.parseDouble(price));
            ram_after.setShop_URL(shop_URL);
            ram_after.setImage_URL(image_URL);
            ram_after.setTdp(Integer.parseInt(tdp));
            ram_after.setType(ramType);
            ram_after.setClock(Integer.parseInt(clock));

            ramDAO.doModify(ram_after);

            response.sendRedirect("rams");
        } else {
            List<String> ramTypes = ramDAO.doRetrieveDistinctRamTypes();
            request.setAttribute("ramTypes", ramTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addRam.jsp");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}