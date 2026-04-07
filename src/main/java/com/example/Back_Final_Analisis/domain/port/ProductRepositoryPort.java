package com.example.Back_Final_Analisis.domain.port;

import com.example.Back_Final_Analisis.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findByBrand(String brand);

    void deleteById(Long id);
}