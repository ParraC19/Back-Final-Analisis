package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo;

import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
