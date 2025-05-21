package com.example.mixit.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String email;
    private int age;
    private String password;
    private boolean verified;
    private String username;

    public User() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    public User(String id, String email, int age, String password, boolean verified) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.password = password;
        this.verified = verified;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
