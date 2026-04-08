package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.SupervisorUseCase.*;
import com.example.Back_Final_Analisis.domain.model.Supervisor;
import com.example.Back_Final_Analisis.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supervisors")
@Tag(name = "Controlador de Supervisores", description = "Gestión de perfiles de supervisores")
public class SupervisorController {

    private final CreateSupervisorUseCase createSupervisorUseCase;
    private final GetAllSupervisorsUseCase getAllSupervisorsUseCase;
    private final GetSupervisorByIdUseCase getSupervisorByIdUseCase;

    public SupervisorController(CreateSupervisorUseCase createSupervisorUseCase,
                                GetAllSupervisorsUseCase getAllSupervisorsUseCase,
                                GetSupervisorByIdUseCase getSupervisorByIdUseCase) {
        this.createSupervisorUseCase = createSupervisorUseCase;
        this.getAllSupervisorsUseCase = getAllSupervisorsUseCase;
        this.getSupervisorByIdUseCase = getSupervisorByIdUseCase;
    }

    @Operation(summary = "Registrar perfil de supervisor a un usuario existente")
    @PostMapping
    public ResponseEntity<Supervisor> createSupervisor(@Valid @RequestBody Supervisor supervisor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createSupervisorUseCase.execute(supervisor));
    }

    @Operation(summary = "Listar todos los supervisores")
    @GetMapping
    public ResponseEntity<List<Supervisor>> getAllSupervisors() {
        return ResponseEntity.ok(getAllSupervisorsUseCase.execute());
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Supervisor> getSupervisorById(@PathVariable Long id) {
        return ResponseEntity.ok(getSupervisorByIdUseCase.execute(id));
    }
}