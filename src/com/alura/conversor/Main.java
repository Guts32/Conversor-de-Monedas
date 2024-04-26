package com.alura.conversor;

import java.util.Scanner;
import com.alura.conversor.conexion.Conexion;
import com.alura.conversor.conversion.Conversion;
import com.alura.conversor.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        Conversion conversion = new Conversion();
        Conexion conexion = new Conexion();
        do {

            Menu menu = new Menu();
            menu.getMenu();
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrando la lista de códigos de moneda y nombres de países...");
                    conexion.HttpList();
                    break;
                case 2:
                    System.out.println("Ha seleccionado la Opción de USD a BRL");
                    conversion.cUsdToBRL();
                    break;
                case 3:
                    System.out.println("Ha seleccionado la Opción de BRL a USD");
                    conversion.cBRLToUSD();
                    break;
                case 4:
                    System.out.println("Ha seleccionado la Opción de USD a ARS");
                    conversion.cUsdToArs();
                    break;
                case 5:
                    System.out.println("Ha seleccionado la Opción de USD a ARS");
                    conversion.cArsToUsd();
                    break;
                case 6:
                    System.out.println("Ha seleccionado la Opción de USD a COP");
                    conversion.cUsdToCop();
                    break;
                case 7:
                    System.out.println("Ha seleccionado la Opción de COP a USD");
                    conversion.cCopToUsd();
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }while (opcion !=8);
    }
}