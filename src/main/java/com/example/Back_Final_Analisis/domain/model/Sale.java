package com.example.Back_Final_Analisis.domain.model;

import com.example.Back_Final_Analisis.domain.enums.SaleType;
import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    private Long id;
    private Long vendorId;
    private String vendorName;
    private String vendorCode;
    private SaleType saleType;
    private Tiendas tienda;
    private String address;
    private LocalDate saleDate;
    private List<SaleItem> items;
    private Double total;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaleItem {
        private Long productId;
        private String productName;
        private Integer quantity;
        private Double unitPrice;
        private Double subtotal;
    }
}
