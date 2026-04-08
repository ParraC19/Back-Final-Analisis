package com.example.Back_Final_Analisis.application.usecase.SellerUseCase;

import com.example.Back_Final_Analisis.domain.enums.Role;
import com.example.Back_Final_Analisis.domain.model.Seller;
import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.SellerRepositoryPort;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateSellerUseCase {

    private final SellerRepositoryPort sellerRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    public CreateSellerUseCase(SellerRepositoryPort sellerRepositoryPort,
                               UserRepositoryPort userRepositoryPort) {
        this.sellerRepositoryPort = sellerRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    public Seller execute(Seller seller) {
        // 1. Verificar que el User existe
        User user = userRepositoryPort.findById(seller.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + seller.getUserId()));

        // 2. Verificar que el User tiene rol VENDEDOR
        if (user.getRole() != Role.VENDEDOR) {
            throw new RuntimeException("El usuario no tiene rol VENDEDOR. Rol actual: " + user.getRole());
        }

        // 3. Enriquecer datos desde User automáticamente
        seller.setUserName(user.getName());
        seller.setUserEmail(user.getEmail());
        seller.setVendorCode(user.getVendorCode());

        return sellerRepositoryPort.save(seller);
    }
}