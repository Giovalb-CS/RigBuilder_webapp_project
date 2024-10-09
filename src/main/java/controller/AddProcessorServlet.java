package controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddProcessorServlet", value = "/addProcessor")
public class AddProcessorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            ProcessorDAO processorDao = new ProcessorDAO();
            List<String> sockets = processorDao.doRetrieveDistinctSockets();
            List<String> ramTypes = processorDao.doRetrieveDistinctRamTypes();
            request.setAttribute("sockets", sockets);
            request.setAttribute("ramTypes", ramTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addProcessor.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProcessorDAO processorDao = new ProcessorDAO();
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
            List<Processor> processors = processorDao.doRetrieveByName(name);
            if (processors.isEmpty()) {
                Processor processor = new Processor();
                processor.setName(name);
                processor.setRating(Double.parseDouble(rating));
                processor.setPrice(Double.parseDouble(price));
                processor.setShop_URL(shop_URL);
                processor.setImage_URL(image_URL);
                processor.setTdp(Integer.parseInt(tdp));
                processor.setSocket(socket);
                processor.setRam_type(ramType);
                processor.setCore(Integer.parseInt(core));
                processor.setThread(Integer.parseInt(thread));
                processor.setClock_base(Double.parseDouble(clock_base));
                processor.setClock_boost(Double.parseDouble(clock_boost));
                processor.setCache(Integer.parseInt(cache));
                processor.setScale(Integer.parseInt(scale));
                processor.setGeneration(generation);

                int id = processorDao.doSave(processor);

                GestireProcessor gestireProcessor = new GestireProcessor();
                GestireProcessorDAO gestireProcessorDao = new GestireProcessorDAO();
                Administrator administrator = (Administrator) session.getAttribute("administrator");
                gestireProcessor.setIdProcessor(id);
                gestireProcessor.setIdAdmin(administrator.getId());
                gestireProcessorDao.doSave(gestireProcessor);

                response.sendRedirect("processors");
            }
            else {
                List<String> sockets = processorDao.doRetrieveDistinctSockets();
                List<String> ramTypes = processorDao.doRetrieveDistinctRamTypes();
                request.setAttribute("sockets", sockets);
                request.setAttribute("ramTypes", ramTypes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/addProcessor.jsp?alreadyExists=1");
                request.setAttribute("alreadyExists", 1);
                dispatcher.forward(request, response);
            }
        }
        else {
            List<String> sockets = processorDao.doRetrieveDistinctSockets();
            List<String> ramTypes = processorDao.doRetrieveDistinctRamTypes();
            request.setAttribute("sockets", sockets);
            request.setAttribute("ramTypes", ramTypes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/addProcessor.jsp?formError=1");
            request.setAttribute("formError", 1);
            dispatcher.forward(request, response);
        }
    }
}