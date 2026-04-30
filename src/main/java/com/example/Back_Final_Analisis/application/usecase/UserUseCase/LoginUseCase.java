package com.example.Back_Final_Analisis.application.usecase.UserUseCase;

import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginUseCase(UserRepositoryPort userRepositoryPort, BCryptPasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(String email, String password) {
        User user = userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return user;
    }
}