package com.example.Back_Final_Analisis.infrastructure.adapter.input.dto;

import com.example.Back_Final_Analisis.domain.enums.Role;
import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateUserRequestDTO {

    // ── Datos del User ────────────────────────────────────────────
    @NotBlank(message = "El nombre es requerido")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String name;

    @NotBlank(message = "El correo es requerido")
    @Email(message = "El formato del correo es inválido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres")
    private String password;

    @NotNull(message = "El rol es requerido")
    private Role role;

    // ── Datos del Seller (solo si role = VENDEDOR) ────────────────
    private SellerInfoDTO sellerInfo;

    // ── Datos del Supervisor (solo si role = SUPERVISOR) ──────────
    private SupervisorInfoDTO supervisorInfo;

    // ── DTOs internos ─────────────────────────────────────────────
    @Data
    public static class SellerInfoDTO {

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

    @Data
    public static class SupervisorInfoDTO {

        @NotBlank(message = "El documento es requerido")
        private String documento;

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
}
