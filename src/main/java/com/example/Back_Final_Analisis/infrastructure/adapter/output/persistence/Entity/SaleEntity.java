package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity;

import com.example.Back_Final_Analisis.domain.enums.SaleType;
import com.example.Back_Final_Analisis.domain.enums.Tiendas;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SaleType saleType;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Tiendas tienda;

    @Column(length = 200)
    private String address;

    @Column(nullable = false)
    private LocalDate saleDate;

    @ElementCollection
    @CollectionTable(name = "sale_items", joinColumns = @JoinColumn(name = "sale_id"))
    private List<SaleItemEmbeddable> items;

    @Column(nullable = false)
    private Double total;
}
