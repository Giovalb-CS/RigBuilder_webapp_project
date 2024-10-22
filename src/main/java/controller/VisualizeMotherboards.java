package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Motherboard;
import model.MotherboardDAO;
import model.Processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisualizeMotherboards", value = "/motherboards")
public class VisualizeMotherboards extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MotherboardDAO motherboardDAO = new MotherboardDAO();

        List<String> sockets = motherboardDAO.doRetrieveDistinctSockets();
        List<String> chipsets = motherboardDAO.doRetrieveDistinctChipsets();
        List<String> ramTypes = motherboardDAO.doRetrieveDistinctRamTypes();
        List<String> formFactors = motherboardDAO.doRetrieveDistinctFormFactors();
        request.setAttribute("sockets", sockets);
        request.setAttribute("chipsets", chipsets);
        request.setAttribute("ramTypes", ramTypes);
        request.setAttribute("formFactors", formFactors);

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String ratingSort = request.getParameter("ratingSort");
        String priceSort = request.getParameter("priceSort");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String socket = request.getParameter("socket");
        String chipset = request.getParameter("chipset");
        String ramType = request.getParameter("ramType");
        String formFactor = request.getParameter("formFactor");

        String compositeMinPrice = request.getParameter("compositeMinPrice");
        String compositeMaxPrice = request.getParameter("compositeMaxPrice");
        String compositeMinRating = request.getParameter("compositeMinRating");
        String compositeMaxRating = request.getParameter("compositeMaxRating");
        String compositeSocket = request.getParameter("compositeSocket");
        String compositeChipset = request.getParameter("compositeChipset");
        String compositeRamType = request.getParameter("compositeRamType");
        String compositeFormFactor = request.getParameter("compositeFormFactor");

        List<Motherboard> motherboards = new ArrayList<>();

        // Logica dei filtri singoli
        if (id != null && !id.isEmpty()) {
            Motherboard motherboard = motherboardDAO.doRetrieveByID(Integer.parseInt(id));
            if ((motherboard != null)) {
                motherboards.add(motherboard);
            }
        } else if (name != null && !name.isEmpty()) {
            motherboards = motherboardDAO.doRetrieveByName(name);
        } else if (ratingSort != null) {
            motherboards = ratingSort.equals("asc")
                    ? motherboardDAO.doRetrieveAllByRatingAsc()
                    : motherboardDAO.doRetrieveAllByRatingDesc();
        } else if (priceSort != null) {
            motherboards = priceSort.equals("asc")
                    ? motherboardDAO.doRetrieveAllByPriceAsc()
                    : motherboardDAO.doRetrieveAllByPriceDesc();
        } else if (minPrice != null && maxPrice != null) {
            motherboards = motherboardDAO.doRetrieveAllByPriceBetween(
                    Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
        } else if (socket != null && !socket.isEmpty()) {
            motherboards = motherboardDAO.doRetrieveBySocket(socket);
        } else if (chipset != null && !chipset.isEmpty()) {
            motherboards = motherboardDAO.doRetrieveByChipset(chipset);
        } else if (ramType != null && !ramType.isEmpty()) {
            motherboards = motherboardDAO.doRetrieveByRAMType(ramType);
        } else if (formFactor != null && !formFactor.isEmpty()) {
            motherboards = motherboardDAO.doRetrieveByFormFactor(formFactor);
        } else {
            motherboards = motherboardDAO.doRetrieveAll();
        }

        if (
                compositeMinPrice != null || compositeMaxPrice != null ||
                (compositeSocket != null && !compositeSocket.isEmpty()) ||
                (compositeChipset != null && !compositeChipset.isEmpty()) ||
                (compositeRamType != null && !compositeRamType.isEmpty()) ||
                (compositeFormFactor != null && !compositeFormFactor.isEmpty()) ||
                compositeMinRating != null || compositeMaxRating != null
        ) {
            // Trasforma solo se il parametro non è vuoto o nullo
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

            motherboards = motherboardDAO.doRetrieveFiltered(
                    minRatingVal,
                    maxRatingVal,
                    minPriceVal,
                    maxPriceVal,
                    compositeSocket,
                    compositeChipset,
                    compositeRamType,
                    compositeFormFactor
            );
        }
        request.setAttribute("motherboards", motherboards);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/motherboards.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            MotherboardDAO motherboardDAO = new MotherboardDAO();
            ArrayList<Motherboard> motherboards = (ArrayList<Motherboard>) motherboardDAO.doRetrieveAll();
            request.setAttribute("motherboards", motherboards);
            List<String> sockets = motherboardDAO.doRetrieveDistinctSockets();
            List<String> chipsets = motherboardDAO.doRetrieveDistinctChipsets();
            List<String> ramTypes = motherboardDAO.doRetrieveDistinctRamTypes();
            List<String> formFactors = motherboardDAO.doRetrieveDistinctFormFactors();
            request.setAttribute("sockets", sockets);
            request.setAttribute("chipsets", chipsets);
            request.setAttribute("ramTypes", ramTypes);
            request.setAttribute("formFactors", formFactors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/motherboards.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}