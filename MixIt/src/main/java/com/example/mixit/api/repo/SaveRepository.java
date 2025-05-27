package com.example.mixit.api.repo;

import com.example.mixit.api.model.Save;
import com.example.mixit.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SaveRepository extends JpaRepository<Save, Long> {
    List<Save> findByUser(User user);
}
