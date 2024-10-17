package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddSsdServlet", value = "/addSsd")
public class AddSsdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            SSDDAO ssddao = new SSDDAO();
            List<String> pcie_gens = ssddao.doRetrieveDistinctPCIeGenerations();
            List<String> capacities = ssddao.doRetrieveDistinctCapacities();
            request.setAttribute("capacities", capacities);
            request.setAttribute("pcie_gens", pcie_gens);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addSsd.jsp");
            rd.forward(request, response);
        } else response.sendRedirect("index.jsp?notLoggedIn=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SSDDAO ssddao = new SSDDAO();
        String name = request.getParameter("name");
        String rating  = request.getParameter("rating");
        String price = request.getParameter("price");
        String shop_URL = request.getParameter("shop_URL");
        String image_URL = request.getParameter("image_URL");
        String tdp = request.getParameter("tdp");
        String pcie_gen = request.getParameter("pcie_gen");
        String capacity = request.getParameter("capacity");
        String speed_read = request.getParameter("speed_read");
        String speed_write = request.getParameter("speed_write");

        if (
                name!=null && !name.isEmpty()
                && rating!=null && !rating.isEmpty()
                && price!=null && !price.isEmpty()
                && shop_URL!=null && !shop_URL.isEmpty()
                && image_URL!=null && !image_URL.isEmpty()
                && tdp!=null && !tdp.isEmpty()
                && pcie_gen!=null && !pcie_gen.isEmpty()
                && capacity!=null && !capacity.isEmpty()
                && speed_read!=null && !speed_read.isEmpty()
                && speed_write!=null && !speed_write.isEmpty()
        ) {
            List<SSD> ssds = ssddao.doRetrieveByName(name);
            if (ssds.isEmpty()) {
                SSD ssd = new SSD();
                ssd.setName(name);
                ssd.setRating(Double.parseDouble(rating));
                ssd.setPrice(Double.parseDouble(price));
                ssd.setShop_URL(shop_URL);
                ssd.setImage_URL(image_URL);
                ssd.setTdp(Integer.parseInt(tdp));
                ssd.setPcie_gen(pcie_gen);
                ssd.setCapacity(capacity);
                ssd.setSpeed_read(Integer.parseInt(speed_read));
                ssd.setSpeed_write(Integer.parseInt(speed_write));

                int id = ssddao.doSave(ssd);

                GestireSSD gestireSSD = new GestireSSD();
                GestireSSDDAO gestireSSDDAO = new GestireSSDDAO();
                Administrator administrator = (Administrator) session.getAttribute("administrator");
                gestireSSD.setIdSSD(id);
                gestireSSD.setIdAdmin(administrator.getId());
                gestireSSDDAO.doSave(gestireSSD);

                response.sendRedirect("ssds");
            } else {
                List<String> pcie_gens = ssddao.doRetrieveDistinctPCIeGenerations();
                List<String> capacities = ssddao.doRetrieveDistinctCapacities();
                request.setAttribute("capacities", capacities);
                request.setAttribute("pcie_gens", pcie_gens);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addSsd.jsp");
                request.setAttribute("alreadyExists", 1);
                rd.forward(request, response);
            }
        } else {
            List<String> pcie_gens = ssddao.doRetrieveDistinctPCIeGenerations();
            List<String> capacities = ssddao.doRetrieveDistinctCapacities();
            request.setAttribute("capacities", capacities);
            request.setAttribute("pcie_gens", pcie_gens);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addSsd.jsp");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}