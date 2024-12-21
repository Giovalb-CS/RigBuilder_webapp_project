package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.SSD;
import model.SSDDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditSsdServlet", value = "/editSsd")
public class EditSsdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            SSDDAO ssddao = new SSDDAO();
            List<String> pcie_gens = ssddao.doRetrieveDistinctPCIeGenerations();
            List<String> capacities = ssddao.doRetrieveDistinctCapacities();
            SSD ssd = ssddao.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("ssd", ssd);
            request.setAttribute("capacities", capacities);
            request.setAttribute("pcie_gens", pcie_gens);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editSsd.jsp");
            rd.forward(request, response);
        } else response.sendRedirect("index.jsp?notLoggedIn=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SSDDAO ssddao = new SSDDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        SSD ssd_before = ssddao.doRetrieveByID(id);
        SSD ssd_after = new SSD();

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
            ssd_after.setId(id);
            ssd_after.setName(name);
            ssd_after.setRating(Double.parseDouble(rating));
            ssd_after.setPrice(Double.parseDouble(price));
            ssd_after.setShop_URL(shop_URL);
            ssd_after.setImage_URL(image_URL);
            ssd_after.setTdp(Integer.parseInt(tdp));
            ssd_after.setPcie_gen(pcie_gen);
            ssd_after.setCapacity(capacity);
            ssd_after.setSpeed_read(Integer.parseInt(speed_read));
            ssd_after.setSpeed_write(Integer.parseInt(speed_write));

            ssddao.doModify(ssd_after);

            response.sendRedirect("ssds");
        }
        else {
            List<String> pcie_gens = ssddao.doRetrieveDistinctPCIeGenerations();
            List<String> capacities = ssddao.doRetrieveDistinctCapacities();
            SSD ssd = ssddao.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("ssd", ssd);
            request.setAttribute("capacities", capacities);
            request.setAttribute("pcie_gens", pcie_gens);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editSsd.jsp");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}