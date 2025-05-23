package com.example.mixit.api.model;

public class Cocktail {
    private String name;

    public Cocktail() {
        // Leerer Konstruktor für Jackson
    }

    public Cocktail(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
