package com.example.Back_Final_Analisis.application.usecase.UserUseCase;

import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public LoginUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User execute(String email, String password) {
        User user = userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return user;
    }
}