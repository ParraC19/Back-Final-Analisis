package com.example.Back_Final_Analisis.infrastructure.adapter.input.dto;

import com.example.Back_Final_Analisis.domain.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderStatusRequestDTO {

    @NotNull(message = "El nuevo estado es requerido")
    private OrderStatus status;
}
