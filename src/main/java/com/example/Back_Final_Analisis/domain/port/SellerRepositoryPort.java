package com.example.Back_Final_Analisis.domain.port;

import com.example.Back_Final_Analisis.domain.model.Seller;
import com.example.Back_Final_Analisis.domain.enums.Tiendas;

import java.util.List;
import java.util.Optional;

public interface SellerRepositoryPort {

    Seller save(Seller seller);

    Optional<Seller> findById(Long id);

    Optional<Seller> findByUserId(Long userId);

    List<Seller> findAll();

    List<Seller> findByTienda(Tiendas tienda);

    void deleteById(Long id);
}