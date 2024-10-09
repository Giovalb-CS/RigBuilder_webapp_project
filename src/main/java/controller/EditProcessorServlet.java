package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Processor;
import model.ProcessorDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditProcessorServlet", value = "/editProcessor")
public class EditProcessorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            ProcessorDAO processorDao = new ProcessorDAO();
            List<String> sockets = processorDao.doRetrieveDistinctSockets();
            List<String> ramTypes = processorDao.doRetrieveDistinctRamTypes();
            Processor processor = processorDao.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("processor", processor);
            request.setAttribute("sockets", sockets);
            request.setAttribute("ramTypes", ramTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editProcessor.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProcessorDAO processorDao = new ProcessorDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Processor processor_before = processorDao.doRetrieveByID(id);
        Processor processor_after = new Processor();
        String name = request.getParameter("name");
        String rating  = request.getParameter("rating");
        String price = request.getParameter("price");
        String shop_URL = request.getParameter("shop_URL");
        String image_URL = request.getParameter("image_URL");
        String tdp = request.getParameter("tdp");
        String socket = request.getParameter("socket");
        String ramType = request.getParameter("ramType");
        String core = request.getParameter("core");
        String thread = request.getParameter("thread");
        String clock_base = request.getParameter("clock_base");
        String clock_boost = request.getParameter("clock_boost");
        String cache = request.getParameter("cache");
        String scale = request.getParameter("scale");
        String generation = request.getParameter("generation");

        if (
                name!=null && !name.isEmpty()
                && rating!=null && !rating.isEmpty()
                && price!=null && !price.isEmpty()
                && shop_URL!=null && !shop_URL.isEmpty()
                && image_URL!=null && !image_URL.isEmpty()
                && tdp!=null && !tdp.isEmpty()
                && socket!=null && !socket.isEmpty()
                && ramType!=null && !ramType.isEmpty()
                && core!=null && !core.isEmpty()
                && thread!=null && !thread.isEmpty()
                && clock_base!=null && !clock_base.isEmpty()
                && clock_boost!=null && !clock_boost.isEmpty()
                && cache!=null && !cache.isEmpty()
                && scale!=null && !scale.isEmpty()
                && generation!=null && !generation.isEmpty()
        ) {
            processor_after.setId(id);
            processor_after.setName(name);
            processor_after.setRating(Double.parseDouble(rating));
            processor_after.setPrice(Double.parseDouble(price));
            processor_after.setShop_URL(shop_URL);
            processor_after.setImage_URL(image_URL);
            processor_after.setTdp(Integer.parseInt(tdp));
            processor_after.setSocket(socket);
            processor_after.setRam_type(ramType);
            processor_after.setCore(Integer.parseInt(core));
            processor_after.setThread(Integer.parseInt(thread));
            processor_after.setClock_base(Double.parseDouble(clock_base));
            processor_after.setClock_boost(Double.parseDouble(clock_boost));
            processor_after.setCache(Integer.parseInt(cache));
            processor_after.setScale(Integer.parseInt(scale));
            processor_after.setGeneration(generation);

            processorDao.doModify(processor_after);

            response.sendRedirect("processors");
        }
        else {
            List<String> sockets = processorDao.doRetrieveDistinctSockets();
            List<String> ramTypes = processorDao.doRetrieveDistinctRamTypes();
            Processor processor = processorDao.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("processor", processor);
            request.setAttribute("sockets", sockets);
            request.setAttribute("ramTypes", ramTypes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/editProcessor.jsp");
            request.setAttribute("formError", 1);
            dispatcher.forward(request, response);
        }
    }
}