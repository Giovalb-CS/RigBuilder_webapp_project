package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Processor;
import model.ProcessorDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "API_Processor", value = "/api/processors/*")
public class API_Processor extends HttpServlet {

    private static class FilterParams {
        String name;
        String priceSort;
        String ratingSort;
        Double minPrice;
        Double maxPrice;
        String socket;
        String ramType;
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
                    ", ramType='" + ramType + '\'' +
                    ", minRating=" + minRating +
                    ", maxRating=" + maxRating +
                    '}';
        }
    }

    private final ProcessorDAO processorDao = new ProcessorDAO();

    // Creazione di una classe wrapper per includere tutti i dati
    public static class DataWrapper {
        List<Processor> processors;
        List<String> sockets;
        List<String> ramTypes;

        public DataWrapper(List<Processor> processors, List<String> sockets, List<String> ramTypes) {
            this.processors = processors;
            this.sockets = sockets;
            this.ramTypes = ramTypes;
        }

        public List<Processor> getProcessors() {
            return processors;
        }

        public void setProcessors(List<Processor> processors) {
            this.processors = processors;
        }

        public List<String> getSockets() {
            return sockets;
        }

        public void setSockets(List<String> sockets) {
            this.sockets = sockets;
        }

        public List<String> getRamTypes() {
            return ramTypes;
        }

        public void setRamTypes(List<String> ramTypes) {
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

        // Recupera i processori, i socket e i tipi di RAM
        List<Processor> processors = processorDao.doRetrieveAll();
        List<String> sockets = processorDao.doRetrieveDistinctSockets();
        List<String> ramTypes = processorDao.doRetrieveDistinctRamTypes();

        // Invia i dati al client come JSON
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DataWrapper dataWrapper = new DataWrapper(processors, sockets, ramTypes);
        String jsonResponse = gson.toJson(dataWrapper);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        System.out.println("GET: /processors");
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

            List<Processor> processors = new ArrayList<>();

            if (filterParams.name != null && !filterParams.name.isEmpty()) {
                processors = processorDao.doRetrieveByName(filterParams.name);
            } else if (filterParams.ratingSort != null && !filterParams.ratingSort.isEmpty()) {
                processors = filterParams.ratingSort.equals("asc")
                        ? processorDao.doRetrieveAllByRatingAsc()
                        : processorDao.doRetrieveAllByRatingDesc();
            } else if (filterParams.priceSort != null && !filterParams.priceSort.isEmpty()) {
                processors = filterParams.priceSort.equals("asc")
                        ? processorDao.doRetrieveAllByPriceAsc()
                        : processorDao.doRetrieveAllByPriceDesc();
            } else {
                processors = processorDao.doRetrieveFiltered(
                        filterParams.minPrice,
                        filterParams.maxPrice,
                        filterParams.socket,
                        filterParams.ramType,
                        filterParams.minRating,
                        filterParams.maxRating
                );
            }

            List<String> sockets = processorDao.doRetrieveDistinctSockets();
            List<String> ramTypes = processorDao.doRetrieveDistinctRamTypes();

            DataWrapper dataWrapper = new DataWrapper(processors, sockets, ramTypes);
            String jsonResponse = gson.toJson(dataWrapper);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
            System.out.println("POST: /processors/filters\n" + filterParams.toString());
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint.");
        }
    }
}
