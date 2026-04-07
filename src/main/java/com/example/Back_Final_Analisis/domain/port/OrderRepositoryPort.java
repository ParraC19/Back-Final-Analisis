package com.example.Back_Final_Analisis.domain.port;

import com.example.Back_Final_Analisis.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {

    Order save(Order order);

    List<Order> findAll();

    Optional<Order> findById(Long id);

    List<Order> findByVendorId(Long vendorId);
}