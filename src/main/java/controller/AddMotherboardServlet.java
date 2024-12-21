package controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddMotherboardServlet", value = "/addMotherboard")
public class AddMotherboardServlet extends HttpServlet {
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
            request.setAttribute("sockets", sockets);
            request.setAttribute("chipsets", chipsets);
            request.setAttribute("ramTypes", ramTypes);
            request.setAttribute("formFactors", formFactors);
            request.setAttribute("lanTypes", lanTypes);
            request.setAttribute("wifiTypes", wifiTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addMotherboard.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MotherboardDAO motherboardDAO = new MotherboardDAO();

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
            List<Motherboard> motherboards = motherboardDAO.doRetrieveByName(name);
            if (motherboards.isEmpty()) {
                Motherboard motherboard = new Motherboard();
                motherboard.setName(name);
                motherboard.setRating(Double.parseDouble(rating));
                motherboard.setPrice(Double.parseDouble(price));
                motherboard.setShop_URL(shop_URL);
                motherboard.setImage_URL(image_URL);
                motherboard.setTdp(Integer.parseInt(tdp));
                motherboard.setSocket(socket);
                motherboard.setChipset(chipset);
                motherboard.setRam_type(ramType);
                motherboard.setRam_max_speed(Integer.parseInt(ramMaxSpeed));
                motherboard.setRam_slot(Integer.parseInt(ramSlots));
                motherboard.setRam_max(Integer.parseInt(ramMaxCapacity));
                motherboard.setPcie_x16_slot(Integer.parseInt(pciex16slots));
                motherboard.setPcie_x1_slot(Integer.parseInt(pciex1slots));
                motherboard.setM2_slot(Integer.parseInt(m2slots));
                motherboard.setSata_slot(Integer.parseInt(sataslots));
                motherboard.setLan(lanType);
                motherboard.setWifi(wifiType);
                motherboard.setForm_factor(formFactor);

                int id = motherboardDAO.doSave(motherboard);

                GestireMotherboard gestireMotherboard = new GestireMotherboard();
                GestireMotherboardDAO gestireMotherboardDAO = new GestireMotherboardDAO();
                Administrator administrator = (Administrator) session.getAttribute("administrator");
                gestireMotherboard.setIdMotherboard(id);
                gestireMotherboard.setIdAdmin(administrator.getId());
                gestireMotherboardDAO.doSave(gestireMotherboard);

                response.sendRedirect("motherboards");
            } else {
                List<String> sockets = motherboardDAO.doRetrieveDistinctSockets();
                List<String> chipsets = motherboardDAO.doRetrieveDistinctChipsets();
                List<String> ramTypes = motherboardDAO.doRetrieveDistinctRamTypes();
                List<String> formFactors = motherboardDAO.doRetrieveDistinctFormFactors();
                List<String> lanTypes = motherboardDAO.doRetrieveDistinctLanTypes();
                List<String> wifiTypes = motherboardDAO.doRetrieveDistinctWifiTypes();
                request.setAttribute("sockets", sockets);
                request.setAttribute("chipsets", chipsets);
                request.setAttribute("ramTypes", ramTypes);
                request.setAttribute("formFactors", formFactors);
                request.setAttribute("lanTypes", lanTypes);
                request.setAttribute("wifiTypes", wifiTypes);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addMotherboard.jsp?alreadyExists=1");
                request.setAttribute("alreadyExists", 1);
                rd.forward(request, response);
            }
        } else {
            List<String> sockets = motherboardDAO.doRetrieveDistinctSockets();
            List<String> chipsets = motherboardDAO.doRetrieveDistinctChipsets();
            List<String> ramTypes = motherboardDAO.doRetrieveDistinctRamTypes();
            List<String> formFactors = motherboardDAO.doRetrieveDistinctFormFactors();
            List<String> lanTypes = motherboardDAO.doRetrieveDistinctLanTypes();
            List<String> wifiTypes = motherboardDAO.doRetrieveDistinctWifiTypes();
            request.setAttribute("sockets", sockets);
            request.setAttribute("chipsets", chipsets);
            request.setAttribute("ramTypes", ramTypes);
            request.setAttribute("formFactors", formFactors);
            request.setAttribute("lanTypes", lanTypes);
            request.setAttribute("wifiTypes", wifiTypes);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addMotherboard.jsp?formError=1");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}