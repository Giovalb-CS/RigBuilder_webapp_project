package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddCaseboxServlet", value = "/addCase")
public class AddCaseboxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            CaseboxDAO caseboxDAO = new CaseboxDAO();
            List<String> radiatorSizes = caseboxDAO.doRetrieveDistinctRadiatorSizes();
            List<String> formFactors = caseboxDAO.doRetrieveDistinctFormFactors();
            request.setAttribute("radiatorSizes", radiatorSizes);
            request.setAttribute("formFactors", formFactors);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addCase.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CaseboxDAO caseboxDAO = new CaseboxDAO();

        String name = request.getParameter("name");
        String rating  = request.getParameter("rating");
        String price = request.getParameter("price");
        String shop_URL = request.getParameter("shop_URL");
        String image_URL = request.getParameter("image_URL");
        String maxCoolerHeight = request.getParameter("maxCoolerHeight");
        String radiatorSize = request.getParameter("radiatorSize");
        String gpuLenght = request.getParameter("gpuLenght");
        String formFactor = request.getParameter("formFactor");
        String psuLenght = request.getParameter("psuLenght");
        String pcieSlots = request.getParameter("pcieSlots");

        if (
                name!=null && !name.isEmpty()
                && rating!=null && !rating.isEmpty()
                && price!=null && !price.isEmpty()
                && shop_URL!=null && !shop_URL.isEmpty()
                && image_URL!=null && !image_URL.isEmpty()
                && maxCoolerHeight!=null && !maxCoolerHeight.isEmpty()
                && radiatorSize!=null && !radiatorSize.isEmpty()
                && gpuLenght!=null && !gpuLenght.isEmpty()
                && formFactor!=null && !formFactor.isEmpty()
                && psuLenght!=null && !psuLenght.isEmpty()
                && pcieSlots!=null && !pcieSlots.isEmpty()
        ) {
           List<Casebox> caseboxes = caseboxDAO.doRetrieveByName(name);
           if (caseboxes.isEmpty()) {
               Casebox casebox = new Casebox();
               casebox.setName(name);
               casebox.setRating(Double.parseDouble(rating));
               casebox.setPrice(Double.parseDouble(price));
               casebox.setShop_URL(shop_URL);
               casebox.setImage_URL(image_URL);
               casebox.setMax_cooler_height(Integer.parseInt(maxCoolerHeight));
               casebox.setRadiator_size(Integer.parseInt(radiatorSize));
               casebox.setGpu_lenght(Integer.parseInt(gpuLenght));
               casebox.setForm_factor(formFactor);
               casebox.setPsu_lenght(Integer.parseInt(psuLenght));
               casebox.setPcie_slots(Integer.parseInt(pcieSlots));

               int id = caseboxDAO.doSave(casebox);

               GestireCasebox gestireCasebox = new GestireCasebox();
               GestireCaseboxDAO gestireCaseboxDAO = new GestireCaseboxDAO();
               Administrator administrator = (Administrator) session.getAttribute("administrator");
               gestireCasebox.setIdCasebox(id);
               gestireCasebox.setIdAdmin(administrator.getId());
               gestireCaseboxDAO.doSave(gestireCasebox);

               response.sendRedirect("cases");
           } else {
               List<String> radiatorSizes = caseboxDAO.doRetrieveDistinctRadiatorSizes();
               List<String> formFactors = caseboxDAO.doRetrieveDistinctFormFactors();
               request.setAttribute("radiatorSizes", radiatorSizes);
               request.setAttribute("formFactors", formFactors);
               RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addCase.jsp?alreadyExists=1");
               request.setAttribute("alreadyExists", 1);
               rd.forward(request, response);
           }
        } else {
            List<String> radiatorSizes = caseboxDAO.doRetrieveDistinctRadiatorSizes();
            List<String> formFactors = caseboxDAO.doRetrieveDistinctFormFactors();
            request.setAttribute("radiatorSizes", radiatorSizes);
            request.setAttribute("formFactors", formFactors);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/addCase.jsp?formError=1");
            request.setAttribute("formError", 1);
            rd.forward(request, response);
        }
    }
}