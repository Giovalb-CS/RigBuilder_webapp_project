package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Casebox;
import model.CaseboxDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "API_CaseBox", value = "/api/cases/*")
public class API_CaseBox extends HttpServlet {

    private static class FilterParams {
        String name;
        String priceSort;
        String ratingSort;
        Double minPrice;
        Double maxPrice;
        Integer minCoolerHeight;
        Integer minRadiatorSize;
        Integer minGPULength;
        String formFactor;
        Integer minPSULength;
        Integer minPCIeSlots;
        Double minRating;
        Double maxRating;
    }

    private final CaseboxDAO caseboxDAO = new CaseboxDAO();

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

        List<Casebox> cases = caseboxDAO.doRetrieveAll();

        // Converti in JSON e invia la risposta
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String casesJson = gson.toJson(cases);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(casesJson);
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

            List<Casebox> cases = new ArrayList<>();

            // Logica per i filtri e ordinamenti singoli
            if (filterParams.name != null && !filterParams.name.isEmpty()) {
                // Ricerca per nome
                cases = caseboxDAO.doRetrieveByName(filterParams.name);
            } else if (filterParams.ratingSort != null && !filterParams.ratingSort.isEmpty()) {
                // Ordinamento per rating
                cases = filterParams.ratingSort.equals("asc")
                        ? caseboxDAO.doRetrieveAllByRatingAsc()
                        : caseboxDAO.doRetrieveAllByRatingDesc();
            } else if (filterParams.priceSort != null && !filterParams.priceSort.isEmpty()) {
                // Ordinamento per prezzo
                cases = filterParams.priceSort.equals("asc")
                        ? caseboxDAO.doRetrieveAllByPriceAsc()
                        : caseboxDAO.doRetrieveAllByPriceDesc();
            } else {
                // Filtro composito
                cases = caseboxDAO.doRetrieveFiltered(
                        filterParams.minRating,
                        filterParams.maxRating,
                        filterParams.minPrice,
                        filterParams.maxPrice,
                        filterParams.minCoolerHeight,
                        filterParams.minRadiatorSize,
                        filterParams.minGPULength,
                        filterParams.formFactor,
                        filterParams.minPSULength,
                        filterParams.minPCIeSlots
                );
            }

            String casesJson = gson.toJson(cases);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(casesJson);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint.");
        }
    }
}