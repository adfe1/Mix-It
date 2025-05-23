package com.example.mixit.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CocktailService {

    private final WebClient webClient = WebClient.create("https://www.thecocktaildb.com/api/json/v1/1");

            public Mono<String> fetchCocktails(String querry) {
                if (querry.length() == 1) {
                    return webClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .path("/search.php")
                                    .queryParam("f", querry) // <-- RICHTIG
                                    .build())
                            .retrieve()
                            .bodyToMono(String.class);
                }
                else {
                    return webClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .path("/search.php")
                                    .queryParam("s", querry) // <-- RICHTIG
                                    .build())
                            .retrieve()
                            .bodyToMono(String.class);
                }
            }

}