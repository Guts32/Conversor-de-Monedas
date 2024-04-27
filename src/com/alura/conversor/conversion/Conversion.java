package com.alura.conversor.conversion;

import com.alura.conversor.conexion.Conexion;

import java.util.Scanner;

public class Conversion implements IConversion {
    Scanner scanner = new Scanner(System.in);


    @Override
    public void convert(String mBase, String mCambio) {
        System.out.println("Ingrese la cantidad en" +mBase+ "que desea convertir a "+mCambio+": ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpia el búfer del teclado
        }

        double mConversion = scanner.nextDouble();
        Conexion apiConexion = new Conexion();
        apiConexion.httpClient(mBase,mCambio, mConversion);

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
