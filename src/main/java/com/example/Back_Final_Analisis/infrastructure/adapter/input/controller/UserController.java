package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.UserUseCase.CreateUserUseCase;
import com.example.Back_Final_Analisis.application.usecase.UserUseCase.GetAllUsersUseCase;
import com.example.Back_Final_Analisis.application.usecase.UserUseCase.GetUserByIdUseCase;
import com.example.Back_Final_Analisis.application.usecase.UserUseCase.GetVendorByCodeUseCase;
import com.example.Back_Final_Analisis.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Controlador de Usuarios", description = "Gestión de usuarios del sistema")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetVendorByCodeUseCase getVendorByCodeUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            GetAllUsersUseCase getAllUsersUseCase,
            GetUserByIdUseCase getUserByIdUseCase,
            GetVendorByCodeUseCase getVendorByCodeUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getVendorByCodeUseCase = getVendorByCodeUseCase;
    }

    @Operation(summary = "Crear un nuevo usuario")
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserUseCase.execute(user));
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(getAllUsersUseCase.execute());
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(getUserByIdUseCase.execute(id));
    }

    @Operation(summary = "Obtener vendedor por su código único")
    @GetMapping("/vendor/{vendorCode}")
    public ResponseEntity<User> getVendorByCode(@PathVariable String vendorCode) {
        return ResponseEntity.ok(getVendorByCodeUseCase.execute(vendorCode));
    }
}