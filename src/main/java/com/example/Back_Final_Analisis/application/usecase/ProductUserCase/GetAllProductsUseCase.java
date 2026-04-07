package com.example.Back_Final_Analisis.application.usecase.ProductUserCase;

import com.example.Back_Final_Analisis.domain.model.Product;
import com.example.Back_Final_Analisis.domain.port.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public GetAllProductsUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<Product> execute() {
        return productRepositoryPort.findAll();
    }
}