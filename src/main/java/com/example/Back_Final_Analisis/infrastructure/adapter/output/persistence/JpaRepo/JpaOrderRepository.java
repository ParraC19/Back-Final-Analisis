package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo;

import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByVendorId(Long vendorId);
}