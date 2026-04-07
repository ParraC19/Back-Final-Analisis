package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long vendorId;

    @Column(length = 100)
    private String vendorName;

    @Column(length = 20)
    private String vendorCode;

    @Column(nullable = false)
    private LocalDate saleDate;

    @Column(nullable = false, length = 200)
    private String address;

    @ElementCollection
    @CollectionTable(name = "sale_items", joinColumns = @JoinColumn(name = "sale_id"))
    private List<SaleItemEmbeddable> items;

    @Column(nullable = false)
    private Double total;

    @Embeddable
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaleItemEmbeddable {

        private Long productId;

        @Column(length = 100)
        private String productName;

        private Integer quantity;
        private Double unitPrice;
        private Double subtotal;
    }
}