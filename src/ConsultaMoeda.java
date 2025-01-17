import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoeda {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/64900edc4a1b6d8d6a01155b/latest/";

    public double obterTaxaDeConversao(String moedaBase, String moedaDestino) {
        String endpoint = API_URL + moedaBase;

        // Criar a requisição HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .header("Accept", "application/json")
                .build();

        HttpClient client = HttpClient.newHttpClient();

        try {
            // Enviar a solicitação e capturar a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na API: Código HTTP " + response.statusCode());
            }

            // Parse da resposta JSON
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            // Obter as taxas de conversão
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

            // Retornar a taxa de conversão da moeda de destino
            return conversionRates.get(moedaDestino).getAsDouble();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao consultar a API: " + e.getMessage(), e);
        }
    }
}
