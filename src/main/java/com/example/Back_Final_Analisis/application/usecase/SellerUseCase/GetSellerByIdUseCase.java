package com.example.Back_Final_Analisis.application.usecase.SellerUseCase;

import com.example.Back_Final_Analisis.domain.model.Seller;
import com.example.Back_Final_Analisis.domain.port.SellerRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetSellerByIdUseCase {

    private final SellerRepositoryPort sellerRepositoryPort;

    public GetSellerByIdUseCase(SellerRepositoryPort sellerRepositoryPort) {
        this.sellerRepositoryPort = sellerRepositoryPort;
    }

    public Seller execute(Long id) {
        return sellerRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con id: " + id));
    }
}