package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo;

import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.SupervisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaSupervisorRepository extends JpaRepository<SupervisorEntity, Long> {
    Optional<SupervisorEntity> findByUser_Id(Long userId);
}