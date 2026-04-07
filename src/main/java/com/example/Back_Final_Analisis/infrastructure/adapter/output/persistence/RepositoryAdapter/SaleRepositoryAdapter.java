package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.RepositoryAdapter;

import com.example.Back_Final_Analisis.domain.model.Sale;
import com.example.Back_Final_Analisis.domain.port.SaleRepositoryPort;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.SaleEntity;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo.JpaSaleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SaleRepositoryAdapter implements SaleRepositoryPort {

    private final JpaSaleRepository jpaSaleRepository;

    public SaleRepositoryAdapter(JpaSaleRepository jpaSaleRepository) {
        this.jpaSaleRepository = jpaSaleRepository;
    }

    @Override
    public Sale save(Sale sale) {
        SaleEntity entity = mapToEntity(sale);
        SaleEntity saved = jpaSaleRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return jpaSaleRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Sale> findAll() {
        return jpaSaleRepository.findAll().stream().map(this::mapToDomain).toList();
    }

    @Override
    public List<Sale> findByVendorId(Long vendorId) {
        return jpaSaleRepository.findByVendorId(vendorId).stream().map(this::mapToDomain).toList();
    }

    private SaleEntity mapToEntity(Sale sale) {
        List<SaleEntity.SaleItemEmbeddable> itemEntities = sale.getItems().stream()
                .map(item -> SaleEntity.SaleItemEmbeddable.builder()
                        .productId(item.getProductId())
                        .productName(item.getProductName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .subtotal(item.getSubtotal())
                        .build())
                .toList();

        return SaleEntity.builder()
                .id(sale.getId())
                .vendorId(sale.getVendorId())
                .vendorName(sale.getVendorName())
                .vendorCode(sale.getVendorCode())
                .saleDate(sale.getSaleDate())
                .address(sale.getAddress())
                .items(itemEntities)
                .total(sale.getTotal())
                .build();
    }

    private Sale mapToDomain(SaleEntity entity) {
        List<Sale.SaleItem> items = entity.getItems().stream()
                .map(item -> Sale.SaleItem.builder()
                        .productId(item.getProductId())
                        .productName(item.getProductName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .subtotal(item.getSubtotal())
                        .build())
                .toList();

        return Sale.builder()
                .id(entity.getId())
                .vendorId(entity.getVendorId())
                .vendorName(entity.getVendorName())
                .vendorCode(entity.getVendorCode())
                .saleDate(entity.getSaleDate())
                .address(entity.getAddress())
                .items(items)
                .total(entity.getTotal())
                .build();
    }
}