package com.alura.conversor.menu;

import java.util.Scanner;

public class Menu {
    public String getMenu(){
        String menu = """
                ======================================================================
                                           MENÚ PRINCIPAL
               ======================================================================
                1. Mostrar lista de monedas para convertir
                2. Convertir de USD (Dolar Estadounidence) a  BRL (Real Brasileño)
                3. Convertir de BRL (Real Brasileño) a  USD (Dolar Estadounidence)
                4. Convertir de USD (Dolar Estadounidence) a  ARS (Peso Argentino)
                5. Convertir de ARS (Peso Argentino) a  USD (Dolar Estadounidence)
                6. Convertir de USD (Dolar Estadounidence) a  COP (Peso Colombiano)
                7. Convertir de COP (Peso Colombiano) a  USD (Dolar Estadounidence)
                8. Salir
                ======================================================================
                Seleccione una opción:""";
        System.out.println(menu);
        return menu;
    }
}
