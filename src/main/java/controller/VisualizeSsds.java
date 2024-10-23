package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.SSD;
import model.SSDDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisualizeSsds", value = "/ssds")
public class VisualizeSsds extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SSDDAO ssddao = new SSDDAO();
        List<String> pcie_gens = ssddao.doRetrieveDistinctPCIeGenerations();
        request.setAttribute("pcie_gens", pcie_gens);
        List<String> capacities = ssddao.doRetrieveDistinctCapacities();
        request.setAttribute("capacities", capacities);

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String ratingSort = request.getParameter("ratingSort");
        String priceSort = request.getParameter("priceSort");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String pcie_gen = request.getParameter("pcie_gen");
        String capacity = request.getParameter("capacity");

        String compositeMinPrice = request.getParameter("compositeMinPrice");
        String compositeMaxPrice = request.getParameter("compositeMaxPrice");
        String compositeMinRating = request.getParameter("compositeMinRating");
        String compositeMaxRating = request.getParameter("compositeMaxRating");
        String compositePCIeGen = request.getParameter("compositePCIeGen");
        String compositeCapacity = request.getParameter("compositeCapacity");

        List<SSD> ssds = new ArrayList<>();

        if (id != null && !id.isEmpty()) {
            SSD ssd = ssddao.doRetrieveByID(Integer.parseInt(id));
            if ((ssd != null)) {
                ssds.add(ssd);
            }
        } else if (name != null && !name.isEmpty()) {
            ssds = ssddao.doRetrieveByName(name);
        } else if (ratingSort != null) {
            ssds = ratingSort.equals("asc")
                    ? ssddao.doRetrieveAllByRatingAsc()
                    : ssddao.doRetrieveAllByRatingDesc();
        } else if (priceSort != null) {
            ssds = priceSort.equals("asc")
                    ? ssddao.doRetrieveAllByPriceAsc()
                    : ssddao.doRetrieveAllByPriceDesc();
        } else if (minPrice != null && maxPrice != null) {
            ssds = ssddao.doRetrieveAllByPriceBetween(
                    Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
        } else if (pcie_gen != null && !pcie_gen.isEmpty()) {
            ssds = ssddao.doRetrieveAllByPCIEGen(pcie_gen);
        } else if (capacity != null && !capacity.isEmpty()) {
            ssds = ssddao.doRetrieveAllByCapacity(capacity);
        } else {
            ssds = ssddao.doRetrieveAll();
        }

        if (
                compositeMinPrice != null || compositeMaxPrice != null ||
                (compositePCIeGen != null && !compositePCIeGen.isEmpty()) ||
                (compositeCapacity != null && !compositeCapacity.isEmpty()) ||
                compositeMinRating != null || compositeMaxRating != null
        ) {
            Double minPriceVal = (compositeMinPrice != null && !compositeMinPrice.isEmpty())
                    ? Double.parseDouble(compositeMinPrice)
                    : null;

            Double maxPriceVal = (compositeMaxPrice != null && !compositeMaxPrice.isEmpty())
                    ? Double.parseDouble(compositeMaxPrice)
                    : null;

            Double minRatingVal = (compositeMinRating != null && !compositeMinRating.isEmpty())
                    ? Double.parseDouble(compositeMinRating)
                    : null;

            Double maxRatingVal = (compositeMaxRating != null && !compositeMaxRating.isEmpty())
                    ? Double.parseDouble(compositeMaxRating)
                    : null;

            ssds = ssddao.doRetrieveFiltered(
                    minPriceVal,
                    maxPriceVal,
                    minRatingVal,
                    maxRatingVal,
                    compositeCapacity != null && !compositeCapacity.isEmpty() ? compositeCapacity : null,
                    compositePCIeGen != null && !compositePCIeGen.isEmpty() ? compositePCIeGen : null
            );
        }

        request.setAttribute("ssds", ssds);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/ssds.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            SSDDAO ssddao = new SSDDAO();
            ArrayList<SSD> ssds = (ArrayList<SSD>) ssddao.doRetrieveAll();
            List<String> pcie_gens = ssddao.doRetrieveDistinctPCIeGenerations();
            List<String> capacities = ssddao.doRetrieveDistinctCapacities();
            request.setAttribute("capacities", capacities);
            request.setAttribute("pcie_gens", pcie_gens);
            request.setAttribute("ssds", ssds);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/ssds.jsp");
            rd.forward(request, response);
        } else response.sendRedirect("index.jsp?notLoggedIn=1");
    }
}