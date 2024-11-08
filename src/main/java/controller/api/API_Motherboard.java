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
    }

    private final MotherboardDAO motherboardDAO = new MotherboardDAO();

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

        List<Motherboard> motherboards = motherboardDAO.doRetrieveAll();

        // Converti in JSON e invia la risposta
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String motherboardsJson = gson.toJson(motherboards);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(motherboardsJson);
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

            // Converti la lista di processori in JSON e imposta la risposta
            String motherboardsJson = gson.toJson(motherboards);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(motherboardsJson);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint.");
        }
    }
}