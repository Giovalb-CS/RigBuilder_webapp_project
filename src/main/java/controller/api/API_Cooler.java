package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Cooler;
import model.CoolerDAO;
import model.Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "API_Cooler", value = "/api/coolers/*")
public class API_Cooler extends HttpServlet {

    private static class FilterParams {
        String name;
        String priceSort;
        String ratingSort;
        Double minPrice;
        Double maxPrice;
        String socket;
        Integer radiatorSize;
        Integer minCoolerHeight;
        Integer maxCoolerHeight;
        Double minRating;
        Double maxRating;

        @Override
        public String toString() {
            return "FilterParams{" +
                    "name='" + name + '\'' +
                    ", priceSort='" + priceSort + '\'' +
                    ", ratingSort='" + ratingSort + '\'' +
                    ", minPrice=" + minPrice +
                    ", maxPrice=" + maxPrice +
                    ", socket='" + socket + '\'' +
                    ", radiatorSize=" + radiatorSize +
                    ", minCoolerHeight=" + minCoolerHeight +
                    ", maxCoolerHeight=" + maxCoolerHeight +
                    ", minRating=" + minRating +
                    ", maxRating=" + maxRating +
                    '}';
        }
    }

    private final CoolerDAO coolerDAO = new CoolerDAO();

    public static class DataWrapper {
        List<Cooler> coolers;
        List<String> sockets;
        List<String> radiatorSizes;

        public List<Cooler> getCoolers() {
            return coolers;
        }

        public void setCoolers(List<Cooler> coolers) {
            this.coolers = coolers;
        }

        public List<String> getSockets() {
            return sockets;
        }

        public void setSockets(List<String> sockets) {
            this.sockets = sockets;
        }

        public List<String> getRadiatorSizes() {
            return radiatorSizes;
        }

        public void setRadiatorSizes(List<String> radiatorSizes) {
            this.radiatorSizes = radiatorSizes;
        }

        public DataWrapper(List<Cooler> coolers, List<String> sockets, List<String> radiatorSizes) {
            this.coolers = coolers;
            this.sockets = sockets;
            this.radiatorSizes = radiatorSizes;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientApiKey = request.getHeader("X-API-KEY");
        if (clientApiKey == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing API Key");
            return;
        }
        if (!ApiKeyValidator.isApiKeyValid(clientApiKey)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "API Key invalid.");
            return;
        }

        List<Cooler> coolers = coolerDAO.doRetrieveAll();
        List<String> sockets = coolerDAO.doRetrieveDistinctSockets();
        List<String> radiatorSizes = coolerDAO.doRetrieveDistinctRadiatorSizes();

        // Converti in JSON e invia la risposta
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DataWrapper dataWrapper = new DataWrapper(coolers, sockets, radiatorSizes);
        String jsonResponse = gson.toJson(dataWrapper);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        System.out.println("GET: /coolers");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientApiKey = request.getHeader("X-API-KEY");
        if (clientApiKey == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing API Key");
            return;
        }
        if (!ApiKeyValidator.isApiKeyValid(clientApiKey)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "API Key invalid.");
            return;
        }

        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.equals("/filters")) {
            // Riceve e gestisce i filtri
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }

            // Deserializza i parametri di filtro dal JSON
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            FilterParams filterParams = gson.fromJson(jsonBody.toString(), FilterParams.class);

            List<Cooler> coolers = new ArrayList<>();

            // Logica per i filtri e ordinamenti singoli
            if (filterParams.name != null && !filterParams.name.isEmpty()) {
                // Ricerca per nome
                coolers = coolerDAO.doRetrieveByName(filterParams.name);
            } else if (filterParams.ratingSort != null && !filterParams.ratingSort.isEmpty()) {
                // Ordinamento per rating
                coolers = filterParams.ratingSort.equals("asc")
                        ? coolerDAO.doRetrieveAllByRatingAsc()
                        : coolerDAO.doRetrieveAllByRatingDesc();
            } else if (filterParams.priceSort != null && !filterParams.priceSort.isEmpty()) {
                // Ordinamento per prezzo
                coolers = filterParams.priceSort.equals("asc")
                        ? coolerDAO.doRetrieveAllByPriceAsc()
                        : coolerDAO.doRetrieveAllByPriceDesc();
            } else {
                // Filtro composito
                coolers = coolerDAO.doRetrieveFiltered(
                        filterParams.minRating,
                        filterParams.maxRating,
                        filterParams.minPrice,
                        filterParams.maxPrice,
                        filterParams.minCoolerHeight,
                        filterParams.maxCoolerHeight,
                        filterParams.radiatorSize,
                        filterParams.socket
                );
            }

            List<String> sockets = coolerDAO.doRetrieveDistinctSockets();
            List<String> radiatorSizes = coolerDAO.doRetrieveDistinctRadiatorSizes();

            DataWrapper dataWrapper = new DataWrapper(coolers, sockets, radiatorSizes);
            String jsonResponse = gson.toJson(dataWrapper);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
            System.out.println("POST: /coolers/filters\n" + filterParams.toString());
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint.");
        }
    }
}