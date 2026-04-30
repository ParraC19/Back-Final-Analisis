package com.example.Back_Final_Analisis.application.usecase.UserUseCase;

import com.example.Back_Final_Analisis.domain.enums.Role;
import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final BCryptPasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserRepositoryPort userRepositoryPort, BCryptPasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(User user) {
        if (userRepositoryPort.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese correo.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.VENDEDOR);
        User saved = userRepositoryPort.save(user);
        saved.setVendorCode("VEN-" + saved.getId());
        return userRepositoryPort.save(saved);
    }
}
