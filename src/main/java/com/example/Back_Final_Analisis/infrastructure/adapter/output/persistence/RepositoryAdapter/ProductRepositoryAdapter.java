package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.RepositoryAdapter;

import com.example.Back_Final_Analisis.domain.model.Product;
import com.example.Back_Final_Analisis.domain.port.ProductRepositoryPort;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.ProductEntity;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo.JpaProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Product save(Product product) {
        return toDomain(jpaProductRepository.save(toEntity(product)));
    }

    private Product toDomain(ProductEntity e) {
        return Product.builder()
                .id(e.getId()).name(e.getName()).brand(e.getBrand())
                .price(e.getPrice()).stock(e.getStock()).build();
    }

    private ProductEntity toEntity(Product p) {
        return ProductEntity.builder()
                .id(p.getId()).name(p.getName()).brand(p.getBrand())
                .price(p.getPrice()).stock(p.getStock()).build();
    }
}
