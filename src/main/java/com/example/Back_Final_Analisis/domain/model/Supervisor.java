package com.example.Back_Final_Analisis.domain.model;

import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supervisor {

    private Long id;

    // Relación 1 a 1 con User
    @NotNull(message = "El usuario asociado es requerido")
    private Long userId;

    // Se rellena automáticamente desde User
    private String userName;
    private String userEmail;

    @NotBlank(message = "El documento es requerido")
    private String documento;

    // Un supervisor puede estar a cargo de múltiples tiendas
    @NotEmpty(message = "Debe asignarse al menos una tienda")
    private List<Tiendas> tiendasACargo;

    @NotNull(message = "La fecha de nacimiento es requerida")
    private LocalDate fechaDeNacimiento;

    @NotNull(message = "El salario base es requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "El salario debe ser mayor a 0")
    private Double salarioBase;

    @NotNull(message = "La fecha de ingreso es requerida")
    private LocalDate fechaIngreso;
}