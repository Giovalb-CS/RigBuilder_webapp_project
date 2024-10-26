package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Casebox;
import model.CaseboxDAO;
import model.Processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisualizeCases", value = "/cases")
public class VisualizeCases extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CaseboxDAO caseboxDAO = new CaseboxDAO();

        List<String> radiatorSizes = caseboxDAO.doRetrieveDistinctRadiatorSizes();
        List<String> formFactors = caseboxDAO.doRetrieveDistinctFormFactors();
        request.setAttribute("radiatorSizes", radiatorSizes);
        request.setAttribute("formFactors", formFactors);

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String ratingSort = request.getParameter("ratingSort");
        String priceSort = request.getParameter("priceSort");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String minCoolerHeight = request.getParameter("minCoolerHeight");
        String minRadiatorSize = request.getParameter("minRadiatorSize");
        String minGPULength = request.getParameter("minGPULength");
        String formFactor = request.getParameter("formFactor");
        String minPSULength = request.getParameter("minPSULength");
        String minPCIeSlots = request.getParameter("minPCIeSlots");

        String compositeMinPrice = request.getParameter("compositeMinPrice");
        String compositeMaxPrice = request.getParameter("compositeMaxPrice");
        String compositeMinRating = request.getParameter("compositeMinRating");
        String compositeMaxRating = request.getParameter("compositeMaxRating");
        String compositeminCoolerHeight = request.getParameter("compositeminCoolerHeight");
        String compositeminRadiatorSize = request.getParameter("compositeminRadiatorSize");
        String compositeminGPULength = request.getParameter("compositeminGPULength");
        String compositeFormFactor = request.getParameter("compositeFormFactor");
        String compositeminPSULength = request.getParameter("compositeminPSULength");
        String compositeMinPCIeSlots = request.getParameter("compositeMinPCIeSlots");

        List<Casebox> cases = new ArrayList<>();

        if (id != null && !id.isEmpty()) {
            Casebox casebox = caseboxDAO.doRetrieveByID(Integer.parseInt(id));
            if ((casebox != null)) {
                cases.add(casebox);
            }
        } else if (name != null && !name.isEmpty()) {
            cases = caseboxDAO.doRetrieveByName(name);
        } else if (ratingSort != null) {
            cases = ratingSort.equals("asc")
                    ? caseboxDAO.doRetrieveAllByRatingAsc()
                    : caseboxDAO.doRetrieveAllByRatingDesc();
        } else if (priceSort != null) {
            cases = priceSort.equals("asc")
                    ? caseboxDAO.doRetrieveAllByPriceAsc()
                    : caseboxDAO.doRetrieveAllByPriceDesc();
        } else if (minPrice != null && maxPrice != null) {
            cases = caseboxDAO.doRetrieveAllByPriceBetween(
                    Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
        } else if (minCoolerHeight != null && !minCoolerHeight.isEmpty()) {
            cases = caseboxDAO.doRetrieveAllByCoolerHeight(Integer.parseInt(minCoolerHeight));
        } else if (minRadiatorSize != null && !minRadiatorSize.isEmpty()) {
            cases = caseboxDAO.doRetrieveAllByRadiatorSize(Integer.parseInt(minRadiatorSize));
        } else if (minGPULength != null && !minGPULength.isEmpty()) {
            cases = caseboxDAO.doRetrieveAllByGPULenght(Integer.parseInt(minGPULength));
        } else if (formFactor != null && !formFactor.isEmpty()) {
            cases = caseboxDAO.doRetrieveAllByFormFactor(formFactor);
        } else if (minPSULength != null && !minPSULength.isEmpty()) {
            cases = caseboxDAO.doRetrieveAllByPSULenght(Integer.parseInt(minPSULength));
        } else if (minPCIeSlots != null && !minPCIeSlots.isEmpty()) {
            cases = caseboxDAO.doRetrieveAllByPCIeSlots(Integer.parseInt(minPCIeSlots));
        } else {
            cases = caseboxDAO.doRetrieveAll();
        }

        if (
                compositeMinPrice != null || compositeMaxPrice != null ||
                (compositeminCoolerHeight != null && !compositeminCoolerHeight.isEmpty()) ||
                (compositeminRadiatorSize != null && !compositeminRadiatorSize.isEmpty()) ||
                (compositeminGPULength != null && !compositeminGPULength.isEmpty()) ||
                (compositeFormFactor != null && !compositeFormFactor.isEmpty()) ||
                (compositeminPSULength != null && !compositeminPSULength.isEmpty()) ||
                (compositeMinPCIeSlots != null && !compositeMinPCIeSlots.isEmpty()) ||
                compositeMinRating != null || compositeMaxRating != null
        ) {
            Double minPriceVal = (compositeMinPrice != null && !compositeMinPrice.isEmpty())
                    ? Double.parseDouble(compositeMinPrice)
                    : null;

            Double maxPriceVal = (compositeMaxPrice != null && !compositeMaxPrice.isEmpty())
                    ? Double.parseDouble(compositeMaxPrice)
                    : null;

            Double minRatingVal = (compositeMinRating != null && !compositeMinRating.isEmpty())
                    ? Double.parseDouble(compositeMinRating)
                    : null;

            Double maxRatingVal = (compositeMaxRating != null && !compositeMaxRating.isEmpty())
                    ? Double.parseDouble(compositeMaxRating)
                    : null;

            Integer minCoolerHeightVal = (compositeminCoolerHeight != null && !compositeminCoolerHeight.isEmpty())
                    ? Integer.parseInt(compositeminCoolerHeight)
                    : null;

            Integer minRadiatorSizeVal = (compositeminRadiatorSize != null && !compositeminRadiatorSize.isEmpty())
                    ? Integer.parseInt(compositeminRadiatorSize)
                    : null;

            Integer minGPULengthVal = (compositeminGPULength != null && !compositeminGPULength.isEmpty())
                    ? Integer.parseInt(compositeminGPULength)
                    : null;

            Integer minPSULengthVal = (compositeminPSULength != null && !compositeminPSULength.isEmpty())
                    ? Integer.parseInt(compositeminPSULength)
                    : null;

            Integer minPCIeSlotsVal = (compositeMinPCIeSlots != null && !compositeMinPCIeSlots.isEmpty())
                    ? Integer.parseInt(compositeMinPCIeSlots)
                    : null;

            cases = caseboxDAO.doRetrieveFiltered(
                    minRatingVal,
                    maxRatingVal,
                    minPriceVal,
                    maxPriceVal,
                    minCoolerHeightVal,
                    minRadiatorSizeVal,
                    minGPULengthVal,
                    compositeFormFactor,
                    minPSULengthVal,
                    minPCIeSlotsVal
            );
        }

        request.setAttribute("caseboxes", cases);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/cases.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") != null) {
            CaseboxDAO caseboxDAO = new CaseboxDAO();
            ArrayList<Casebox> caseboxes = (ArrayList<Casebox>) caseboxDAO.doRetrieveAll();
            request.setAttribute("caseboxes", caseboxes);
            List<String> radiatorSizes = caseboxDAO.doRetrieveDistinctRadiatorSizes();
            List<String> formFactors = caseboxDAO.doRetrieveDistinctFormFactors();
            request.setAttribute("radiatorSizes", radiatorSizes);
            request.setAttribute("formFactors", formFactors);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/results/cases.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp?notLoggedIn=1");
        }
    }
}