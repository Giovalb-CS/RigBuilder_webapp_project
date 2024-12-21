package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddCoolerServlet", value = "/addCooler")
public class AddCoolerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            CoolerDAO coolerDAO = new CoolerDAO();
            List<String> sockets = coolerDAO.doRetrieveDistinctSockets();
            List<String> radiatorSizes = coolerDAO.doRetrieveDistinctRadiatorSizes();
            request.setAttribute("sockets", sockets);
            request.setAttribute("radiatorSizes", radiatorSizes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addCooler.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CoolerDAO coolerDAO = new CoolerDAO();

        String name = request.getParameter("name");
        String rating  = request.getParameter("rating");
        String price = request.getParameter("price");
        String shop_URL = request.getParameter("shop_URL");
        String image_URL = request.getParameter("image_URL");
        String tdp = request.getParameter("tdp");
        String socket = request.getParameter("socket");
        String rpm = request.getParameter("rpm");
        String noiseLevel = request.getParameter("noiseLevel");
        String radiatorSize = request.getParameter("radiatorSize");
        String coolerHeight = request.getParameter("coolerHeight");

        radiatorSize = (radiatorSize == null || radiatorSize.isEmpty()) ?  "null" : radiatorSize;
        coolerHeight = (coolerHeight == null || coolerHeight.isEmpty()) ? "null" : coolerHeight;

        if (
                name!=null && !name.isEmpty()
                && rating!=null && !rating.isEmpty()
                && price!=null && !price.isEmpty()
                && shop_URL!=null && !shop_URL.isEmpty()
                && image_URL!=null && !image_URL.isEmpty()
                && tdp!=null && !tdp.isEmpty()
                && socket!=null && !socket.isEmpty()
                && rpm!=null && !rpm.isEmpty()
                && noiseLevel!=null && !noiseLevel.isEmpty()
                && radiatorSize!=null && !radiatorSize.isEmpty()
                && coolerHeight!=null && !coolerHeight.isEmpty()
        ) {
            List<Cooler> coolers = coolerDAO.doRetrieveByName(name);
            if (coolers.isEmpty()) {
                Cooler cooler = new Cooler();
                cooler.setName(name);
                cooler.setRating(Double.parseDouble(rating));
                cooler.setPrice(Double.parseDouble(price));
                cooler.setShop_URL(shop_URL);
                cooler.setImage_URL(image_URL);
                cooler.setTdp(Integer.parseInt(tdp));
                cooler.setSocket(socket);
                cooler.setRpm(Integer.parseInt(rpm));
                cooler.setNoise_level(Integer.parseInt(noiseLevel));
                if (radiatorSize.equals("null")) cooler.setRadiator_size(null);
                else cooler.setRadiator_size(Integer.parseInt(radiatorSize));
                if (coolerHeight.equals("null")) cooler.setCooler_height(null);
                else cooler.setCooler_height(Integer.parseInt(coolerHeight));

                int id = coolerDAO.doSave(cooler);

                GestireCooler gestireCooler = new GestireCooler();
                GestireCoolerDAO gestireCoolerDAO = new GestireCoolerDAO();
                Administrator administrator = (Administrator) session.getAttribute("administrator");
                gestireCooler.setIdCooler(id);
                gestireCooler.setIdAdmin(administrator.getId());
                gestireCoolerDAO.doSave(gestireCooler);

                response.sendRedirect("coolers");
            } else {
                List<String> sockets = coolerDAO.doRetrieveDistinctSockets();
                List<String> radiatorSizes = coolerDAO.doRetrieveDistinctRadiatorSizes();
                request.setAttribute("sockets", sockets);
                request.setAttribute("radiatorSizes", radiatorSizes);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addCooler.jsp?alreadyExists=1");
                request.setAttribute("alreadyExists", 1);
                rd.forward(request, response);
            }
        } else {
            List<String> sockets = coolerDAO.doRetrieveDistinctSockets();
            List<String> radiatorSizes = coolerDAO.doRetrieveDistinctRadiatorSizes();
            request.setAttribute("sockets", sockets);
            request.setAttribute("radiatorSizes", radiatorSizes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addCooler.jsp?formError=1");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}