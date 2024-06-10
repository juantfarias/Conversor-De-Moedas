package br.com.alura.cambio.ApiRequest;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SolicitadorTaxaCambio {

    private String chaveApi = "your-key";

    public Double receberValor(String moeda1, String moeda2) throws IOException, InterruptedException {
        String endereco = "https://v6.exchangerate-api.com/v6/"+this.chaveApi+"/pair/"+moeda1+"/"+moeda2;
        Gson gson = new Gson();

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

        var taxaConversao  = gson.fromJson(resposta.body(), FiltroAPI.class);
        return taxaConversao.conversion_rate();
    }
}
