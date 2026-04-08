package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo;

import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaSellerRepository extends JpaRepository<SellerEntity, Long> {
    Optional<SellerEntity> findByUser_Id(Long userId);
    List<SellerEntity> findByTienda(Tiendas tienda);
}