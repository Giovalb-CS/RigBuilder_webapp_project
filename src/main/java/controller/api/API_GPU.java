package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.GPU;
import model.GPUDAO;
import model.Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "API_GPU", value = "/api/gpus/*")
public class API_GPU extends HttpServlet {

    private static class FilterParams {
        String name;
        String priceSort;
        String ratingSort;
        Double minPrice;
        Double maxPrice;
        String memoryType;
        Integer maxSlotWidth;
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
                    ", memoryType='" + memoryType + '\'' +
                    ", maxSlotWidth=" + maxSlotWidth +
                    ", maxLenght=" + maxLenght +
                    ", minRating=" + minRating +
                    ", maxRating=" + maxRating +
                    '}';
        }
    }

    private final GPUDAO gpuDAO = new GPUDAO();

    public static class DataWrapper {
        List<GPU> gpus;
        List<String> memoryTypes;

        public List<GPU> getGpus() {
            return gpus;
        }

        public void setGpus(List<GPU> gpus) {
            this.gpus = gpus;
        }

        public List<String> getMemoryTypes() {
            return memoryTypes;
        }

        public void setMemoryTypes(List<String> memoryTypes) {
            this.memoryTypes = memoryTypes;
        }

        public DataWrapper(List<GPU> gpus, List<String> memoryTypes) {
            this.gpus = gpus;
            this.memoryTypes = memoryTypes;
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

        List<GPU> gpus = gpuDAO.doRetrieveAll();
        List<String> memoryTypes = gpuDAO.doRetrieveDistinctMemoryTypes();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DataWrapper dataWrapper = new DataWrapper(gpus, memoryTypes);
        String jsonResponse = gson.toJson(dataWrapper);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        System.out.println("GET: /gpus");
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

            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            FilterParams filterParams = gson.fromJson(jsonBody.toString(), FilterParams.class);

            List<GPU> gpus = new ArrayList<>();

            // Logica per i filtri e ordinamenti singoli
            if (filterParams.name != null && !filterParams.name.isEmpty()) {
                // Ricerca per nome
                gpus = gpuDAO.doRetrieveByName(filterParams.name);
            } else if (filterParams.ratingSort != null && !filterParams.ratingSort.isEmpty()) {
                // Ordinamento per rating
                gpus = filterParams.ratingSort.equals("asc")
                        ? gpuDAO.doRetrieveAllByRatingAsc()
                        : gpuDAO.doRetrieveAllByRatingDesc();
            } else if (filterParams.priceSort != null && !filterParams.priceSort.isEmpty()) {
                // Ordinamento per prezzo
                gpus = filterParams.priceSort.equals("asc")
                        ? gpuDAO.doRetrieveAllByPriceAsc()
                        : gpuDAO.doRetrieveAllByPriceDesc();
            } else {
                // Filtro composito
                gpus = gpuDAO.doRetrieveFiltered(
                        filterParams.minPrice,
                        filterParams.maxPrice,
                        filterParams.memoryType,
                        filterParams.maxLenght,
                        filterParams.maxSlotWidth,
                        filterParams.minRating,
                        filterParams.maxRating
                );
            }

            List<String> memoryTypes = gpuDAO.doRetrieveDistinctMemoryTypes();

            DataWrapper dataWrapper = new DataWrapper(gpus, memoryTypes);
            String gpusJson = gson.toJson(dataWrapper);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gpusJson);
            System.out.println("POST: /gpus/filters\n" + filterParams.toString());
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint.");
        }
    }
}