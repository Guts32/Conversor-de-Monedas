package com.alura.conversor.conexion;

import com.alura.conversor.conversion.Conversion;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conexion {

    Conversion conversion = new Conversion();
    //Json estatico para almacenar datos de consulta
    static JsonArray supportedCodesArray;

    //Metodo para realizar conversiones
    public void httpClient(String mBase, String mCambio, double mConversion) {
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
    public void httpList(){
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
                StringBuilder response = new StringBuilder();
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
                mostrarEnColumnas(supportedCodesArray);
                //Llamando el metodo para convertir
                codigoMoneda();
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
    private static void mostrarEnColumnas(JsonArray array) {
        List<List<String>> columnData = new ArrayList<>();
        int[] columnWidths = new int[4];

        // Preparar datos para cada columna y calcular anchuras
        for (int i = 0; i < 4; i++) {
            List<String> column = new ArrayList<>();
            for (int j = i; j < array.size(); j += 4) {
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
        for (int i = 0; i < columnData.getFirst().size(); i++) {
            for (int j = 0; j < 4; j++) {
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
                return false;
            }
        }
        return true;
    }


    //Metodo para obtener datos de teclado
    public void codigoMoneda() {
        Conexion apiConexion = new Conexion();
        System.out.println("Digite el codigo de la moneda Base");
        String codigoBase;
        String mBase;
        String mCambio;
        double conversionResult;
        //Llamando el metodo para validar codigo de moneda
        Scanner scanner = new Scanner(System.in);
        do {
            codigoBase = scanner.nextLine();
            if (apiConexion.validarCodigoMoneda(codigoBase)) {
                System.out.println("Digite un Codigo Valido");
            }
        } while (apiConexion.validarCodigoMoneda(codigoBase));

        mBase = codigoBase;

        System.out.println("Digite el codigo de la moneda de Cambio");
        String codigoCambio;
        //Llamando el metodo para validar codigo de moneda
        do {
            codigoCambio = scanner.nextLine();
            if (apiConexion.validarCodigoMoneda(codigoCambio)) {
                System.out.println("Digite un Codigo Valido");
            }
        } while (apiConexion.validarCodigoMoneda(codigoCambio));

        mCambio = codigoCambio;
        conversion.convert(mBase,mCambio);

    }
}
