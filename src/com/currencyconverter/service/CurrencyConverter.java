package com.currencyconverter.service;

import com.currencyconverter.api.ApiClient;
import com.currencyconverter.model.ExchangeRateResponse;
import com.google.gson.Gson;

/**
 * Serviço de conversão de moedas.
 * Orquestra a chamada à API e aplica a lógica de conversão.
 */
public class CurrencyConverter {

    private final ApiClient apiClient;
    private final Gson gson;

    public CurrencyConverter() {
        this.apiClient = new ApiClient();
        this.gson = new Gson();
    }

    /**
     * Converte um valor de uma moeda de origem para uma moeda de destino,
     * buscando as taxas em tempo real na API.
     *
     * @param fromCurrency Código da moeda de origem (ex: "USD")
     * @param toCurrency   Código da moeda de destino (ex: "BRL")
     * @param amount       Valor a ser convertido
     * @return Resultado da conversão
     * @throws IllegalArgumentException se o valor for negativo ou zero
     */
    public ConversionResult convert(String fromCurrency, String toCurrency, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        // 1. Busca JSON bruto da API
        String jsonResposta = apiClient.fetchLatestRates(fromCurrency);

        // 2. Parseia JSON para o modelo
        ExchangeRateResponse response = gson.fromJson(jsonResposta, ExchangeRateResponse.class);

        if (!"success".equalsIgnoreCase(response.getResult())) {
            throw new RuntimeException("A API retornou um resultado inesperado: " + response.getResult());
        }

        // 3. Obtém a taxa e calcula
        double rate = response.getRateFor(toCurrency);
        double convertedAmount = amount * rate;

        return new ConversionResult(fromCurrency, toCurrency, amount, convertedAmount, rate);
    }

    // -------------------------------------------------------------------------
    // Inner record para encapsular o resultado da conversão
    // -------------------------------------------------------------------------

    /**
     * Encapsula todos os dados de uma conversão realizada com sucesso.
     */
    public record ConversionResult(
            String fromCurrency,
            String toCurrency,
            double originalAmount,
            double convertedAmount,
            double rate
    ) {
        @Override
        public String toString() {
            return String.format(
                    "%.2f %s = %.2f %s  (taxa: 1 %s = %.4f %s)",
                    originalAmount, fromCurrency,
                    convertedAmount, toCurrency,
                    fromCurrency, rate, toCurrency
            );
        }
    }
}
