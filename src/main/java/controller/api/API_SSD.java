package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.SSD;
import model.SSDDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "API_SSD", value = "/api/ssds/*")
public class API_SSD extends HttpServlet {

    private static class FilterParams {
        String name;
        String priceSort;
        String ratingSort;
        Double minPrice;
        Double maxPrice;
        String pcie_gen;
        String capacity;
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
                    ", pcie_gen='" + pcie_gen + '\'' +
                    ", capacity='" + capacity + '\'' +
                    ", minRating=" + minRating +
                    ", maxRating=" + maxRating +
                    '}';
        }
    }

    private final SSDDAO ssdDAO = new SSDDAO();

    public static class DataWrapper {
        List<SSD> ssds;
        List<String> pcie_gens;
        List<String> capacities;

        public List<SSD> getSsds() {
            return ssds;
        }

        public void setSsds(List<SSD> ssds) {
            this.ssds = ssds;
        }

        public List<String> getPcie_gens() {
            return pcie_gens;
        }

        public void setPcie_gens(List<String> pcie_gens) {
            this.pcie_gens = pcie_gens;
        }

        public List<String> getCapacities() {
            return capacities;
        }

        public void setCapacities(List<String> capacities) {
            this.capacities = capacities;
        }

        public DataWrapper(List<SSD> ssds, List<String> pcie_gens, List<String> capacities) {
            this.ssds = ssds;
            this.pcie_gens = pcie_gens;
            this.capacities = capacities;
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

        List<SSD> ssds = ssdDAO.doRetrieveAll();
        List<String> pcie_gens = ssdDAO.doRetrieveDistinctPCIeGenerations();
        List<String> capacities = ssdDAO.doRetrieveDistinctCapacities();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DataWrapper data = new DataWrapper(ssds, pcie_gens, capacities);
        String jsonResponse = gson.toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        System.out.println("GET: /ssds");
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

            List<SSD> ssds = new ArrayList<>();

            if (filterParams.name != null && !filterParams.name.isEmpty()) {
                ssds = ssdDAO.doRetrieveByName(filterParams.name);
            } else if (filterParams.ratingSort != null && !filterParams.ratingSort.isEmpty()) {
                ssds = filterParams.ratingSort.equals("asc")
                        ? ssdDAO.doRetrieveAllByRatingAsc()
                        : ssdDAO.doRetrieveAllByRatingDesc();
            } else if (filterParams.priceSort != null && !filterParams.priceSort.isEmpty()) {
                ssds = filterParams.priceSort.equals("asc")
                        ? ssdDAO.doRetrieveAllByPriceAsc()
                        : ssdDAO.doRetrieveAllByPriceDesc();
            } else {
                ssds = ssdDAO.doRetrieveFiltered(
                        filterParams.minPrice,
                        filterParams.maxPrice,
                        filterParams.minRating,
                        filterParams.maxRating,
                        filterParams.capacity,
                        filterParams.pcie_gen
                        );
            }

            List<String> pcie_gens = ssdDAO.doRetrieveDistinctPCIeGenerations();
            List<String> capacities = ssdDAO.doRetrieveDistinctCapacities();

            DataWrapper data = new DataWrapper(ssds, pcie_gens, capacities);
            String jsonResponse = gson.toJson(data);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
            System.out.println("POST: /ssds/filters\n" + filterParams.toString());
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint.");
        }
    }
}