package com.alura.conversordemonedas;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

public class ConsultaApi {
    public Moneda obtenerMoneda(String baseCurrency) {
        try {
            URI direccion = URI.create("https://v6.exchangerate-api.com/v6/5ee2a3e1a4f43ee354b96fe4/latest/" + baseCurrency);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            return gson.fromJson(response.body(), Moneda.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
