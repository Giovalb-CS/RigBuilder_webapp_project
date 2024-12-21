package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Processor;
import model.RAM;
import model.RAMDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "API_RAM", value = "/api/rams/*")
public class API_RAM extends HttpServlet {

    private static class FilterParams {
        String name;
        String priceSort;
        String ratingSort;
        Double minPrice;
        Double maxPrice;
        String ramType;
        Integer minClock;
        Integer maxClock;
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
                    ", ramType='" + ramType + '\'' +
                    ", minClock=" + minClock +
                    ", maxClock=" + maxClock +
                    ", minRating=" + minRating +
                    ", maxRating=" + maxRating +
                    '}';
        }
    }

    private final RAMDAO ramDAO = new RAMDAO();

    public static class DataWrapper {
        List<RAM> rams;
        List<String> ramTypes;

        public List<RAM> getRams() {
            return rams;
        }

        public void setRams(List<RAM> rams) {
            this.rams = rams;
        }

        public List<String> getRamTypes() {
            return ramTypes;
        }

        public void setRamTypes(List<String> ramTypes) {
            this.ramTypes = ramTypes;
        }

        public DataWrapper(List<RAM> rams, List<String> ramTypes) {
            this.rams = rams;
            this.ramTypes = ramTypes;
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

        List<RAM> rams = ramDAO.doRetrieveAll();
        List<String> ramTypes = ramDAO.doRetrieveDistinctRamTypes();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DataWrapper dataWrapper = new DataWrapper(rams, ramTypes);
        String jsonResponse = gson.toJson(dataWrapper);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        System.out.println("GET: /rams");
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
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }

            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            FilterParams filterParams = gson.fromJson(jsonBody.toString(), FilterParams.class);

            List<RAM> rams = new ArrayList<>();

            if (filterParams.name != null && !filterParams.name.isEmpty()) {
                rams = ramDAO.doRetrieveByName(filterParams.name);
            } else if (filterParams.ratingSort != null && !filterParams.ratingSort.isEmpty()) {
                rams = filterParams.ratingSort.equals("asc")
                        ? ramDAO.doRetrieveAllByRatingAsc()
                        : ramDAO.doRetrieveAllByRatingDesc();
            } else if (filterParams.priceSort != null && !filterParams.priceSort.isEmpty()) {
                rams = filterParams.priceSort.equals("asc")
                        ? ramDAO.doRetrieveAllByPriceAsc()
                        : ramDAO.doRetrieveAllByPriceDesc();
            } else {
                rams = ramDAO.doRetrieveFiltered(
                        filterParams.minRating,
                        filterParams.maxRating,
                        filterParams.minPrice,
                        filterParams.maxPrice,
                        filterParams.ramType,
                        filterParams.minClock,
                        filterParams.maxClock
                );
            }

            List<String> ramTypes = ramDAO.doRetrieveDistinctRamTypes();

            DataWrapper dataWrapper = new DataWrapper(rams, ramTypes);
            String jsonResponse = gson.toJson(dataWrapper);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
            System.out.println("POST: /rams/filters\n" + filterParams.toString());
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint.");
        }
    }
}