package com.example.Back_Final_Analisis.application.usecase;

import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public CreateUserUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User execute(User user) {
        if (userRepositoryPort.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El usuario con este correo ya existe.");
        }

        return userRepositoryPort.save(user);
    }
}