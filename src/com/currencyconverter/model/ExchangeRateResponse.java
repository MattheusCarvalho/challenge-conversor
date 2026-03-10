package com.currencyconverter.model;

import java.util.Map;

/**
 * Modelo que representa a resposta JSON da ExchangeRate API.
 * Contém o código da moeda base e um mapa com todas as taxas de conversão.
 */
public class ExchangeRateResponse {

    private String result;
    private String base_code;
    private Map<String, Double> conversion_rates;

    // --- Getters ---

    public String getResult() {
        return result;
    }

    public String getBaseCode() {
        return base_code;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    /**
     * Retorna a taxa de conversão para a moeda informada.
     *
     * @param currencyCode Código ISO 4217 da moeda de destino (ex: "BRL")
     * @return taxa de conversão em relação à moeda base
     * @throws IllegalArgumentException se a moeda não for encontrada na resposta
     */
    public double getRateFor(String currencyCode) {
        if (conversion_rates == null || !conversion_rates.containsKey(currencyCode)) {
            throw new IllegalArgumentException(
                    "Moeda não encontrada na resposta da API: " + currencyCode
            );
        }
        return conversion_rates.get(currencyCode);
    }

    @Override
    public String toString() {
        return "ExchangeRateResponse{result='" + result + "', base_code='" + base_code + "'}";
    }
}
