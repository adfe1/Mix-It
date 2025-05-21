package com.example.mixit.api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.HashSet;

@CrossOrigin(origins = "http://localhost:4321")
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private Set<String> favorites = new HashSet<>();

    @GetMapping
    public Set<String> getFavorites() {
        return favorites;
    }

    @PostMapping("/{id}")
    public void addFavorite(@PathVariable String id) {
        favorites.add(id);
    }

    @DeleteMapping("/{id}")
    public void removeFavorite(@PathVariable String id) {
        favorites.remove(id);
    }
}
