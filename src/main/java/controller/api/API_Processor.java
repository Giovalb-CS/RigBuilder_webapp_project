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
import java.util.List;

@WebServlet(name = "API_Processor", value = "/api/processors/*")
public class API_Processor extends HttpServlet {

    private static class FilterParams {
        Double minPrice;
        Double maxPrice;
        String socket;
        String ramType;
        Double minRating;
        Double maxRating;
    }


    private final ProcessorDAO processorDao = new ProcessorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Processor> processors = processorDao.doRetrieveAll();

        // Converti in JSON e invia la risposta
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String processorsJson = gson.toJson(processors);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(processorsJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            List<Processor> processors = processorDao.doRetrieveFiltered(
                    filterParams.minPrice,
                    filterParams.maxPrice,
                    filterParams.socket,
                    filterParams.ramType,
                    filterParams.minRating,
                    filterParams.maxRating
            );

            // Converti la lista di processori in JSON e imposta la risposta
            String processorsJson = gson.toJson(processors);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(processorsJson);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endpoint.");
        }
    }
}
