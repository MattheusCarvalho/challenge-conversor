package com.currencyconverter.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Camada de acesso à ExchangeRate API.
 * Responsável apenas por realizar a requisição HTTP e retornar o JSON bruto.
 */
public class ApiClient {

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String API_KEY  = "06dde6f86fa71de7eee278bd";

    private final HttpClient client;

    public ApiClient() {
        this.client = HttpClient.newHttpClient();
    }

    /**
     * Busca as taxas de câmbio mais recentes para a moeda base informada.
     *
     * @param baseCurrency Código ISO 4217 da moeda base (ex: "USD", "BRL")
     * @return corpo da resposta HTTP em formato JSON (String)
     * @throws RuntimeException se a requisição falhar ou o servidor retornar erro
     */
    public String fetchLatestRates(String baseCurrency) {
        String url = BASE_URL + API_KEY + "/latest/" + baseCurrency;

        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> resposta = client.send(
                    requisicao,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (resposta.statusCode() != 200) {
                throw new RuntimeException(
                        "Erro na requisição à API. Código HTTP: " + resposta.statusCode()
                );
            }

            return resposta.body();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Requisição interrompida: " + e.getMessage(), e);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Falha ao conectar à API: " + e.getMessage(), e);
        }
    }
}
