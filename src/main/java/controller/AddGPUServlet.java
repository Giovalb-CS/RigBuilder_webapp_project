package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddGPUServlet", value = "/addGpu")
public class AddGPUServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            GPUDAO gpuDao = new GPUDAO();
            List<String> memoryTypes = gpuDao.doRetrieveDistinctMemoryTypes();
            request.setAttribute("memoryTypes", memoryTypes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/addGpu.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        GPUDAO gpuDao = new GPUDAO();
        String name = request.getParameter("name");
        String rating = request.getParameter("rating");
        String price = request.getParameter("price");
        String shop_URL = request.getParameter("shop_URL");
        String image_URL = request.getParameter("image_URL");
        String tdp = request.getParameter("tdp");
        String memoryType = request.getParameter("memoryType");
        String memoryClock = request.getParameter("memoryClock");
        String coreClock = request.getParameter("coreClock");
        String boostClock = request.getParameter("boostClock");
        String lenght = request.getParameter("lenght");
        String slotWidth = request.getParameter("slotWidth");
        String powerCable = request.getParameter("powerCable");

        if (
                name!=null && !name.isEmpty()
                && rating!=null && !rating.isEmpty()
                && price!=null && !price.isEmpty()
                && shop_URL!=null && !shop_URL.isEmpty()
                && image_URL!=null && !image_URL.isEmpty()
                && tdp!=null && !tdp.isEmpty()
                && memoryType!=null && !memoryType.isEmpty()
                && memoryClock!=null && !memoryClock.isEmpty()
                && coreClock!=null && !coreClock.isEmpty()
                && boostClock!=null && !boostClock.isEmpty()
                && lenght!=null && !lenght.isEmpty()
                && slotWidth!=null && !slotWidth.isEmpty()
                && powerCable!=null && !powerCable.isEmpty()
        ) {
            List<GPU> gpus = gpuDao.doRetrieveByName(name);
            if (gpus.isEmpty()) {
                GPU gpu = new GPU();
                gpu.setName(name);
                gpu.setRating(Double.parseDouble(rating));
                gpu.setPrice(Double.parseDouble(price));
                gpu.setShop_URL(shop_URL);
                gpu.setImage_URL(image_URL);
                gpu.setTdp(Integer.parseInt(tdp));
                gpu.setMemory(memoryType);
                gpu.setMemory_clock(Integer.parseInt(memoryClock));
                gpu.setCore_clock(Integer.parseInt(coreClock));
                gpu.setBoost_clock(Integer.parseInt(boostClock));
                gpu.setLenght(Integer.parseInt(lenght));
                gpu.setSlot_width(Integer.parseInt(slotWidth));
                gpu.setPower_cable(powerCable);

                int id = gpuDao.doSave(gpu);

                GestireGPU gestireGPU = new GestireGPU();
                GestireGPUDAO gestireGPUDAO = new GestireGPUDAO();
                Administrator administrator = (Administrator) session.getAttribute("administrator");
                gestireGPU.setIdGPU(id);
                gestireGPU.setIdAdmin(administrator.getId());
                gestireGPUDAO.doSave(gestireGPU);

                response.sendRedirect("gpus");
            } else {
                List<String> memoryTypes = gpuDao.doRetrieveDistinctMemoryTypes();
                request.setAttribute("memoryTypes", memoryTypes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/addGpu.jsp?alreadyExists=1");
                request.setAttribute("alreadyExists", 1);
                dispatcher.forward(request, response);
            }
        } else {
            List<String> memoryTypes = gpuDao.doRetrieveDistinctMemoryTypes();
            request.setAttribute("memoryTypes", memoryTypes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/addGpu.jsp?formError=1");
            request.setAttribute("formError", 1);
            dispatcher.forward(request, response);
        }
    }
}