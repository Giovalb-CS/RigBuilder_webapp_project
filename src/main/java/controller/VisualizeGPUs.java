package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.GPU;
import model.GPUDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisualizeGPUs", value = "/gpus")
public class VisualizeGPUs extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            GPUDAO gpuDao = new GPUDAO();

            List<String> memoryTypes = gpuDao.doRetrieveDistinctMemoryTypes();
            request.setAttribute("memoryTypes", memoryTypes);

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String ratingSort = request.getParameter("ratingSort");
            String priceSort = request.getParameter("priceSort");
            String minPrice = request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice");
            String memoryType = request.getParameter("memoryType");

            String compositeMinPrice = request.getParameter("compositeMinPrice");
            String compositeMaxPrice = request.getParameter("compositeMaxPrice");
            String compositeMemoryType = request.getParameter("compositeMemoryType");
            String compositeMaxSlotWidth = request.getParameter("compositeMaxSlotWidth");
            String compositeMaxLenght = request.getParameter("compositeMaxLenght");
            String compositeMinRating = request.getParameter("compositeMinRating");
            String compositeMaxRating = request.getParameter("compositeMaxRating");

            List<GPU> gpus = new ArrayList<>();

            if (id != null && !id.isEmpty()) {
                GPU gpu = gpuDao.doRetrieveByID(Integer.parseInt(id));
                if (gpu != null) {
                    gpus.add(gpu);
                }
            } else if (name != null && !name.isEmpty()) {
                gpus = gpuDao.doRetrieveByName(name);
            } else if (ratingSort != null && !ratingSort.isEmpty()) {
                gpus = ratingSort.equals("asc")
                        ? gpuDao.doRetrieveAllByRatingAsc()
                        : gpuDao.doRetrieveAllByRatingDesc();
            } else if (priceSort != null && !priceSort.isEmpty()) {
                gpus = priceSort.equals("asc")
                        ? gpuDao.doRetrieveAllByPriceAsc()
                        : gpuDao.doRetrieveAllByPriceDesc();
            } else if (minPrice != null && maxPrice != null) {
                gpus = gpuDao.doRetrieveAllByPriceBetween(Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
            }else if (memoryType != null && !memoryType.isEmpty()) {
                gpus = gpuDao.doRetrieveAllByMemory(memoryType);
            } else {
                gpus = gpuDao.doRetrieveAll();
            }

            if (
                    compositeMinPrice != null ||
                            compositeMaxPrice != null ||
                            (compositeMemoryType != null && !compositeMemoryType.isEmpty()) ||
                            (compositeMaxSlotWidth != null && !compositeMaxSlotWidth.isEmpty()) ||
                            compositeMaxLenght != null ||
                            compositeMinRating != null ||
                            compositeMaxRating != null)
            {
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

                Integer minSlotWidthVal = (compositeMaxSlotWidth != null && !compositeMaxSlotWidth.isEmpty())
                        ? Integer.parseInt(compositeMaxSlotWidth)
                        : null;

                Integer maxLenghtVal = (compositeMaxLenght != null && !compositeMaxLenght.isEmpty())
                        ? Integer.parseInt(compositeMaxLenght)
                        : null;

                gpus = gpuDao.doRetrieveFiltered(
                        minPriceVal,
                        maxPriceVal,
                        compositeMemoryType != null && !compositeMemoryType.isEmpty() ? compositeMemoryType : null,
                        maxLenghtVal,
                        minSlotWidthVal,
                        minRatingVal,
                        maxRatingVal
                );
            }

            request.setAttribute("gpus", gpus);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/gpus.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            ArrayList<GPU> gpus = (ArrayList<GPU>) (new GPUDAO()).doRetrieveAll();
            request.setAttribute("gpus", gpus);
            GPUDAO gpuDao = new GPUDAO();
            List<String> memoryTypes = gpuDao.doRetrieveDistinctMemoryTypes();
            request.setAttribute("memoryTypes", memoryTypes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/gpus.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}