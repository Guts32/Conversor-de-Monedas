package com.alura.conversor.conversion;

import com.alura.conversor.conexion.Conexion;

import java.util.Scanner;

public class Conversion {
    Scanner scanner = new Scanner(System.in);
    private String mBase = "";
    private String mCambio = "";
    private double mConversion = 0;


    //Dolar a Real Brasileño
    public void cUsdToBRL() {
        System.out.println("Ingrese la cantidad en Dolares(USD) que desea convertir a Real Brasileño(BRL): ");
        mBase = "USD";
        mCambio = "BRL";
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpia el búfer del teclado
        }

        mConversion = scanner.nextDouble();
        Conexion apiConexion = new Conexion();
        apiConexion.HttpClient(this.mBase, this.mCambio, this.mConversion);

    }

    //Real Brasileño a Dolar
    public void cBRLToUSD() {
        System.out.println("Ingrese la cantidad en Real Brasileño(BRL) que desea convertir a Dolares(USD): ");
        mBase = "BRL";
        mCambio = "USD";
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpia el búfer del teclado
        }

        mConversion = scanner.nextDouble();
        Conexion apiConexion = new Conexion();
        apiConexion.HttpClient(this.mBase, this.mCambio, this.mConversion);
    }

   //Dolar a Peso argentino
    public void cUsdToArs() {
        System.out.println("Ingrese la cantidad en Dolares(USD) que desea convertir a Pesos argentinos(ARS): ");
        mBase = "USD";
        mCambio = "ARS";
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpia el búfer del teclado
        }

        mConversion = scanner.nextDouble();
        Conexion apiConexion = new Conexion();
        apiConexion.HttpClient(this.mBase, this.mCambio, this.mConversion);
    }

    //Peso Argentino a Dolar
    public void cArsToUsd() {
        System.out.println("Ingrese la cantidad en Pesos argentinos(ARS) que desea convertir a Dolares(USD): ");
        mBase = "ARS";
        mCambio = "USD";
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpia el búfer del teclado
        }

        mConversion = scanner.nextDouble();
        Conexion apiConexion = new Conexion();
        apiConexion.HttpClient(this.mBase, this.mCambio, this.mConversion);
    }

    //Dolar a peso colombiano
    public void cUsdToCop() {
        System.out.println("Ingrese la cantidad en Dolares(USD) que desea convertir a Peso colombiano(COP): ");
        mBase = "USD";
        mCambio = "COP";
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpia el búfer del teclado
        }

        mConversion = scanner.nextDouble();
        Conexion apiConexion = new Conexion();
        apiConexion.HttpClient(this.mBase, this.mCambio, this.mConversion);
    }

    //Dolar a peso colombiano
    public void cCopToUsd() {
        System.out.println("Ingrese la cantidad en Peso colombiano(COP) que desea convertir a Dolares(USD): ");
        mBase = "USD";
        mCambio = "COP";
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpia el búfer del teclado
        }

        mConversion = scanner.nextDouble();
        Conexion apiConexion = new Conexion();
        apiConexion.HttpClient(this.mBase, this.mCambio, this.mConversion);
    }


    //Moneda Usuario a Moneda Usuario
    public void cUToU() {
        System.out.println("Digite el codigo de la moneda Base");
        mBase = scanner.nextLine();
        System.out.println("Digite el codigo de la moneda de Cambio");
        mCambio = scanner.nextLine();
        System.out.println("Digite el valor a convertir");
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpia el búfer del teclado
        }

        mConversion = scanner.nextDouble();
        Conexion apiConexion = new Conexion();
        apiConexion.HttpClient(this.mBase, this.mCambio, this.mConversion);
    }
}
