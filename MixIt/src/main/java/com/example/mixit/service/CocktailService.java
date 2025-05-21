package com.example.mixit.service;

import com.example.mixit.api.model.Cocktail;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CocktailService {
    private final WebClient webClient = WebClient.create("https://www.thecocktaildb.com/api/json/v1/1");

    public String fetchCocktails(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search.php")
                        .queryParam("s", query)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Achtung: block() nur bei einfachen synchronen Fällen
    }

    private final List<Cocktail> allCocktails = List.of(
            new Cocktail("Mojito"),
            new Cocktail("Pina Colada"),
            new Cocktail("Caipirinha")
    );

    public List<Cocktail> search(String keyword) {
        return allCocktails.stream()
                .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
