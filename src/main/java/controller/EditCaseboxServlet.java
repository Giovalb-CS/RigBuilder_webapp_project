package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Casebox;
import model.CaseboxDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCaseboxServlet", value = "/editCase")
public class EditCaseboxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            CaseboxDAO caseboxDAO = new CaseboxDAO();
            List<String> radiatorSizes = caseboxDAO.doRetrieveDistinctRadiatorSizes();
            List<String> formFactors = caseboxDAO.doRetrieveDistinctFormFactors();
            Casebox casebox = caseboxDAO.doRetrieveByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("casebox", casebox);
            request.setAttribute("radiatorSizes", radiatorSizes);
            request.setAttribute("formFactors", formFactors);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/editCase.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CaseboxDAO caseboxDAO = new CaseboxDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Casebox casebox_after = new Casebox();

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
            casebox_after.setId(id);
            casebox_after.setName(name);
            casebox_after.setRating(Double.parseDouble(rating));
            casebox_after.setPrice(Double.parseDouble(price));
            casebox_after.setShop_URL(shop_URL);
            casebox_after.setImage_URL(image_URL);
            casebox_after.setMax_cooler_height(Integer.parseInt(maxCoolerHeight));
            casebox_after.setRadiator_size(Integer.parseInt(radiatorSize));
            casebox_after.setGpu_lenght(Integer.parseInt(gpuLenght));
            casebox_after.setForm_factor(formFactor);
            casebox_after.setPsu_lenght(Integer.parseInt(psuLenght));
            casebox_after.setPcie_slots(Integer.parseInt(pcieSlots));

            caseboxDAO.doModify(casebox_after);

            response.sendRedirect("cases");
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