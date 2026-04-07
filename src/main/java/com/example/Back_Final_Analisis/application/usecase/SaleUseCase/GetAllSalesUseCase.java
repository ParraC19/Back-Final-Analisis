package com.example.Back_Final_Analisis.application.usecase.SaleUseCase;

import com.example.Back_Final_Analisis.domain.model.Sale;
import com.example.Back_Final_Analisis.domain.port.SaleRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllSalesUseCase {

    private final SaleRepositoryPort saleRepositoryPort;

    public GetAllSalesUseCase(SaleRepositoryPort saleRepositoryPort) {
        this.saleRepositoryPort = saleRepositoryPort;
    }

    public List<Sale> execute() {
        return saleRepositoryPort.findAll();
    }
}