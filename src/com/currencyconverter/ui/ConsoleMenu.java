package com.currencyconverter.ui;

import com.currencyconverter.service.CurrencyConverter;
import com.currencyconverter.service.CurrencyConverter.ConversionResult;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interface textual (console) do Conversor de Moedas.
 * Responsável exclusivamente pela interação com o usuário: exibição do menu,
 * leitura de dados e apresentação dos resultados.
 */
public class ConsoleMenu {

    // Cores ANSI para o terminal
    private static final String RESET  = "\u001B[0m";
    private static final String CYAN   = "\u001B[36m";
    private static final String GREEN  = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED    = "\u001B[31m";
    private static final String BOLD   = "\u001B[1m";

    private final CurrencyConverter converter;
    private final Scanner scanner;

    // ---- Opções do menu ----
    // Cada linha: { moeda origem, moeda destino, descrição }
    private static final String[][] MENU_OPTIONS = {
            {"USD", "BRL", "Dólar Americano      →  Real Brasileiro"},
            {"BRL", "USD", "Real Brasileiro      →  Dólar Americano"},
            {"USD", "ARS", "Dólar Americano      →  Peso Argentino"},
            {"USD", "BOB", "Dólar Americano      →  Boliviano Boliviano"},
            {"USD", "CLP", "Dólar Americano      →  Peso Chileno"},
            {"USD", "COP", "Dólar Americano      →  Peso Colombiano"},
            {"BRL", "ARS", "Real Brasileiro      →  Peso Argentino"},
            {"BRL", "COP", "Real Brasileiro      →  Peso Colombiano"},
    };

    public ConsoleMenu() {
        this.converter = new CurrencyConverter();
        this.scanner   = new Scanner(System.in);
    }

    /**
     * Inicia o loop principal de interação com o usuário.
     * Continua até que o usuário escolha a opção 0 (Sair).
     */
    public void start() {
        printWelcome();

        int choice = -1;
        while (choice != 0) {
            printMenu();
            choice = readMenuChoice();

            if (choice == 0) {
                printGoodbye();
                break;
            }

            if (choice < 1 || choice > MENU_OPTIONS.length) {
                printError("Opção inválida. Por favor, escolha um número do menu.");
                continue;
            }

            handleConversion(choice - 1);
        }

        scanner.close();
    }

    // -------------------------------------------------------------------------
    // Métodos privados de UI
    // -------------------------------------------------------------------------

    private void printWelcome() {
        System.out.println();
        System.out.println(CYAN + BOLD + "╔══════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + BOLD + "║        CONVERSOR DE MOEDAS  💱           ║" + RESET);
        System.out.println(CYAN + BOLD + "║   Taxas obtidas em tempo real via API    ║" + RESET);
        System.out.println(CYAN + BOLD + "╚══════════════════════════════════════════╝" + RESET);
        System.out.println();
    }

    private void printMenu() {
        System.out.println(BOLD + "─── Selecione uma conversão ───────────────" + RESET);
        for (int i = 0; i < MENU_OPTIONS.length; i++) {
            System.out.printf("  %s%d%s. %s%n",
                    YELLOW, i + 1, RESET,
                    MENU_OPTIONS[i][2]);
        }
        System.out.println(BOLD + "  0. Sair" + RESET);
        System.out.println(BOLD + "───────────────────────────────────────────" + RESET);
        System.out.print(BOLD + "Escolha: " + RESET);
    }

    private int readMenuChoice() {
        try {
            int value = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            return value;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Descarta entrada inválida
            printError("Entrada inválida. Digite apenas um número inteiro.");
            return -1;
        }
    }

    private void handleConversion(int index) {
        String from = MENU_OPTIONS[index][0];
        String to   = MENU_OPTIONS[index][1];

        System.out.printf("%nDigite o valor em %s%s%s a converter: ", YELLOW, from, RESET);

        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            printError("Valor inválido. Use apenas números (ex: 100 ou 99.50).");
            return;
        }

        try {
            System.out.println(CYAN + "\n⏳ Buscando taxa de câmbio em tempo real..." + RESET);
            ConversionResult result = converter.convert(from, to, amount);
            printResult(result);
        } catch (IllegalArgumentException e) {
            printError(e.getMessage());
        } catch (RuntimeException e) {
            printError("Erro ao consultar a API: " + e.getMessage());
        }
    }

    private void printResult(ConversionResult result) {
        System.out.println();
        System.out.println(GREEN + BOLD + "✅ Resultado da Conversão:" + RESET);
        System.out.println(GREEN + "   " + result + RESET);
        System.out.println();
    }

    private void printError(String message) {
        System.out.println();
        System.out.println(RED + "❌ " + message + RESET);
        System.out.println();
    }

    private void printGoodbye() {
        System.out.println();
        System.out.println(CYAN + BOLD + "Obrigado por usar o Conversor de Moedas! Até logo! 👋" + RESET);
        System.out.println();
    }
}
