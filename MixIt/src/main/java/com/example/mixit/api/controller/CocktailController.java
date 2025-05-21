package com.example.mixit.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.example.mixit.api.model.Cocktail;
import com.example.mixit.service.CocktailService;

@RestController
@RequestMapping("/api")
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/cocktails")
    public ResponseEntity<Map<String, Object>> searchCocktails(@RequestParam String search) {
        List<Cocktail> drinks = cocktailService.search(search);
        Map<String, Object> response = new HashMap<>();
        response.put("drinks", drinks);
        return ResponseEntity.ok(response);
    }
}
