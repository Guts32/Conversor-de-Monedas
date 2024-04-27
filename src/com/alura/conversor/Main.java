package com.alura.conversor;

import java.util.Scanner;
import com.alura.conversor.conexion.Conexion;
import com.alura.conversor.conversion.Conversion;
import com.alura.conversor.menu.Menu;

public class Main {
    public static void main(String[] args) {
        //Creacion del Scanner
        Scanner sc = new Scanner(System.in);
        int opcion;
        //Creando instancias
        Conversion conversion = new Conversion();
        Conexion conexion = new Conexion();

        //Mostrando menu y validando casos
        do {
            Menu menu = new Menu();
            menu.getMenu();
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrando la lista de códigos de moneda y nombres de países...");
                    conexion.httpList();
                    break;
                case 2:
                    System.out.println("Ha seleccionado la Opción de USD a BRL");
                    conversion.convert("USD","BRL");
                    break;
                case 3:
                    System.out.println("Ha seleccionado la Opción de BRL a USD");
                    conversion.convert("BRL","USD");
                    break;
                case 4:
                    System.out.println("Ha seleccionado la Opción de USD a ARS");
                    conversion.convert("USD","ARS");
                    break;
                case 5:
                    System.out.println("Ha seleccionado la Opción de USD a ARS");
                    conversion.convert("ARS","USD");
                    break;
                case 6:
                    System.out.println("Ha seleccionado la Opción de USD a COP");
                    conversion.convert("USD","COP");
                    break;
                case 7:
                    System.out.println("Ha seleccionado la Opción de COP a USD");
                    conversion.convert("COP","USD");
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