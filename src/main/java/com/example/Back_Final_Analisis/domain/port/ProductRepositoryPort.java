package com.example.Back_Final_Analisis.domain.port;

import com.example.Back_Final_Analisis.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
}
