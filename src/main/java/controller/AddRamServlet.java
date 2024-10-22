package controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddRamServlet", value = "/addRam")
public class AddRamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            RAMDAO ramDAO = new RAMDAO();
            List<String> ramTypes = ramDAO.doRetrieveDistinctRamTypes();
            request.setAttribute("ramTypes", ramTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addRam.jsp");
            rd.forward(request, response);
        }
        else response.sendRedirect("index.jsp?notLoggedIn=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RAMDAO ramDAO = new RAMDAO();
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
           List<RAM> rams = ramDAO.doRetrieveByName(name);
           if (rams.isEmpty()) {
               RAM ram = new RAM();
               ram.setName(name);
               ram.setRating(Double.parseDouble(rating));
               ram.setPrice(Double.parseDouble(price));
               ram.setShop_URL(shop_URL);
               ram.setImage_URL(image_URL);
               ram.setTdp(Integer.parseInt(tdp));
               ram.setType(ramType);
               ram.setClock(Integer.parseInt(clock));

               int id = ramDAO.doSave(ram);

               GestireRAM gestireRAM = new GestireRAM();
               GestireRAMDAO gestireRAMDAO = new GestireRAMDAO();
               Administrator administrator = (Administrator) session.getAttribute("administrator");
               gestireRAM.setIdRAM(id);
               gestireRAM.setIdAdmin(administrator.getId());
               gestireRAMDAO.doSave(gestireRAM);

               response.sendRedirect("rams");
           } else {
               List<String> ramTypes = ramDAO.doRetrieveDistinctRamTypes();
               request.setAttribute("ramTypes", ramTypes);
               RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addRam.jsp?alreadyExists=1");
               request.setAttribute("alreadyExists", 1);
               rd.forward(request, response);
           }
        } else {
            List<String> ramTypes = ramDAO.doRetrieveDistinctRamTypes();
            request.setAttribute("ramTypes", ramTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addRam.jsp?formError=1");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}