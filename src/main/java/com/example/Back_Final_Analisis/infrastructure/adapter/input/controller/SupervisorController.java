package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.SupervisorUseCase.GetAllSupervisorsUseCase;
import com.example.Back_Final_Analisis.application.usecase.SupervisorUseCase.GetSupervisorByIdUseCase;
import com.example.Back_Final_Analisis.domain.model.Supervisor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supervisors")
@Tag(name = "Controlador de Supervisores", description = "Gestión de perfiles de supervisores")
public class SupervisorController {

    private final GetAllSupervisorsUseCase getAllSupervisorsUseCase;
    private final GetSupervisorByIdUseCase getSupervisorByIdUseCase;

    public SupervisorController(GetAllSupervisorsUseCase getAllSupervisorsUseCase,
                                GetSupervisorByIdUseCase getSupervisorByIdUseCase) {
        this.getAllSupervisorsUseCase = getAllSupervisorsUseCase;
        this.getSupervisorByIdUseCase = getSupervisorByIdUseCase;
    }

    @Operation(summary = "Listar todos los supervisores")
    @GetMapping
    public ResponseEntity<List<Supervisor>> getAllSupervisors() {
        return ResponseEntity.ok(getAllSupervisorsUseCase.execute());
    }

    @Operation(summary = "Obtener supervisor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Supervisor> getSupervisorById(@PathVariable Long id) {
        return ResponseEntity.ok(getSupervisorByIdUseCase.execute(id));
    }
}
