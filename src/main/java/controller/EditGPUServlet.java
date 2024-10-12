package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.GPU;
import model.GPUDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditGPUServlet", value = "/editGpu")
public class EditGPUServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            GPUDAO gpuDao = new GPUDAO();
            List<String> memoryTypes = gpuDao.doRetrieveDistinctMemoryTypes();
            GPU gpu = gpuDao.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("gpu", gpu);
            request.setAttribute("memoryTypes", memoryTypes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/editGpu.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        GPUDAO gpuDao = new GPUDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        GPU gpu_before = gpuDao.doRetrieveByID(id);
        GPU gpu_after = new GPU();
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
            gpu_after.setId(id);
            gpu_after.setName(name);
            gpu_after.setRating(Double.parseDouble(rating));
            gpu_after.setPrice(Double.parseDouble(price));
            gpu_after.setShop_URL(shop_URL);
            gpu_after.setImage_URL(image_URL);
            gpu_after.setTdp(Integer.parseInt(tdp));
            gpu_after.setMemory(memoryType);
            gpu_after.setMemory_clock(Integer.parseInt(memoryClock));
            gpu_after.setCore_clock(Integer.parseInt(coreClock));
            gpu_after.setBoost_clock(Integer.parseInt(boostClock));
            gpu_after.setLenght(Integer.parseInt(lenght));
            gpu_after.setSlot_width(Integer.parseInt(slotWidth));
            gpu_after.setPower_cable(powerCable);

            gpuDao.doModify(gpu_after);

            response.sendRedirect("gpus");
        }
        else {
            List<String> memoryTypes = gpuDao.doRetrieveDistinctMemoryTypes();
            GPU gpu = gpuDao.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("gpu", gpu);
            request.setAttribute("memoryTypes", memoryTypes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/editGpu.jsp?formError=1");
            request.setAttribute("formError", 1);
            dispatcher.forward(request, response);
        }
    }
}