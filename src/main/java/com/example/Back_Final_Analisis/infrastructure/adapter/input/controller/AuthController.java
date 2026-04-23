package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.UserUseCase.LoginUseCase;
import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.infrastructure.adapter.input.dto.LoginRequestDTO;
import com.example.Back_Final_Analisis.infrastructure.adapter.input.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Endpoints de login del sistema")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @Operation(summary = "Iniciar sesión", description = "Autentica al usuario con email y contraseña y retorna su información")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        User user = loginUseCase.execute(request.getEmail(), request.getPassword());
        LoginResponseDTO response = LoginResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .vendorCode(user.getVendorCode())
                .role(user.getRole())
                .message("Inicio de sesión exitoso")
                .build();
        return ResponseEntity.ok(response);
    }
}
