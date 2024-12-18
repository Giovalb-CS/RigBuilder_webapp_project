package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Motherboard;
import model.MotherboardDAO;
import model.Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "API_Motherboard", value = "/api/motherboards/*")
public class API_Motherboard extends HttpServlet {

    private static class FilterParams {
        String name;
        String priceSort;
        String ratingSort;
        Double minPrice;
        Double maxPrice;
        String socket;
        String chipset;
        String ramType;
        String formFactor;
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
                    ", chipset='" + chipset + '\'' +
                    ", ramType='" + ramType + '\'' +
                    ", formFactor='" + formFactor + '\'' +
                    ", minRating=" + minRating +
                    ", maxRating=" + maxRating +
                    '}';
        }
    }

    private final MotherboardDAO motherboardDAO = new MotherboardDAO();

    public static class DataWrapper {
        ArrayList<Motherboard> motherboards;
        List<String> sockets;
        List<String> chipsets;
        List<String> ramTypes;
        List<String> formFactors;

        public DataWrapper(ArrayList<Motherboard> motherboards, List<String> sockets, List<String> chipsets, List<String> ramTypes, List<String> formFactors) {
            this.motherboards = motherboards;
            this.sockets = sockets;
            this.chipsets = chipsets;
            this.ramTypes = ramTypes;
            this.formFactors = formFactors;
        }

        public ArrayList<Motherboard> getMotherboards() {
            return motherboards;
        }

        public void setMotherboards(ArrayList<Motherboard> motherboards) {
            this.motherboards = motherboards;
        }

        public List<String> getSockets() {
            return sockets;
        }

        public void setSockets(List<String> sockets) {
            this.sockets = sockets;
        }

        public List<String> getChipsets() {
            return chipsets;
        }

        public void setChipsets(List<String> chipsets) {
            this.chipsets = chipsets;
        }

        public List<String> getRamTypes() {
            return ramTypes;
        }

        public void setRamTypes(List<String> ramTypes) {
            this.ramTypes = ramTypes;
        }

        public List<String> getFormFactors() {
            return formFactors;
        }

        public void setFormFactors(List<String> formFactors) {
            this.formFactors = formFactors;
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

        ArrayList<Motherboard> motherboards = (ArrayList<Motherboard>) motherboardDAO.doRetrieveAll();
        List<String> sockets = motherboardDAO.doRetrieveDistinctSockets();
        List<String> chipsets = motherboardDAO.doRetrieveDistinctChipsets();
        List<String> ramTypes = motherboardDAO.doRetrieveDistinctRamTypes();
        List<String> formFactors = motherboardDAO.doRetrieveDistinctFormFactors();

        // Converti in JSON e invia la risposta
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DataWrapper dataWrapper = new DataWrapper(motherboards, sockets, chipsets, ramTypes, formFactors);
        String jsonResponse = gson.toJson(dataWrapper);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        System.out.println("GET: /motherboards");
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

            List<Motherboard> motherboards = new ArrayList<>();

            // Logica per i filtri e ordinamenti singoli
            if (filterParams.name != null && !filterParams.name.isEmpty()) {
                // Ricerca per nome
                motherboards = motherboardDAO.doRetrieveByName(filterParams.name);
            } else if (filterParams.ratingSort != null && !filterParams.ratingSort.isEmpty()) {
                // Ordinamento per rating
                motherboards = filterParams.ratingSort.equals("asc")
                        ? motherboardDAO.doRetrieveAllByRatingAsc()
                        : motherboardDAO.doRetrieveAllByRatingDesc();
            } else if (filterParams.priceSort != null && !filterParams.priceSort.isEmpty()) {
                // Ordinamento per prezzo
                motherboards = filterParams.priceSort.equals("asc")
                        ? motherboardDAO.doRetrieveAllByPriceAsc()
                        : motherboardDAO.doRetrieveAllByPriceDesc();
            } else {
                // Filtro composito
                motherboards = motherboardDAO.doRetrieveFiltered(
                        filterParams.minRating,
                        filterParams.maxRating,
                        filterParams.minPrice,
                        filterParams.maxPrice,
                        filterParams.socket,
                        filterParams.chipset,
                        filterParams.ramType,
                        filterParams.formFactor
                );
            }

            List<String> sockets = motherboardDAO.doRetrieveDistinctSockets();
            List<String> chipsets = motherboardDAO.doRetrieveDistinctChipsets();
            List<String> ramTypes = motherboardDAO.doRetrieveDistinctRamTypes();
            List<String> formFactors = motherboardDAO.doRetrieveDistinctFormFactors();

            DataWrapper dataWrapper = new DataWrapper((ArrayList<Motherboard>) motherboards, sockets, chipsets, ramTypes, formFactors);
            String jsonResponse = gson.toJson(dataWrapper);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
            System.out.println("POST: /motherboards/filters\n" + filterParams.toString());
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint.");
        }
    }
}