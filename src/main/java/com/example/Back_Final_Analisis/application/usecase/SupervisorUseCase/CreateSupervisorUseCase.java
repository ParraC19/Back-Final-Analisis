package com.example.Back_Final_Analisis.application.usecase.SupervisorUseCase;

import com.example.Back_Final_Analisis.domain.enums.Role;
import com.example.Back_Final_Analisis.domain.model.Supervisor;
import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.SupervisorRepositoryPort;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateSupervisorUseCase {

    private final SupervisorRepositoryPort supervisorRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    public CreateSupervisorUseCase(SupervisorRepositoryPort supervisorRepositoryPort,
                                   UserRepositoryPort userRepositoryPort) {
        this.supervisorRepositoryPort = supervisorRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    public Supervisor execute(Supervisor supervisor) {
        // 1. Verificar que el User existe
        User user = userRepositoryPort.findById(supervisor.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + supervisor.getUserId()));

        // 2. Verificar que el User tiene rol SUPERVISOR
        if (user.getRole() != Role.SUPERVISOR) {
            throw new RuntimeException("El usuario no tiene rol SUPERVISOR. Rol actual: " + user.getRole());
        }

        // 3. Enriquecer datos desde User
        supervisor.setUserName(user.getName());
        supervisor.setUserEmail(user.getEmail());

        return supervisorRepositoryPort.save(supervisor);
    }
}