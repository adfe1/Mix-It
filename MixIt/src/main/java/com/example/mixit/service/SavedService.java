package com.example.mixit.service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class SavedService {
        private final WebClient webClient = WebClient.create("https://www.thecocktaildb.com/api/json/v1/1");
        public Mono<String> fetchCocktails(String saved) {

                return webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/lookup.php?")
                                .queryParam("i", saved)
                                .build())
                        .retrieve()
                        .bodyToMono(String.class);

        }


}
