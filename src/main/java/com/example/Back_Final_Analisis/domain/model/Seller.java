package com.example.Back_Final_Analisis.domain.model;

import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación 1 a 1 con User (para login)
    @NotNull(message = "El usuario asociado es requerido")
    private Long userId;

    // Se rellena automáticamente desde User
    private String userName;
    private String userEmail;
    private String vendorCode;

    @NotBlank(message = "El documento es requerido")
    private String documento;

    @NotNull(message = "La tienda es requerida")
    private Tiendas tienda;

    @NotNull(message = "La fecha de nacimiento es requerida")
    private LocalDate fechaDeNacimiento;

    @NotNull(message = "El salario base es requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "El salario debe ser mayor a 0")
    private Double salarioBase;

    @NotNull(message = "La fecha de ingreso es requerida")
    private LocalDate fechaIngreso;
}