package com.example.Back_Final_Analisis.application.usecase.UserUseCase;

import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetVendorByCodeUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public GetVendorByCodeUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User execute(String vendorCode) {
        return userRepositoryPort.findByVendorCode(vendorCode)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con código: " + vendorCode));
    }
}