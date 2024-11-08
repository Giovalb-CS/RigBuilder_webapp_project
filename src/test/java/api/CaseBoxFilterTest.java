package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CaseBoxFilterTest {

    private static final String API_KEY = "1P9N112129";

    public static void post(){
        try {
            // Endpoint URL
            String endpointUrl = "http://localhost:8080/RigBuilder_webapp_project_war_exploded/api/cases/filters";

            // Creazione del JSON dei parametri di filtro
            Gson gson = new Gson();
            JsonObject filterParams = new JsonObject();
//            filterParams.addProperty("name", "Cooler Master");
//            filterParams.addProperty("priceSort", "asc");
//            filterParams.addProperty("ratingSort", "desc");
            filterParams.addProperty("minPrice", 0.0);
            filterParams.addProperty("maxPrice", 100.0);
            filterParams.addProperty("minCoolerHeight", 180);
            filterParams.addProperty("minRadiatorSize", 240);
            filterParams.addProperty("minGPULength", 400);
            filterParams.addProperty("formFactor", "ATX");
            filterParams.addProperty("minPSULength", 284);
            filterParams.addProperty("minPCIeSlots", 8);
            filterParams.addProperty("minRating", 4.0);
            filterParams.addProperty("maxRating", 4.6);

            // Converte in JSON
            String jsonInputString = gson.toJson(filterParams);

            // Configurazione della connessione
            URL url = new URL(endpointUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("X-API-KEY", API_KEY);
            conn.setDoOutput(true);

            // Invia i dati JSON
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Leggi la risposta
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void get(){
        try {
            // Endpoint URL
            String endpointUrl = "http://localhost:8080/RigBuilder_webapp_project_war_exploded/api/cases";

            // Configurazione della connessione
            URL url = new URL(endpointUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // Use GET instead of POST
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("X-API-KEY", API_KEY);

            // Leggi la risposta
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response from the input stream
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.out.println("Get Request:\n");
        get();
        System.out.println("\n========================================\n");
        System.out.println("Post Request:\n");
        post();
    }
}
