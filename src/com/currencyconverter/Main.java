package com.currencyconverter;

import com.currencyconverter.ui.ConsoleMenu;

/**
 * Ponto de entrada do Conversor de Moedas.
 * Instancia e inicia o menu de console.
 */
public class Main {

    public static void main(String[] args) {
        ConsoleMenu menu = new ConsoleMenu();
        menu.start();
    }
}
