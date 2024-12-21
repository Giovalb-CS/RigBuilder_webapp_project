package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Motherboard;
import model.MotherboardDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditMotherboardServlet", value = "/editMotherboard")
public class EditMotherboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            MotherboardDAO motherboardDAO = new MotherboardDAO();
            List<String> sockets = motherboardDAO.doRetrieveDistinctSockets();
            List<String> chipsets = motherboardDAO.doRetrieveDistinctChipsets();
            List<String> ramTypes = motherboardDAO.doRetrieveDistinctRamTypes();
            List<String> formFactors = motherboardDAO.doRetrieveDistinctFormFactors();
            List<String> lanTypes = motherboardDAO.doRetrieveDistinctLanTypes();
            List<String> wifiTypes = motherboardDAO.doRetrieveDistinctWifiTypes();
            Motherboard motherboard = motherboardDAO.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("motherboard", motherboard);
            request.setAttribute("sockets", sockets);
            request.setAttribute("chipsets", chipsets);
            request.setAttribute("ramTypes", ramTypes);
            request.setAttribute("formFactors", formFactors);
            request.setAttribute("lanTypes", lanTypes);
            request.setAttribute("wifiTypes", wifiTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editMotherboard.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MotherboardDAO motherboardDAO = new MotherboardDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Motherboard motherboard_before = motherboardDAO.doRetrieveByID(id);
        Motherboard motherboard_after = new Motherboard();

        String name = request.getParameter("name");
        String rating  = request.getParameter("rating");
        String price = request.getParameter("price");
        String shop_URL = request.getParameter("shop_URL");
        String image_URL = request.getParameter("image_URL");
        String tdp = request.getParameter("tdp");
        String socket = request.getParameter("socket");
        String chipset = request.getParameter("chipset");
        String ramType = request.getParameter("ramType");
        String ramMaxSpeed = request.getParameter("ramMaxSpeed");
        String ramSlots = request.getParameter("ramSlots");
        String ramMaxCapacity = request.getParameter("ramMaxCapacity");
        String pciex16slots = request.getParameter("pciex16slots");
        String pciex1slots = request.getParameter("pciex1slots");
        String m2slots = request.getParameter("m2slots");
        String sataslots = request.getParameter("sataslots");
        String lanType = request.getParameter("lanType");
        String wifiType = request.getParameter("wifiType");
        String formFactor = request.getParameter("formFactor");

        if (
                name!=null && !name.isEmpty()
                        && rating!=null && !rating.isEmpty()
                        && price!=null && !price.isEmpty()
                        && shop_URL!=null && !shop_URL.isEmpty()
                        && image_URL!=null && !image_URL.isEmpty()
                        && tdp!=null && !tdp.isEmpty()
                        && socket!=null && !socket.isEmpty()
                        && chipset!=null && !chipset.isEmpty()
                        && ramType!=null && !ramType.isEmpty()
                        && ramMaxSpeed!=null && !ramMaxSpeed.isEmpty()
                        && ramSlots!=null && !ramSlots.isEmpty()
                        && ramMaxCapacity!=null && !ramMaxCapacity.isEmpty()
                        && pciex16slots!=null && !pciex16slots.isEmpty()
                        && pciex1slots!=null && !pciex1slots.isEmpty()
                        && m2slots!=null && !m2slots.isEmpty()
                        && sataslots!=null && !sataslots.isEmpty()
                        && lanType!=null && !lanType.isEmpty()
                        && wifiType!=null && !wifiType.isEmpty()
                        && formFactor!=null && !formFactor.isEmpty()
        ) {
            motherboard_after.setId(id);
            motherboard_after.setName(name);
            motherboard_after.setRating(Double.parseDouble(rating));
            motherboard_after.setPrice(Double.parseDouble(price));
            motherboard_after.setShop_URL(shop_URL);
            motherboard_after.setImage_URL(image_URL);
            motherboard_after.setTdp(Integer.parseInt(tdp));
            motherboard_after.setSocket(socket);
            motherboard_after.setChipset(chipset);
            motherboard_after.setRam_type(ramType);
            motherboard_after.setRam_max_speed(Integer.parseInt(ramMaxSpeed));
            motherboard_after.setRam_slot(Integer.parseInt(ramSlots));
            motherboard_after.setRam_max(Integer.parseInt(ramMaxCapacity));
            motherboard_after.setPcie_x16_slot(Integer.parseInt(pciex16slots));
            motherboard_after.setPcie_x1_slot(Integer.parseInt(pciex1slots));
            motherboard_after.setM2_slot(Integer.parseInt(m2slots));
            motherboard_after.setSata_slot(Integer.parseInt(sataslots));
            motherboard_after.setLan(lanType);
            motherboard_after.setWifi(wifiType);
            motherboard_after.setForm_factor(formFactor);

            motherboardDAO.doModify(motherboard_after);

            response.sendRedirect("motherboards");
        } else {
            List<String> sockets = motherboardDAO.doRetrieveDistinctSockets();
            List<String> chipsets = motherboardDAO.doRetrieveDistinctChipsets();
            List<String> ramTypes = motherboardDAO.doRetrieveDistinctRamTypes();
            List<String> formFactors = motherboardDAO.doRetrieveDistinctFormFactors();
            List<String> lanTypes = motherboardDAO.doRetrieveDistinctLanTypes();
            List<String> wifiTypes = motherboardDAO.doRetrieveDistinctWifiTypes();
            Motherboard motherboard = motherboardDAO.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("motherboard", motherboard);
            request.setAttribute("sockets", sockets);
            request.setAttribute("chipsets", chipsets);
            request.setAttribute("ramTypes", ramTypes);
            request.setAttribute("formFactors", formFactors);
            request.setAttribute("lanTypes", lanTypes);
            request.setAttribute("wifiTypes", wifiTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editMotherboard.jsp");
            rd.forward(request, response);
        }
    }
}