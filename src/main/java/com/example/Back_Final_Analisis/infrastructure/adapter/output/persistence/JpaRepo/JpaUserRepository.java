package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo;

import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByVendorCode(String vendorCode);
}