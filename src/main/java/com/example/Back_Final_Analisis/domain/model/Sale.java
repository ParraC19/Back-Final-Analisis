package com.example.Back_Final_Analisis.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El vendedor es requerido")
    private Long vendorId;

    // Se rellena automáticamente desde el User al consultar
    private String vendorName;
    private String vendorCode;

    @NotNull(message = "La fecha de venta es requerida")
    private LocalDate saleDate;

    @NotBlank(message = "La dirección es requerida")
    @Size(max = 200, message = "La dirección no puede exceder los 200 caracteres")
    private String address;

    @NotEmpty(message = "La venta debe tener al menos un producto")
    private List<SaleItem> items;

    private Double total;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaleItem {

        private Long productId;
        private String productName;

        @NotNull(message = "La cantidad es requerida")
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        private Integer quantity;

        private Double unitPrice;
        private Double subtotal;
    }
}