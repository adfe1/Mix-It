package com.example.mixit.api.controller;

import com.example.mixit.api.model.Save;
import com.example.mixit.api.model.User;
import com.example.mixit.api.payload.request.SaveRequest;
import com.example.mixit.api.repo.SaveRepository;
import com.example.mixit.api.security.services.UserDetailsServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/save")
@CrossOrigin(origins = "http://localhost:3000") // Dein Frontend-Port
public class SaveController {

    @Autowired
    private SaveRepository saveRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<String> addSave(@RequestBody SaveRequest request) {
        User currentUser = userDetailsService.getCurrentUser();

        Save save = new Save();
        save.setIdDrink(request.getIdDrink());
        save.setUser(currentUser);

        saveRepository.save(save);
        return ResponseEntity.ok("Drink gespeichert");
    }

    @GetMapping("/my")
    public ResponseEntity<List<Save>> getMySaves() {
        User currentUser = userDetailsService.getCurrentUser();
        List<Save> saves = saveRepository.findByUser(currentUser);
        return ResponseEntity.ok(saves);
    }
}
