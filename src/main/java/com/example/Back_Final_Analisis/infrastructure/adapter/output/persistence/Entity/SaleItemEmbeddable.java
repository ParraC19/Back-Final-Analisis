package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemEmbeddable {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
}
