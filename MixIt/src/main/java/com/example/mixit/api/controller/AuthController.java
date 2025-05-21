package com.example.mixit.api.controller;

import com.example.mixit.api.model.LoginRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getUsername()) && "1234".equals(request.getPassword())) {
            return ResponseEntity.ok(Map.of("message", "Login erfolgreich"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Ungültige Anmeldedaten"));
        }
    }
}
