package com.example.mixit.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@CrossOrigin(origins = "http://localhost:4321") // Astro-Dev-Port!
@RestController
@RequestMapping("/api/cocktails")
public class CocktailController {

    @GetMapping("/search")
    public ResponseEntity<?> searchCocktails(@RequestParam String query) {
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s="
                + URLEncoder.encode(query, StandardCharsets.UTF_8);
        RestTemplate restTemplate = new RestTemplate();
        Object response = restTemplate.getForObject(url, Object.class);
        return ResponseEntity.ok(response);
    }
}
