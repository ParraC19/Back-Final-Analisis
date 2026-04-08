package com.example.Back_Final_Analisis.application.usecase.SupervisorUseCase;

import com.example.Back_Final_Analisis.domain.model.Supervisor;
import com.example.Back_Final_Analisis.domain.port.SupervisorRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetSupervisorByIdUseCase {

    private final SupervisorRepositoryPort supervisorRepositoryPort;

    public GetSupervisorByIdUseCase(SupervisorRepositoryPort supervisorRepositoryPort) {
        this.supervisorRepositoryPort = supervisorRepositoryPort;
    }

    public Supervisor execute(Long id) {
        return supervisorRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Supervisor no encontrado con id: " + id));
    }
}
