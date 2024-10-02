package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Processor;
import model.ProcessorDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisualizeProcessors", value = "/processors")
public class VisualizeProcessors extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProcessorDAO processorDao = new ProcessorDAO();

        // Recupero dei valori unici di socket e ramType per i <select>
        List<String> sockets = processorDao.doRetrieveDistinctSockets();
        List<String> ramTypes = processorDao.doRetrieveDistinctRamTypes();
        request.setAttribute("sockets", sockets);
        request.setAttribute("ramTypes", ramTypes);

        // Recupero dei parametri dal form
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String ratingSort = request.getParameter("ratingSort");
        String priceSort = request.getParameter("priceSort");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String socket = request.getParameter("socket");
        String ramType = request.getParameter("ramType");

        // Filtro composito
        String compositeMinPrice = request.getParameter("compositeMinPrice");
        String compositeMaxPrice = request.getParameter("compositeMaxPrice");
        String compositeSocket = request.getParameter("compositeSocket");
        String compositeRamType = request.getParameter("compositeRamType");
        String compositeMinRating = request.getParameter("compositeMinRating");
        String compositeMaxRating = request.getParameter("compositeMaxRating");

        // Lista dei processori filtrati
        List<Processor> processors = new ArrayList<>();

        // Logica dei filtri singoli
        if (id != null && !id.isEmpty()) {
            Processor processor = processorDao.doRetrieveByID(Integer.parseInt(id));
            if ((processor != null)) {
                processors.add(processor);
            }
        } else if (name != null && !name.isEmpty()) {
            processors = processorDao.doRetrieveByName(name);
        } else if (ratingSort != null) {
            processors = ratingSort.equals("asc")
                    ? processorDao.doRetrieveAllByRatingAsc()
                    : processorDao.doRetrieveAllByRatingDesc();
        } else if (priceSort != null) {
            processors = priceSort.equals("asc")
                    ? processorDao.doRetrieveAllByPriceAsc()
                    : processorDao.doRetrieveAllByPriceDesc();
        } else if (minPrice != null && maxPrice != null) {
            processors = processorDao.doRetrieveAllByPriceBetween(
                    Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
        } else if (socket != null && !socket.isEmpty()) {
            processors = processorDao.doRetrieveBySocket(socket);
        } else if (ramType != null && !ramType.isEmpty()) {
            processors = processorDao.doRetrieveByRAMType(ramType);
        } else {
            processors = processorDao.doRetrieveAll();
        }

        // Logica del filtro composito
        if (compositeMinPrice != null || compositeMaxPrice != null ||
                (compositeSocket != null && !compositeSocket.isEmpty()) ||
                (compositeRamType != null && !compositeRamType.isEmpty()) ||
                compositeMinRating != null || compositeMaxRating != null) {

            // Trasforma solo se il parametro non è vuoto o nullo
            Double minPriceVal = (compositeMinPrice != null && !compositeMinPrice.isEmpty())
                    ? Double.parseDouble(compositeMinPrice)
                    : null;

            Double maxPriceVal = (compositeMaxPrice != null && !compositeMaxPrice.isEmpty())
                    ? Double.parseDouble(compositeMaxPrice)
                    : null;

            Double minRatingVal = (compositeMinRating != null && !compositeMinRating.isEmpty())
                    ? Double.parseDouble(compositeMinRating)
                    : null;

            Double maxRatingVal = (compositeMaxRating != null && !compositeMaxRating.isEmpty())
                    ? Double.parseDouble(compositeMaxRating)
                    : null;

            // Esegui il filtro composito
            processors = processorDao.doRetrieveFiltered(
                    minPriceVal,
                    maxPriceVal,
                    compositeSocket != null && !compositeSocket.isEmpty() ? compositeSocket : null,
                    compositeRamType != null && !compositeRamType.isEmpty() ? compositeRamType : null,
                    minRatingVal,
                    maxRatingVal
            );
        }

        // Imposta i processori come attributo nella richiesta
        request.setAttribute("processors", processors);

        // Forward alla JSP per visualizzare i risultati
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/processors.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            ArrayList<Processor> processors = (ArrayList<Processor>) (new ProcessorDAO()).doRetrieveAll();
            request.setAttribute("processors", processors);
            ProcessorDAO processorDao = new ProcessorDAO();
            List<String> sockets = processorDao.doRetrieveDistinctSockets();
            List<String> ramTypes = processorDao.doRetrieveDistinctRamTypes();
            request.setAttribute("sockets", sockets);
            request.setAttribute("ramTypes", ramTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/processors.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}
