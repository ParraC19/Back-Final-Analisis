package com.example.Back_Final_Analisis.domain.port;

import com.example.Back_Final_Analisis.domain.model.Supervisor;

import java.util.List;
import java.util.Optional;

public interface SupervisorRepositoryPort {

    Supervisor save(Supervisor supervisor);

    Optional<Supervisor> findById(Long id);

    Optional<Supervisor> findByUserId(Long userId);

    List<Supervisor> findAll();

    void deleteById(Long id);
}