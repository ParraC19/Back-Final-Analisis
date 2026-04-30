package com.example.Back_Final_Analisis.domain.port;

import com.example.Back_Final_Analisis.domain.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepositoryPort {
    Sale save(Sale sale);
    List<Sale> findAll();
    Optional<Sale> findById(Long id);
}
