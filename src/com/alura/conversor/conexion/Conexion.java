package com.alura.conversor.conexion;

import com.alura.conversor.conversion.Conversion;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Conexion {

    Conversion conversion = new Conversion();
    //Json estatico para almacenar datos de consulta
    static JsonArray supportedCodesArray;

    //Metodo para realizar conversiones
    public void HttpClient(String mBase, String mCambio, double mConversion) {
        //clave de api
        String apiKey = "415a7578d6ba87811ce2a6d9";
        //URL
        String url_str = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/"
                + mBase + "/" + mCambio + "/" + mConversion;

        try {
            //Conectando a api
            URL url = new URL(url_str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            //Validando si se tiene conexion
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //Almacenando en buffer
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

                //Setear resultado con dos decimales
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
//funcion para listar los codigos de monedas y paises
    public void HttpList(){
        try {
            //url para obtener los codigos
            String url = "https://v6.exchangerate-api.com/v6/415a7578d6ba87811ce2a6d9/codes";

            //realizando conexion
            URL apiUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) apiUrl.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            //Validando conexion
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //Almacenando en buffer
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //Usando Gson
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

                supportedCodesArray = jsonObject.getAsJsonArray("supported_codes");

                // Imprimir la lista de códigos de moneda y nombres de países
                System.out.println("Códigos de moneda y nombres de países disponibles para conversión:");
                //Llamando metodo para crear 4 columnas de codigos
                mostrarEnColumnas(supportedCodesArray,4);
                //Llamando el metodo para convertir
                conversion.cUToU();
                //Cerrando conexion
                con.disconnect();
            } else {
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

    //Metodo para validar el Codigo de las Monedas
    public boolean validarCodigoMoneda(String codigoMoneda) {
        //Haciendo recorrido en el arreglo con los datos obtenidos de la lista
        for (int i = 0; i < supportedCodesArray.size(); i++) {
            JsonArray currencyArray = supportedCodesArray.get(i).getAsJsonArray();
            String currencyCode = currencyArray.get(0).getAsString();
            //validando si coincide el texto ingresado y el codigo de moneda
            if (currencyCode.equals(codigoMoneda)) {
                return true;
            }
        }
        return false;
    }
}
