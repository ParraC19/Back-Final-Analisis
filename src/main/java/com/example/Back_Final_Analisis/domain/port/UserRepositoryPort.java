package com.example.Back_Final_Analisis.domain.port;

import com.example.Back_Final_Analisis.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    Optional<User> findById(Long id);
}