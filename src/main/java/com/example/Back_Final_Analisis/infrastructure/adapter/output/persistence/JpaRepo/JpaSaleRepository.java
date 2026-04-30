package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo;

import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSaleRepository extends JpaRepository<SaleEntity, Long> {
}
