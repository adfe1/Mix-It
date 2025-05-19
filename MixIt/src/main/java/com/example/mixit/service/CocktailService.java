package com.example.mixit.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CocktailService {
    private final WebClient webClient = WebClient.create("www.thecocktaildb.com/api/json/v1/1");

    public Mono<String> fetchCocktailsByFirstLetter(String letter) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search.php")
                        .queryParam("i"+letter)
                        .build())
                .retrieve()
                .bodyToMono(String.class); // Für den Anfang als String, später DataResponse.class
    }
}
