package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "API_AutoBuild", value = "/api/autobuild/*")
public class API_AutoBuild extends HttpServlet {

    private static class AutoBuildParams {
        Double price;
        String cpuBrand;

        @Override
        public String toString() {
            return "AutoBuildParams{" +
                    "price=" + price +
                    ", cpuBrand='" + cpuBrand + '\'' +
                    '}';
        }
    }

    public static class DataWrapper {
        Processor processor;
        GPU gpu;
        RAM ram;
        Motherboard motherboard;
        SSD ssd;
        Cooler cooler;
        PSU psu;
        Casebox casebox;

        public DataWrapper(Processor processor, GPU gpu, RAM ram, Motherboard motherboard, SSD ssd, Cooler cooler, PSU psu, Casebox casebox) {
            this.processor = processor;
            this.gpu = gpu;
            this.ram = ram;
            this.motherboard = motherboard;
            this.ssd = ssd;
            this.cooler = cooler;
            this.psu = psu;
            this.casebox = casebox;
        }

        public Processor getProcessor() {
            return processor;
        }

        public void setProcessor(Processor processor) {
            this.processor = processor;
        }

        public GPU getGpu() {
            return gpu;
        }

        public void setGpu(GPU gpu) {
            this.gpu = gpu;
        }

        public RAM getRam() {
            return ram;
        }

        public void setRam(RAM ram) {
            this.ram = ram;
        }

        public Motherboard getMotherboard() {
            return motherboard;
        }

        public void setMotherboard(Motherboard motherboard) {
            this.motherboard = motherboard;
        }

        public SSD getSsd() {
            return ssd;
        }

        public void setSsd(SSD ssd) {
            this.ssd = ssd;
        }

        public Cooler getCooler() {
            return cooler;
        }

        public void setCooler(Cooler cooler) {
            this.cooler = cooler;
        }

        public PSU getPsu() {
            return psu;
        }

        public void setPsu(PSU psu) {
            this.psu = psu;
        }

        public Casebox getCasebox() {
            return casebox;
        }

        public void setCasebox(Casebox casebox) {
            this.casebox = casebox;
        }
    }

    private final ProcessorDAO processorDao = new ProcessorDAO();
    private final GPUDAO gpuDao = new GPUDAO();
    private final RAMDAO ramDao = new RAMDAO();
    private final MotherboardDAO motherboardDao = new MotherboardDAO();
    private final SSDDAO ssdDao = new SSDDAO();
    private final CoolerDAO coolerDao = new CoolerDAO();
    private final PSUDAO psuDao = new PSUDAO();
    private final CaseboxDAO caseboxDao = new CaseboxDAO();

    private double cpuBudget;
    private double gpuBudget;
    private double ramBudget;
    private double motherboardBudget;
    private double ssdBudget;
    private double coolerBudget;
    private double psuBudget;
    private double caseboxBudget;

    private List<Processor> processors;
    private List<GPU> gpus;
    private List<RAM> rams;
    private List<Motherboard> motherboards;
    private List<SSD> ssds;
    private List<Cooler> coolers;
    private List<PSU> psus;
    private List<Casebox> caseboxes;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientApiKey = request.getHeader("X-API-KEY");
        if (clientApiKey == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing API Key");
            return;
        }
        if (!ApiKeyValidator.isApiKeyValid(clientApiKey)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "API Key invalid.");
            return;
        }

        BufferedReader reader = request.getReader();
        StringBuilder jsonBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        AutoBuildParams params = gson.fromJson(jsonBody.toString(), AutoBuildParams.class);
        System.out.println(params.toString());

        cpuBudget = params.price * 0.22;
        gpuBudget = params.price * 0.20;
        ramBudget = params.price * 0.12;
        motherboardBudget = params.price * 0.14;
        ssdBudget = params.price * 0.15;
        coolerBudget = params.price * 0.05;
        psuBudget = params.price * 0.06;
        caseboxBudget = params.price * 0.06;
        System.out.println("Start\n" + String.format(
                "Budgets: CPU = %.2f, GPU = %.2f, RAM = %.2f, Motherboard = %.2f, SSD = %.2f, Cooler = %.2f, PSU = %.2f, Case = %.2f",
                cpuBudget, gpuBudget, ramBudget, motherboardBudget, ssdBudget, coolerBudget, psuBudget, caseboxBudget
        ));

        processors = processorDao.doRetrieveAllByPriceBetween(1, cpuBudget);
        gpus = gpuDao.doRetrieveAllByPriceBetween(1, gpuBudget);
        rams = ramDao.doRetrieveAllByPriceBetween(1, ramBudget);
        motherboards = motherboardDao.doRetrieveAllByPriceBetween(1, motherboardBudget);
        ssds = ssdDao.doRetrieveAllByPriceBetween(1, ssdBudget);
        coolers = coolerDao.doRetrieveAllByPriceBetween(1, coolerBudget);
        psus = psuDao.doRetrieveAllByPriceBetween(1, psuBudget);
        caseboxes = caseboxDao.doRetrieveAllByPriceBetween(1, caseboxBudget);

        // troviamo il best processor
        Processor bestProcessor = processors.stream()
                .filter(p -> p.getRam_type().equalsIgnoreCase("DDR5") || p.getRam_type().equalsIgnoreCase("DDR4")) // RAM compatibile
                .filter(p -> p.getCore() >= 4 && p.getClock_base() >= 2.5) // Almeno 4 core e 2.5 GHz base
                .filter(p -> params.cpuBrand == null || p.getName().toLowerCase().contains(params.cpuBrand.toLowerCase())) // Filtro per il brand
                .max(Comparator.comparingDouble(p -> {
                    int ramPriority = p.getRam_type().equalsIgnoreCase("DDR5") ? 10 : 5;
                    return (p.getCore() * 2) + p.getClock_base() + ramPriority - (p.getPrice() / 100);
                }))
                .orElse(null);

        if (bestProcessor == null) {
            System.out.println("No processor found within the budget.");
            response.sendError(422, "No processor found within the budget.");
            return;
        }
        System.out.println("Selected Processor: " + bestProcessor);

        double diffCPU = cpuBudget - bestProcessor.getPrice();

        // ridistribuzione diff
        gpuBudget += diffCPU * 0.04;
        ramBudget += diffCPU * 0.05;
        motherboardBudget += diffCPU * 0.5;
        ssdBudget += diffCPU * 0.16;
        coolerBudget += diffCPU * 0.10;
        psuBudget += diffCPU * 0.30;
        caseboxBudget += diffCPU * 0.30;
        System.out.println("\nCPU diff added\n" + String.format(
                "Budgets: CPU = %.2f, GPU = %.2f, RAM = %.2f, Motherboard = %.2f, SSD = %.2f, Cooler = %.2f, PSU = %.2f, Case = %.2f",
                cpuBudget, gpuBudget, ramBudget, motherboardBudget, ssdBudget, coolerBudget, psuBudget, caseboxBudget
        ));

        gpus = gpuDao.doRetrieveAllByPriceBetween(1, gpuBudget);
        rams = ramDao.doRetrieveAllByPriceBetween(1, ramBudget);
        motherboards = motherboardDao.doRetrieveAllByPriceBetween(1, motherboardBudget);
        ssds = ssdDao.doRetrieveAllByPriceBetween(1, ssdBudget);
        coolers = coolerDao.doRetrieveAllByPriceBetween(1, coolerBudget);
        psus = psuDao.doRetrieveAllByPriceBetween(1, psuBudget);
        caseboxes = caseboxDao.doRetrieveAllByPriceBetween(1, caseboxBudget);

        // troviamo la best gpu
        GPU bestGPU = gpus.stream()
                .filter(g -> g.getMemory() != null && !g.getMemory().isEmpty())
                .max(Comparator.comparingDouble(g -> {
                    // punteggio: più alta è la memoria e il clock, più basso è il TDP, meglio è
                    double memoryClock = g.getMemory_clock(); // Memory Clock
                    double coreClock = g.getCore_clock(); // Core Clock
                    double tdp = g.getTdp(); // TDP

                    // calcola il punteggio tenendo conto della memoria, core clock e tdp
                    return (memoryClock * 0.4) + (coreClock * 0.4) - (tdp * 0.2);
                }))
                .orElse(null);

        if (bestGPU == null) {
            System.out.println("No GPU found within the budget.");
            response.sendError(422, "No GPU found within the budget.");
            return;
        }
        System.out.println("Selected GPU: " + bestGPU);

        double diffGPU = gpuBudget - bestGPU.getPrice();
        ramBudget += diffGPU * 0.09;
        motherboardBudget += diffGPU * 0.15;
        ssdBudget += diffGPU * 0.16;
        coolerBudget += diffGPU * 0.10;
        psuBudget += diffGPU * 0.25;
        caseboxBudget += diffGPU * 0.25;
        System.out.println("\nGPU diff added\n" + String.format(
                "Budgets: CPU = %.2f, GPU = %.2f, RAM = %.2f, Motherboard = %.2f, SSD = %.2f, Cooler = %.2f, PSU = %.2f, Case = %.2f",
                cpuBudget, gpuBudget, ramBudget, motherboardBudget, ssdBudget, coolerBudget, psuBudget, caseboxBudget
        ));
        rams = ramDao.doRetrieveAllByPriceBetween(1, ramBudget);
        motherboards = motherboardDao.doRetrieveAllByPriceBetween(1, motherboardBudget);
        ssds = ssdDao.doRetrieveAllByPriceBetween(1, ssdBudget);
        coolers = coolerDao.doRetrieveAllByPriceBetween(1, coolerBudget);
        psus = psuDao.doRetrieveAllByPriceBetween(1, psuBudget);
        caseboxes = caseboxDao.doRetrieveAllByPriceBetween(1, caseboxBudget);

        // Troviamo la best RAM
        RAM bestRAM = rams.stream()
                .filter(r -> r.getType() != null && !r.getType().isEmpty())  // Filtro per tipo di RAM non nullo
                .filter(r -> r.getClock() > 0)  // Filtro per RAM con clock valido
                .filter(r -> r.getType().equalsIgnoreCase(bestProcessor.getRam_type()))  // Il tipo di RAM deve essere uguale a quello della CPU
                .max(Comparator.comparingDouble(r -> {
                    // estrazione della quantità di RAM dal nome (ad esempio "2 x 8 GB" diventa 16 GB)
                    String ramName = r.getName();
                    Pattern pattern = Pattern.compile("(\\d+)\\s*x\\s*(\\d+)\\s*GB", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(ramName);
                    int totalRamCapacity = 0;
                    if (matcher.find()) {
                        int dimms = Integer.parseInt(matcher.group(1));  // Numero di DIMM
                        int capacityPerDimm = Integer.parseInt(matcher.group(2));  // Capacità per DIMM
                        totalRamCapacity = dimms * capacityPerDimm;  // Capacità totale
                    }

                    // Punteggio: più grande è la RAM, meglio è
                    double ramCapacityScore = totalRamCapacity;

                    // Punteggio per tipo di RAM: preferiamo DDR5 > DDR4 > DDR3
                    double ramTypeScore = 0;
                    if (r.getType().equalsIgnoreCase("DDR5")) {
                        ramTypeScore = 3;
                    } else if (r.getType().equalsIgnoreCase("DDR4")) {
                        ramTypeScore = 2;
                    } else if (r.getType().equalsIgnoreCase("DDR3")) {
                        ramTypeScore = 1;
                    }

                    // Punteggio per il clock: più alto è, meglio è
                    double ramClockScore = r.getClock();

                    // Calcoliamo il punteggio totale
                    return (ramCapacityScore * 0.5) + (ramTypeScore * 2) + (ramClockScore * 0.5);
                }))
                .orElse(null);

        if (bestRAM == null) {
            System.out.println("No RAM found within the budget.");
            response.sendError(422, "No RAM found within the budget.");
            return;
        }
        System.out.println("Selected RAM: " + bestRAM);

        double diffRAM = ramBudget - bestRAM.getPrice();
        motherboardBudget += diffRAM * 0.10;
        ssdBudget += diffRAM * 0.15;
        coolerBudget += diffRAM * 0.25;
        psuBudget += diffRAM * 0.25;
        caseboxBudget += diffRAM * 0.25;
        System.out.println("\nRAM diff added\n" + String.format(
                "Budgets: CPU = %.2f, GPU = %.2f, RAM = %.2f, Motherboard = %.2f, SSD = %.2f, Cooler = %.2f, PSU = %.2f, Case = %.2f",
                cpuBudget, gpuBudget, ramBudget, motherboardBudget, ssdBudget, coolerBudget, psuBudget, caseboxBudget
        ));
        motherboards = motherboardDao.doRetrieveAllByPriceBetween(1, motherboardBudget);
        ssds = ssdDao.doRetrieveAllByPriceBetween(1, ssdBudget);
        coolers = coolerDao.doRetrieveAllByPriceBetween(1, coolerBudget);
        psus = psuDao.doRetrieveAllByPriceBetween(1, psuBudget);
        caseboxes = caseboxDao.doRetrieveAllByPriceBetween(1, caseboxBudget);

        // Troviamo la best motherboard
        Motherboard bestMotherboard = motherboards.stream()
                .filter(m -> m.getSocket().equalsIgnoreCase(bestProcessor.getSocket()))
                .filter(m -> m.getRam_type().equalsIgnoreCase(bestRAM.getType()))
                .max(Comparator.comparingDouble(m -> {
                    double ramSpeedScore = m.getRam_max_speed();
                    double pcieScore = m.getPcie_x16_slot() * 1.2;  // ogni slot PCIe x16 vale 2 punti
                    double m2SlotScore = m.getM2_slot() * 2.3;  // ogni slot M.2 vale 1.5 punti
                    return ramSpeedScore + pcieScore + m2SlotScore;
                }))
                .orElse(null);

        if (bestMotherboard == null) {
            System.out.println("No motherboard found within the budget.");
            response.sendError(422, "No motherboard found within the budget.");
            return;
        }
        System.out.println("Selected Motherboard: " + bestMotherboard);

        double diffMOBO = motherboardBudget - bestMotherboard.getPrice();
        ssdBudget += diffMOBO * 0.25;
        coolerBudget += diffMOBO * 0.15;
        psuBudget += diffMOBO * 0.20;
        caseboxBudget += diffMOBO * 0.40;
        System.out.println("\nMOBO diff added\n" + String.format(
                "Budgets: CPU = %.2f, GPU = %.2f, RAM = %.2f, Motherboard = %.2f, SSD = %.2f, Cooler = %.2f, PSU = %.2f, Case = %.2f",
                cpuBudget, gpuBudget, ramBudget, motherboardBudget, ssdBudget, coolerBudget, psuBudget, caseboxBudget
        ));
        ssds = ssdDao.doRetrieveAllByPriceBetween(1, ssdBudget);
        coolers = coolerDao.doRetrieveAllByPriceBetween(1, coolerBudget);
        psus = psuDao.doRetrieveAllByPriceBetween(1, psuBudget);
        caseboxes = caseboxDao.doRetrieveAllByPriceBetween(1, caseboxBudget);

        // Troviamo il best SSD
        SSD bestSSD = ssds.stream()
                .filter(s -> s.getCapacity() != null && !s.getCapacity().isEmpty())  // Filtro per capacità non nullo
                .max(Comparator.comparingDouble(s -> {
                    String capacityString = s.getCapacity();
                    double capacity = 0;
                    if (capacityString.toUpperCase().contains("TB")) {
                        capacity = Double.parseDouble(capacityString.replace("TB", "").trim()) * 1024; // Convertiamo in GB
                    } else if (capacityString.toUpperCase().contains("GB")) {
                        capacity = Double.parseDouble(capacityString.replace("GB", "").trim());
                    }
                    double capacityScore = capacity;
                    double speedReadScore = s.getSpeed_read();
                    double speedWriteScore = s.getSpeed_write();

                    return (capacityScore * 0.8) + (speedReadScore * 0.1) + (speedWriteScore * 0.1);
                }))
                .orElse(null);

        if (bestSSD == null) {
            System.out.println("No SSD found within the budget.");
            response.sendError(422, "No SSD found within the budget.");
            return;
        }
        System.out.println("Selected SSD: " + bestSSD);

        double diffSSD = ssdBudget - bestSSD.getPrice();
        coolerBudget += diffSSD * 0.15;
        psuBudget += diffSSD * 0.45;
        caseboxBudget += diffSSD * 0.40;
        System.out.println("\nSSD diff added\n" + String.format(
                "Budgets: CPU = %.2f, GPU = %.2f, RAM = %.2f, Motherboard = %.2f, SSD = %.2f, Cooler = %.2f, PSU = %.2f, Case = %.2f",
                cpuBudget, gpuBudget, ramBudget, motherboardBudget, ssdBudget, coolerBudget, psuBudget, caseboxBudget
        ));
        coolers = coolerDao.doRetrieveAllByPriceBetween(1, coolerBudget);
        psus = psuDao.doRetrieveAllByPriceBetween(1, psuBudget);
        caseboxes = caseboxDao.doRetrieveAllByPriceBetween(1, caseboxBudget);

        // Troviamo il best cooler
        Cooler bestCooler = coolers.stream()
                .filter(c -> c.getSocket() != null &&
                        Arrays.stream(c.getSocket().split("/")) // Dividi la stringa delle socket
                                .anyMatch(socket -> socket.trim().equalsIgnoreCase(bestProcessor.getSocket()))) // Controlla se una delle socket corrisponde
                .min(Comparator.comparingDouble(Cooler::getPrice)) // Selezioniamo il cooler con il prezzo più basso
                .orElse(null);

        if (bestCooler == null) {
            System.out.println("No cooler found within the budget.");
            response.sendError(422, "No cooler found within the budget.");
            return;
        }
        System.out.println("Selected Cooler: " + bestCooler);

        double diffCooler = coolerBudget - bestCooler.getPrice();
        psuBudget += diffCooler * 0.80;
        caseboxBudget += diffCooler * 0.20;
        System.out.println("\nCooler diff added\n" + String.format(
                "Budgets: CPU = %.2f, GPU = %.2f, RAM = %.2f, Motherboard = %.2f, SSD = %.2f, Cooler = %.2f, PSU = %.2f, Case = %.2f",
                cpuBudget, gpuBudget, ramBudget, motherboardBudget, ssdBudget, coolerBudget, psuBudget, caseboxBudget
        ));
        psus = psuDao.doRetrieveAllByPriceBetween(1, psuBudget);
        caseboxes = caseboxDao.doRetrieveAllByPriceBetween(1, caseboxBudget);

        // Calcola il TDP totale della build
        int totalTdp = 0;
        totalTdp += bestProcessor.getTdp();
        totalTdp += bestGPU.getTdp();
        totalTdp += bestRAM.getTdp();
        totalTdp += bestMotherboard.getTdp();
        totalTdp += bestSSD.getTdp();
        totalTdp += bestCooler.getTdp();
        System.out.println("Total TDP of the build: " + totalTdp);
        // Ordine di priorità per l'efficienza
        Map<String, Integer> efficiencyPriority = new HashMap<>();
        efficiencyPriority.put("80+ Titanium", 6);
        efficiencyPriority.put("80+ Platinum", 5);
        efficiencyPriority.put("80+ Gold", 4);
        efficiencyPriority.put("80+ Silver", 3);
        efficiencyPriority.put("80+ Bronze", 2);
        efficiencyPriority.put("80+", 1);
        efficiencyPriority.put("unknown", 0);

        // Trova il bestPSU
        int finalTotalTdp = totalTdp;
        PSU bestPSU = psus.stream()
                .filter(p -> p.getWattage() >= finalTotalTdp)
                .filter(p -> efficiencyPriority.containsKey(p.getEfficiency()))
                .max(Comparator.comparingDouble(p -> {
                    int efficiencyScore = efficiencyPriority.getOrDefault(p.getEfficiency(), 0);
                    double wattageScore = p.getWattage() - finalTotalTdp;
                    return (efficiencyScore * 2) + (wattageScore * 0.1); // Peso maggiore all'efficienza
                }))
                .orElse(null);

        if (bestPSU == null) {
            System.out.println("No PSU found within the budget.");
            response.sendError(422, "No PSU found within the budget.");
            return;
        }
        System.out.println("Selected PSU: " + bestPSU);

        double diffCase = psuBudget - bestPSU.getPrice();
        caseboxBudget += diffCase;
        System.out.println("\nPSU diff added\n" + String.format(
                "Budgets: CPU = %.2f, GPU = %.2f, RAM = %.2f, Motherboard = %.2f, SSD = %.2f, Cooler = %.2f, PSU = %.2f, Case = %.2f",
                cpuBudget, gpuBudget, ramBudget, motherboardBudget, ssdBudget, coolerBudget, psuBudget, caseboxBudget
        ));
        caseboxes = caseboxDao.doRetrieveAllByPriceBetween(1, caseboxBudget);

        Casebox bestCase = caseboxes.stream()
                .filter(c -> c.getMax_cooler_height() >= bestCooler.getCooler_height())
                .filter(c -> c.getRadiator_size() >= (bestCooler.getRadiator_size() != null ? bestCooler.getRadiator_size() : 0))
                .filter(c -> c.getGpu_lenght() >= bestGPU.getLenght())
                .filter(c -> c.getForm_factor() != null && c.getForm_factor().contains(bestMotherboard.getForm_factor()))
                .filter(c -> c.getPsu_lenght() >= bestPSU.getLenght())
                .filter(c -> c.getPcie_slots() >= 1)
                .min(Comparator.comparingDouble(Casebox::getPrice))
                .orElse(null);

        if (bestCase == null) {
            System.out.println("No suitable case found for the build.");
        } else {
            System.out.println("Selected Case: " + bestCase);
        }

        System.out.println("Remaining budget: " + (params.price - bestProcessor.getPrice() - bestGPU.getPrice() - bestRAM.getPrice() - bestMotherboard.getPrice() - bestSSD.getPrice() - bestCooler.getPrice() - bestPSU.getPrice() - bestCase.getPrice()));

        DataWrapper dataWrapper = new DataWrapper(bestProcessor, bestGPU, bestRAM, bestMotherboard, bestSSD, bestCooler, bestPSU, bestCase);
        String jsonResponse = gson.toJson(dataWrapper);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}