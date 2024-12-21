package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.PSU;
import model.PSUDAO;
import model.Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "API_PSU", value = "/api/psus/*")
public class API_PSU extends HttpServlet {

    private static class FilterParams {
        String name;
        String priceSort;
        String ratingSort;
        Double minPrice;
        Double maxPrice;
        String type;
        String efficiency;
        Integer minWattage;
        Integer maxWattage;
        Integer minLenght;
        Integer maxLenght;
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
                    ", type='" + type + '\'' +
                    ", efficiency='" + efficiency + '\'' +
                    ", minWattage=" + minWattage +
                    ", maxWattage=" + maxWattage +
                    ", minLenght=" + minLenght +
                    ", maxLenght=" + maxLenght +
                    ", minRating=" + minRating +
                    ", maxRating=" + maxRating +
                    '}';
        }
    }

    private final PSUDAO psuDAO = new PSUDAO();

    public static class DataWrapper {
        List<PSU> psus;
        List<String> types;
        List<String> efficiencies;

        public List<PSU> getPsus() {
            return psus;
        }

        public void setPsus(List<PSU> psus) {
            this.psus = psus;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public List<String> getEfficiencies() {
            return efficiencies;
        }

        public void setEfficiencies(List<String> efficiencies) {
            this.efficiencies = efficiencies;
        }

        public DataWrapper(List<PSU> psus, List<String> types, List<String> efficiencies) {
            this.psus = psus;
            this.types = types;
            this.efficiencies = efficiencies;
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

        List<PSU> psus = psuDAO.doRetrieveAll();
        List<String> types = psuDAO.doRetrieveDistinctTypes();
        List<String> efficiencies = psuDAO.doRetrieveDistinctEfficiencyTypes();

        // Converti in JSON e invia la risposta
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DataWrapper dataWrapper = new DataWrapper(psus, types, efficiencies);
        String jsonResponse = gson.toJson(dataWrapper);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        System.out.println("GET: /psus");
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

            List<PSU> psus = new ArrayList<>();

            // Logica per i filtri e ordinamenti singoli
            if (filterParams.name != null && !filterParams.name.isEmpty()) {
                // Ricerca per nome
                psus = psuDAO.doRetrieveByName(filterParams.name);
            } else if (filterParams.ratingSort != null && !filterParams.ratingSort.isEmpty()) {
                // Ordinamento per rating
                psus = filterParams.ratingSort.equals("asc")
                        ? psuDAO.doRetrieveAllByRatingAsc()
                        : psuDAO.doRetrieveAllByRatingDesc();
            } else if (filterParams.priceSort != null && !filterParams.priceSort.isEmpty()) {
                // Ordinamento per prezzo
                psus = filterParams.priceSort.equals("asc")
                        ? psuDAO.doRetrieveAllByPriceAsc()
                        : psuDAO.doRetrieveAllByPriceDesc();
            } else {
                // Filtro composito
                psus = psuDAO.doRetrieveFiltered(
                        filterParams.minPrice,
                        filterParams.maxPrice,
                        filterParams.minRating,
                        filterParams.maxRating,
                        filterParams.type,
                        filterParams.efficiency,
                        filterParams.minWattage,
                        filterParams.maxWattage,
                        filterParams.minLenght,
                        filterParams.maxLenght
                );
            }

            List<String> types = psuDAO.doRetrieveDistinctTypes();
            List<String> efficiencies = psuDAO.doRetrieveDistinctEfficiencyTypes();

            DataWrapper dataWrapper = new DataWrapper(psus, types, efficiencies);
            String jsonResponse = gson.toJson(dataWrapper);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
            System.out.println("POST: /psus/filters\n" + filterParams.toString());
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint.");
        }
    }
}