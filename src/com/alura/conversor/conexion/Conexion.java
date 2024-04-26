package com.alura.conversor.conexion;

import com.alura.conversor.conversion.Conversion;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
    Conversion conversion = new Conversion();
    public void HttpClient(String mBase, String mCambio, double mConversion) {
        String apiKey = "415a7578d6ba87811ce2a6d9";
        String url_str = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/"
                + mBase + "/" + mCambio + "/" + mConversion;

        try {
            URL url = new URL(url_str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Convertir el contenido de la respuesta en un objeto GSON
                Gson gson = new Gson();
                JsonObject json = gson.fromJson(response.toString(), JsonObject.class);

                double conversionResult = json.get("conversion_result").getAsDouble();
                String resultado = String.format("%.2f", conversionResult);
                System.out.println("El resultado es: " + resultado);
            } else {
                System.out.println("La solicitud no fue exitosa. Código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HttpList(){
        try {
            // Defino la URL de la API y tu clave de acceso
            String url = "https://v6.exchangerate-api.com/v6/415a7578d6ba87811ce2a6d9/codes";

            // Crea la conexión HTTP
            URL apiUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) apiUrl.openConnection();
            con.setRequestMethod("GET");

            // Obtiene el código de respuesta
            int responseCode = con.getResponseCode();

            // Verifica el código de respuesta
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // La solicitud fue exitosa
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Convierte la respuesta a un objeto JSON
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

                // Accede a los datos de la respuesta (supported_codes)
                JsonArray supportedCodesArray = jsonObject.getAsJsonArray("supported_codes");

                // Imprimir la lista de códigos de moneda y nombres de países
                System.out.println("Códigos de moneda y nombres de países disponibles para conversión:");
                mostrarEnColumnas(supportedCodesArray,4);
                conversion.cUToU();
            } else {
                // Manejar el caso en que la solicitud no fue exitosa
                System.out.println("La solicitud no fue exitosa. Código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Método para mostrar la lista en varias columnas con columnas ajustadas en anchura
    private static void mostrarEnColumnas(JsonArray array, int columnas) {
        List<List<String>> columnData = new ArrayList<>();
        int[] columnWidths = new int[columnas];

        // Preparar datos para cada columna y calcular anchuras
        for (int i = 0; i < columnas; i++) {
            List<String> column = new ArrayList<>();
            for (int j = i; j < array.size(); j += columnas) {
                JsonArray currencyArray = array.get(j).getAsJsonArray();
                String currencyCode = currencyArray.get(0).getAsString();
                String countryName = currencyArray.get(1).getAsString();
                String entry = currencyCode + " - " + countryName;
                column.add(entry);
                columnWidths[i] = Math.max(columnWidths[i], entry.length());
            }
            columnData.add(column);
        }

        // Imprimir datos con anchuras ajustadas
        for (int i = 0; i < columnData.get(0).size(); i++) {
            for (int j = 0; j < columnas; j++) {
                if (i < columnData.get(j).size()) {
                    String entry = columnData.get(j).get(i);
                    System.out.printf("%-" + (columnWidths[j] + 2) + "s", entry); // +2 para el espacio entre columnas
                }
            }
            System.out.println();
        }
    }
}
