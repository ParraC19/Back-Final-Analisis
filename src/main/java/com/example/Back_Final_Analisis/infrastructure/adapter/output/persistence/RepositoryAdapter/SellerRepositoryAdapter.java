package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.RepositoryAdapter;

import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import com.example.Back_Final_Analisis.domain.model.Seller;
import com.example.Back_Final_Analisis.domain.port.SellerRepositoryPort;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.SellerEntity;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.UserEntity;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo.JpaSellerRepository;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SellerRepositoryAdapter implements SellerRepositoryPort {

    private final JpaSellerRepository jpaSellerRepository;
    private final JpaUserRepository jpaUserRepository;

    public SellerRepositoryAdapter(JpaSellerRepository jpaSellerRepository,
                                   JpaUserRepository jpaUserRepository) {
        this.jpaSellerRepository = jpaSellerRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Seller save(Seller seller) {
        UserEntity userEntity = jpaUserRepository.findById(seller.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + seller.getUserId()));
        SellerEntity entity = toEntity(seller, userEntity);
        return toDomain(jpaSellerRepository.save(entity));
    }

    @Override
    public Optional<Seller> findById(Long id) {
        return jpaSellerRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Seller> findByUserId(Long userId) {
        return jpaSellerRepository.findByUser_Id(userId).map(this::toDomain);
    }

    @Override
    public List<Seller> findAll() {
        return jpaSellerRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaSellerRepository.deleteById(id);
    }

    @Override
    public List<Seller> findByTienda(Tiendas tienda) {
        return jpaSellerRepository.findByTienda(tienda)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    // ─── Mapper Entity → Domain ───────────────────────────────────────────────

    private Seller toDomain(SellerEntity e) {
        return Seller.builder()
                .id(e.getId())
                .userId(e.getUser().getId())
                .userName(e.getUser().getName())
                .userEmail(e.getUser().getEmail())
                .vendorCode(e.getUser().getVendorCode())
                .documento(e.getDocumento())
                .tienda(e.getTienda())
                .fechaDeNacimiento(e.getFechaDeNacimiento())
                .salarioBase(e.getSalarioBase())
                .fechaIngreso(e.getFechaIngreso())
                .build();
    }

    // ─── Mapper Domain → Entity ───────────────────────────────────────────────

    private SellerEntity toEntity(Seller s, UserEntity userEntity) {
        return SellerEntity.builder()
                .id(s.getId())
                .user(userEntity)
                .documento(s.getDocumento())
                .tienda(s.getTienda())
                .fechaDeNacimiento(s.getFechaDeNacimiento())
                .salarioBase(s.getSalarioBase())
                .fechaIngreso(s.getFechaIngreso())
                .build();
    }
}