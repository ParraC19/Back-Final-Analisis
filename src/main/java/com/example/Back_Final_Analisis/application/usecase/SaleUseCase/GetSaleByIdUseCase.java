package com.example.Back_Final_Analisis.application.usecase.SaleUseCase;

import com.example.Back_Final_Analisis.domain.model.Sale;
import com.example.Back_Final_Analisis.domain.port.SaleRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetSaleByIdUseCase {

    private final SaleRepositoryPort saleRepositoryPort;

    public GetSaleByIdUseCase(SaleRepositoryPort saleRepositoryPort) {
        this.saleRepositoryPort = saleRepositoryPort;
    }

    public Sale execute(Long id) {
        return saleRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));
    }
}