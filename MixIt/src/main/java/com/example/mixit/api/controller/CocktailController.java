package com.example.mixit.api.controller;

import com.example.mixit.service.CocktailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/cocktails")
    public Mono<String> getCocktails(@RequestParam(defaultValue = "randomselection.php") String letter) {
        return cocktailService.fetchCocktailsByFirstLetter(letter);
    }
}