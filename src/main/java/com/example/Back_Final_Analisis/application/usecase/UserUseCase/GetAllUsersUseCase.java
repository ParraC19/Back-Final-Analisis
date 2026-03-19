package com.example.Back_Final_Analisis.application.usecase.UserUseCase;

import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public GetAllUsersUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public List<User> execute() {
        return userRepositoryPort.findAll();
    }
}