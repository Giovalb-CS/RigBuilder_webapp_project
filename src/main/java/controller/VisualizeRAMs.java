package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RAM;
import model.RAMDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisualizeRAMs", value = "/rams")
public class VisualizeRAMs extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            RAMDAO ramDAO = new RAMDAO();

            List<String> ramTypes = ramDAO.doRetrieveDistinctRamTypes();
            request.setAttribute("ramTypes", ramTypes);

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String ratingSort = request.getParameter("ratingSort");
            String priceSort = request.getParameter("priceSort");
            String minPrice = request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice");
            String ramType = request.getParameter("ramType");
            String clock = request.getParameter("clock");

            String compositeMinPrice = request.getParameter("compositeMinPrice");
            String compositeMaxPrice = request.getParameter("compositeMaxPrice");
            String compositeRamType = request.getParameter("compositeRamType");
            String compositeMinClock = request.getParameter("compositeMinClock");
            String compositeMaxClock = request.getParameter("compositeMaxClock");
            String compositeMinRating = request.getParameter("compositeMinRating");
            String compositeMaxRating = request.getParameter("compositeMaxRating");

            List<RAM> rams = new ArrayList<>();

            if (id != null && !id.isEmpty()) {
                RAM ram = ramDAO.doRetrieveByID(Integer.parseInt(id));
                if (ram != null) {
                    rams.add(ram);
                }
            } else if (name != null && !name.isEmpty()) {
                rams = ramDAO.doRetrieveByName(name);
            } else if (ratingSort != null) {
                rams = ratingSort.equals("asc")
                        ? ramDAO.doRetrieveAllByRatingAsc()
                        : ramDAO.doRetrieveAllByRatingDesc();
            } else if (priceSort != null) {
                rams = priceSort.equals("asc")
                        ? ramDAO.doRetrieveAllByPriceAsc()
                        : ramDAO.doRetrieveAllByPriceDesc();
            } else if (minPrice != null && maxPrice != null) {
                rams = ramDAO.doRetrieveAllByPriceBetween(Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
            } else if (ramType != null && !ramType.isEmpty()) {
                rams = ramDAO.doRetrieveAllByType(ramType);
            } else if (clock != null && !clock.isEmpty()) {
                rams = ramDAO.doRetrieveAllByMinClock(Integer.parseInt(clock));
            } else {
                rams = ramDAO.doRetrieveAll();
            }

            if (
                    compositeMinPrice != null || compositeMaxPrice != null ||
                    (compositeRamType != null && !compositeRamType.isEmpty()) ||
                    (compositeMinClock != null && !compositeMinClock.isEmpty()) ||
                    (compositeMaxClock != null && !compositeMaxClock.isEmpty()) ||
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

                Integer minClockVal = (compositeMinClock != null && !compositeMinClock.isEmpty())
                        ? Integer.parseInt(compositeMinClock)
                        : null;

                Integer maxClockVal = (compositeMaxClock != null && !compositeMaxClock.isEmpty())
                        ? Integer.parseInt(compositeMaxClock)
                        : null;

                rams = ramDAO.doRetrieveFiltered(
                        minRatingVal,
                        maxRatingVal,
                        minPriceVal,
                        maxPriceVal,
                        compositeRamType != null && !compositeRamType.isEmpty() ? compositeRamType : null,
                        minClockVal,
                        maxClockVal
                );
            }
            request.setAttribute("rams", rams);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/rams.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            RAMDAO ramDAO = new RAMDAO();
            ArrayList<RAM> rams = (ArrayList<RAM>) ramDAO.doRetrieveAll();
            request.setAttribute("rams", rams);
            List<String> ramTypes = ramDAO.doRetrieveDistinctRamTypes();
            request.setAttribute("ramTypes", ramTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/rams.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}