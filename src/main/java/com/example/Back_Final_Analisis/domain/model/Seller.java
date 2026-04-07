package com.example.Back_Final_Analisis.domain.model;

import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 100, message = "El nombre no puedo exceder los 100 carácteres")
    private String name;

    @NotBlank(message = "El documento es requerido")
    @Column(name = "documento")
    private String documento;

    @NotBlank(message = "La sede del vendedor es requerida")
    @Column(name = "Tienda")
    @Enumerated(EnumType.STRING)
    private Tiendas tienda;

    @NotBlank(message = "La fecha de nacimiento es requerida")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaDeNacimiento;

    @NotBlank(message = "El salario base es requerido")
    @Column(name = "salario_base")
    private String salarioBase;

    @NotBlank(message = "La fecha de ingreso es requerida")
    @Column(name = "fecha_ingreso")
    private String fechaIngreso;

    
}
