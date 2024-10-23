package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Cooler;
import model.CoolerDAO;
import model.Processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisualizeCoolers", value = "/coolers")
public class VisualizeCoolers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CoolerDAO coolerDAO = new CoolerDAO();

        List<String> sockets = coolerDAO.doRetrieveDistinctSockets();
        List<String> radiatorSizes = coolerDAO.doRetrieveDistinctRadiatorSizes();
        request.setAttribute("sockets", sockets);
        request.setAttribute("radiatorSizes", radiatorSizes);

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String ratingSort = request.getParameter("ratingSort");
        String priceSort = request.getParameter("priceSort");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String socket = request.getParameter("socket");
        String radiatorSize = request.getParameter("radiatorSize");
        String maxCoolerHeight = request.getParameter("maxCoolerHeight");

        String compositeMinPrice = request.getParameter("compositeMinPrice");
        String compositeMaxPrice = request.getParameter("compositeMaxPrice");
        String compositeMinRating = request.getParameter("compositeMinRating");
        String compositeMaxRating = request.getParameter("compositeMaxRating");
        String compositeSocket = request.getParameter("compositeSocket");
        String compositeRadiatorSize = request.getParameter("compositeRadiatorSize");
        String compositeMinCoolerHeight = request.getParameter("compositeMinCoolerHeight");
        String compositeMaxCoolerHeight = request.getParameter("compositeMaxCoolerHeight");

        List<Cooler> coolers = new ArrayList<>();

        if (id != null && !id.isEmpty()) {
            Cooler cooler = coolerDAO.doRetrieveByID(Integer.parseInt(id));
            if ((cooler != null)) {
                coolers.add(cooler);
            }
        } else if (name != null && !name.isEmpty()) {
            coolers = coolerDAO.doRetrieveByName(name);
        } else if (ratingSort != null) {
            coolers = ratingSort.equals("asc")
                    ? coolerDAO.doRetrieveAllByRatingAsc()
                    : coolerDAO.doRetrieveAllByRatingDesc();
        } else if (priceSort != null) {
            coolers = priceSort.equals("asc")
                    ? coolerDAO.doRetrieveAllByPriceAsc()
                    : coolerDAO.doRetrieveAllByPriceDesc();
        } else if (minPrice != null && maxPrice != null) {
            coolers = coolerDAO.doRetrieveAllByPriceBetween(
                    Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
        } else if (socket != null && !socket.isEmpty()) {
            coolers = coolerDAO.doRetrieveAllBySocket(socket);
        } else if (radiatorSize != null && !radiatorSize.isEmpty()) {
            coolers = coolerDAO.doRetrieveAllByRadiatorSize(Integer.parseInt(radiatorSize));
        } else if (maxCoolerHeight != null && !maxCoolerHeight.isEmpty()) {
            coolers = coolerDAO.doRetrieveAllByMaxCoolerHeight(Integer.parseInt(maxCoolerHeight));
        } else {
            coolers = coolerDAO.doRetrieveAll();
        }

        if (
                compositeMinPrice != null || compositeMaxPrice != null ||
                        (compositeSocket != null && !compositeSocket.isEmpty()) ||
                        (compositeRadiatorSize != null && !compositeRadiatorSize.isEmpty()) ||
                        (compositeMinCoolerHeight != null && !compositeMinCoolerHeight.isEmpty()) ||
                        (compositeMaxCoolerHeight != null && !compositeMaxCoolerHeight.isEmpty()) ||
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

            Integer radiatorSizeVal = (compositeRadiatorSize != null && !compositeRadiatorSize.isEmpty())
                    ? Integer.parseInt(compositeRadiatorSize)
                    : null;

            Integer minCoolerHeightVal = (compositeMinCoolerHeight != null && !compositeMinCoolerHeight.isEmpty())
                    ? Integer.parseInt(compositeMinCoolerHeight)
                    : null;

            Integer maxCoolerHeightVal = (compositeMaxCoolerHeight != null && !compositeMaxCoolerHeight.isEmpty())
                    ? Integer.parseInt(compositeMaxCoolerHeight)
                    : null;

            coolers = coolerDAO.doRetrieveFiltered(
                    minRatingVal,
                    maxRatingVal,
                    minPriceVal,
                    maxPriceVal,
                    minCoolerHeightVal,
                    maxCoolerHeightVal,
                    radiatorSizeVal,
                    compositeSocket != null && !compositeSocket.isEmpty() ? compositeSocket : null
            );
        }

        request.setAttribute("coolers", coolers);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/coolers.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            CoolerDAO coolerDAO = new CoolerDAO();
            ArrayList<Cooler> coolers = (ArrayList<Cooler>) coolerDAO.doRetrieveAll();
            request.setAttribute("coolers", coolers);
            List<String> sockets = coolerDAO.doRetrieveDistinctSockets();
            List<String> radiatorSizes = coolerDAO.doRetrieveDistinctRadiatorSizes();
            request.setAttribute("sockets", sockets);
            request.setAttribute("radiatorSizes", radiatorSizes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/coolers.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}