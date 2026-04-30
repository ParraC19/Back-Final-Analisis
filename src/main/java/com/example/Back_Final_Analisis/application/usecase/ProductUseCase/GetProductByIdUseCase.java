package com.example.Back_Final_Analisis.application.usecase.ProductUseCase;

import com.example.Back_Final_Analisis.domain.model.Product;
import com.example.Back_Final_Analisis.domain.port.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public GetProductByIdUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public Product execute(Long id) {
        return productRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
}
