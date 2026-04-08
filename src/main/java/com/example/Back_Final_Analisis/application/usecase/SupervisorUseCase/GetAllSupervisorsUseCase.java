package com.example.Back_Final_Analisis.application.usecase.SupervisorUseCase;

import com.example.Back_Final_Analisis.domain.model.Supervisor;
import com.example.Back_Final_Analisis.domain.port.SupervisorRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllSupervisorsUseCase {

    private final SupervisorRepositoryPort supervisorRepositoryPort;

    public GetAllSupervisorsUseCase(SupervisorRepositoryPort supervisorRepositoryPort) {
        this.supervisorRepositoryPort = supervisorRepositoryPort;
    }

    public List<Supervisor> execute() {
        return supervisorRepositoryPort.findAll();
    }
}