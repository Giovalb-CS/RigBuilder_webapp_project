package controller.api;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.GPUDAO;

import java.io.IOException;

@WebServlet(name = "API_GPU", value = "/api/gpus/*")
public class API_GPU extends HttpServlet {

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
    }

    private final GPUDAO gpuDAO = new GPUDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}