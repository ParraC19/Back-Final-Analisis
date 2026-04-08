package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.RepositoryAdapter;

import com.example.Back_Final_Analisis.domain.model.Supervisor;
import com.example.Back_Final_Analisis.domain.port.SupervisorRepositoryPort;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.SupervisorEntity;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.UserEntity;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo.JpaSupervisorRepository;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SupervisorRepositoryAdapter implements SupervisorRepositoryPort {

    private final JpaSupervisorRepository jpaSupervisorRepository;
    private final JpaUserRepository jpaUserRepository;

    public SupervisorRepositoryAdapter(JpaSupervisorRepository jpaSupervisorRepository,
                                       JpaUserRepository jpaUserRepository) {
        this.jpaSupervisorRepository = jpaSupervisorRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Supervisor save(Supervisor supervisor) {
        UserEntity userEntity = jpaUserRepository.findById(supervisor.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        SupervisorEntity entity = mapToEntity(supervisor, userEntity);
        return mapToDomain(jpaSupervisorRepository.save(entity));
    }

    @Override
    public Optional<Supervisor> findById(Long id) {
        return jpaSupervisorRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public Optional<Supervisor> findByUserId(Long userId) {
        return jpaSupervisorRepository.findByUser_Id(userId).map(this::mapToDomain);
    }

    @Override
    public List<Supervisor> findAll() {
        return jpaSupervisorRepository.findAll().stream().map(this::mapToDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaSupervisorRepository.deleteById(id);
    }

    private SupervisorEntity mapToEntity(Supervisor supervisor, UserEntity userEntity) {
        return SupervisorEntity.builder()
                .id(supervisor.getId())
                .user(userEntity)
                .documento(supervisor.getDocumento())
                .tiendasACargo(supervisor.getTiendasACargo())
                .fechaDeNacimiento(supervisor.getFechaDeNacimiento())
                .salarioBase(supervisor.getSalarioBase())
                .fechaIngreso(supervisor.getFechaIngreso())
                .build();
    }

    private Supervisor mapToDomain(SupervisorEntity entity) {
        UserEntity user = entity.getUser();

        return Supervisor.builder()
                .id(entity.getId())
                .userId(user != null ? user.getId() : null)
                .userName(user != null ? user.getName() : null)
                .userEmail(user != null ? user.getEmail() : null)
                .documento(entity.getDocumento())
                .tiendasACargo(entity.getTiendasACargo())
                .fechaDeNacimiento(entity.getFechaDeNacimiento())
                .salarioBase(entity.getSalarioBase())
                .fechaIngreso(entity.getFechaIngreso())
                .build();
    }
}