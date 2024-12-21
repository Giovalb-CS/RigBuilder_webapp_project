package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Cooler;
import model.CoolerDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCoolerServlet", value = "/editCooler")
public class EditCoolerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            CoolerDAO coolerDAO = new CoolerDAO();
            List<String> sockets = coolerDAO.doRetrieveDistinctSockets();
            List<String> radiatorSizes = coolerDAO.doRetrieveDistinctRadiatorSizes();
            Cooler cooler = coolerDAO.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("cooler", cooler);
            request.setAttribute("sockets", sockets);
            request.setAttribute("radiatorSizes", radiatorSizes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editCooler.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CoolerDAO coolerDAO = new CoolerDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Cooler cooler_before = coolerDAO.doRetrieveByID(id);
        Cooler cooler_after = new Cooler();

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
            cooler_after.setId(id);
            cooler_after.setName(name);
            cooler_after.setRating(Double.parseDouble(rating));
            cooler_after.setPrice(Double.parseDouble(price));
            cooler_after.setShop_URL(shop_URL);
            cooler_after.setImage_URL(image_URL);
            cooler_after.setTdp(Integer.parseInt(tdp));
            cooler_after.setSocket(socket);
            cooler_after.setRpm(Integer.parseInt(rpm));
            cooler_after.setNoise_level(Integer.parseInt(noiseLevel));
            if (radiatorSize.equals("null")) cooler_after.setRadiator_size(null);
            else cooler_after.setRadiator_size(Integer.parseInt(radiatorSize));
            if (coolerHeight.equals("null")) cooler_after.setCooler_height(null);
            else cooler_after.setCooler_height(Integer.parseInt(coolerHeight));

            coolerDAO.doModify(cooler_after);

            response.sendRedirect("coolers");
        } else {
            List<String> sockets = coolerDAO.doRetrieveDistinctSockets();
            List<String> radiatorSizes = coolerDAO.doRetrieveDistinctRadiatorSizes();
            Cooler cooler = coolerDAO.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("cooler", cooler);
            request.setAttribute("sockets", sockets);
            request.setAttribute("radiatorSizes", radiatorSizes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editCooler.jsp?formError=1");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}