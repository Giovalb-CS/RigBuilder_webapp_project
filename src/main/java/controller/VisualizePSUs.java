package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.PSU;
import model.PSUDAO;
import model.Processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisualizePSUs", value = "/psus")
public class VisualizePSUs extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            PSUDAO psuDAO = new PSUDAO();

            List<String> types = psuDAO.doRetrieveDistinctTypes();
            List<String> efficiencies = psuDAO.doRetrieveDistinctEfficiencyTypes();
            request.setAttribute("types", types);
            request.setAttribute("efficiencies", efficiencies);

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String ratingSort = request.getParameter("ratingSort");
            String priceSort = request.getParameter("priceSort");
            String minPrice = request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice");
            String type = request.getParameter("type");
            String efficiency = request.getParameter("efficiency");
            String minWattage = request.getParameter("minWattage");
            String maxLenght = request.getParameter("maxLenght");

            String compositeMinPrice = request.getParameter("compositeMinPrice");
            String compositeMaxPrice = request.getParameter("compositeMaxPrice");
            String compositeMinRating = request.getParameter("compositeMinRating");
            String compositeMaxRating = request.getParameter("compositeMaxRating");
            String compositeType = request.getParameter("compositeType");
            String compositeEfficiency = request.getParameter("compositeEfficiency");
            String compositeMinWattage = request.getParameter("compositeMinWattage");
            String compositeMaxWattage = request.getParameter("compositeMaxWattage");
            String compositeMinLenght = request.getParameter("compositeMinLenght");
            String compositeMaxLenght = request.getParameter("compositeMaxLenght");

            List<PSU> psus = new ArrayList<>();

            // Logica dei filtri singoli
            if (id != null && !id.isEmpty()) {
                PSU psu = psuDAO.doRetrieveByID(Integer.parseInt(id));
                if ((psu != null)) {
                    psus.add(psu);
                }
            } else if (name != null && !name.isEmpty()) {
                psus = psuDAO.doRetrieveByName(name);
            } else if (ratingSort != null) {
                psus = ratingSort.equals("asc")
                        ? psuDAO.doRetrieveAllByRatingAsc()
                        : psuDAO.doRetrieveAllByRatingDesc();
            } else if (priceSort != null) {
                psus = priceSort.equals("asc")
                        ? psuDAO.doRetrieveAllByPriceAsc()
                        : psuDAO.doRetrieveAllByPriceDesc();
            } else if (minPrice != null && maxPrice != null) {
                psus = psuDAO.doRetrieveAllByPriceBetween(
                        Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
            } else if (type != null && !type.isEmpty()) {
                psus = psuDAO.doRetrieveAllByType(type);
            } else if (efficiency != null && !efficiency.isEmpty()) {
                psus = psuDAO.doRetrieveAllByEfficiency(efficiency);
            } else if (minWattage != null && !minWattage.isEmpty()) {
                psus = psuDAO.doRetrieveAllByMinWattage(Integer.valueOf(minWattage));
            } else if (maxLenght != null && !maxLenght.isEmpty()) {
                psus = psuDAO.doRetrieveAllByMaxLenght(Integer.valueOf(maxLenght));
            } else {
                psus = psuDAO.doRetrieveAll();
            }

            if (
                    compositeMinPrice != null || compositeMaxPrice != null ||
                            (compositeType != null && !compositeType.isEmpty()) ||
                            (compositeEfficiency != null && !compositeEfficiency.isEmpty()) ||
                            (compositeMinWattage != null && !compositeMinWattage.isEmpty()) ||
                            (compositeMaxWattage != null && !compositeMaxWattage.isEmpty()) ||
                            (compositeMinLenght != null && !compositeMinLenght.isEmpty()) ||
                            (compositeMaxLenght != null && !compositeMaxLenght.isEmpty()) ||
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

                Integer minWattageVal = (compositeMinWattage != null && !compositeMinWattage.isEmpty())
                        ? Integer.parseInt(compositeMinWattage)
                        : null;

                Integer maxWattageVal = (compositeMaxWattage != null && !compositeMaxWattage.isEmpty())
                        ? Integer.parseInt(compositeMaxWattage)
                        : null;

                Integer minLenghtVal = (compositeMinLenght != null && !compositeMinLenght.isEmpty())
                        ? Integer.parseInt(compositeMinLenght)
                        : null;

                Integer maxLenghtVal = (compositeMaxLenght != null && !compositeMaxLenght.isEmpty())
                        ? Integer.parseInt(compositeMaxLenght)
                        : null;

                psus = psuDAO.doRetrieveFiltered(
                        minPriceVal,
                        maxPriceVal,
                        minRatingVal,
                        maxRatingVal,
                        compositeType,
                        compositeEfficiency,
                        minWattageVal,
                        maxWattageVal,
                        minLenghtVal,
                        maxLenghtVal
                );
            }

            request.setAttribute("psus", psus);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/psus.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            PSUDAO psuDAO = new PSUDAO();
            ArrayList<PSU> psus = (ArrayList<PSU>) psuDAO.doRetrieveAll();
            request.setAttribute("psus", psus);
            List<String> types = psuDAO.doRetrieveDistinctTypes();
            List<String> efficiencies = psuDAO.doRetrieveDistinctEfficiencyTypes();
            request.setAttribute("types", types);
            request.setAttribute("efficiencies", efficiencies);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/psus.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}