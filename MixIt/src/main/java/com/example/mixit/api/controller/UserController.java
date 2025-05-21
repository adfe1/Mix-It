package com.example.mixit.api.controller;

import com.example.mixit.api.model.User;
import com.example.mixit.api.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4321")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Alle Benutzer abrufen
    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // Benutzer per ID abrufen
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        Optional<User> user = userRepository.findById(Long.valueOf(id));
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Benutzer erstellen
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return ResponseEntity.created(URI.create("/api/" + savedUser.getId()))
                .body(savedUser);
    }

    // Benutzer aktualisieren
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        return userRepository.findById(Long.valueOf(id)).map(existingUser -> {
            existingUser.setEmail(user.getEmail());
            existingUser.setAge(user.getAge());
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            existingUser.setVerified(user.isVerified());
            return ResponseEntity.ok(userRepository.save(existingUser));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Benutzer löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if (!userRepository.existsById(Long.valueOf(id))) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }

    // ✅ Registrierung mit Passwort-Hashing
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Benutzername existiert bereits!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(UUID.randomUUID().toString());
        userRepository.save(user);
        return ResponseEntity.ok("Erfolgreich registriert!");
    }
}
