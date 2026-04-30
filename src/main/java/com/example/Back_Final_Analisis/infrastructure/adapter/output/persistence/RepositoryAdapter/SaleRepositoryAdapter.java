package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.RepositoryAdapter;

import com.example.Back_Final_Analisis.domain.model.Sale;
import com.example.Back_Final_Analisis.domain.port.SaleRepositoryPort;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.SaleEntity;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.SaleItemEmbeddable;
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
        return toDomain(jpaSaleRepository.save(toEntity(sale)));
    }

    @Override
    public List<Sale> findAll() {
        return jpaSaleRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return jpaSaleRepository.findById(id).map(this::toDomain);
    }

    private Sale toDomain(SaleEntity e) {
        List<Sale.SaleItem> items = e.getItems() == null ? List.of() :
                e.getItems().stream().map(i -> Sale.SaleItem.builder()
                        .productId(i.getProductId()).productName(i.getProductName())
                        .quantity(i.getQuantity()).unitPrice(i.getUnitPrice())
                        .subtotal(i.getSubtotal()).build()).toList();
        return Sale.builder()
                .id(e.getId()).vendorId(e.getVendorId()).vendorName(e.getVendorName())
                .vendorCode(e.getVendorCode()).saleType(e.getSaleType()).tienda(e.getTienda())
                .address(e.getAddress()).saleDate(e.getSaleDate()).items(items).total(e.getTotal()).build();
    }

    private SaleEntity toEntity(Sale s) {
        List<SaleItemEmbeddable> items = s.getItems() == null ? List.of() :
                s.getItems().stream().map(i -> SaleItemEmbeddable.builder()
                        .productId(i.getProductId()).productName(i.getProductName())
                        .quantity(i.getQuantity()).unitPrice(i.getUnitPrice())
                        .subtotal(i.getSubtotal()).build()).toList();
        return SaleEntity.builder()
                .id(s.getId()).vendorId(s.getVendorId()).vendorName(s.getVendorName())
                .vendorCode(s.getVendorCode()).saleType(s.getSaleType()).tienda(s.getTienda())
                .address(s.getAddress()).saleDate(s.getSaleDate()).items(items).total(s.getTotal()).build();
    }
}
