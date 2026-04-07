package com.example.Back_Final_Analisis.domain.model;

import com.example.Back_Final_Analisis.domain.enums.OrderStatus;
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
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El vendedor es requerido")
    private Long vendorId;

    private String vendorName;

    @NotNull(message = "La fecha de la orden es requerida")
    private LocalDate orderDate;

    @NotEmpty(message = "La orden debe tener al menos un producto")
    private List<OrderItem> items;

    private Double total;

    private OrderStatus status;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {

        private Long productId;
        private String productName;

        @NotNull(message = "La cantidad es requerida")
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        private Integer quantity;

        private Double unitPrice;
        private Double subtotal;
    }
}