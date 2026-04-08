package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity;

import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "supervisors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @Column(nullable = false, length = 50)
    private String documento;

    @ElementCollection
    @CollectionTable(name = "supervisor_tiendas", joinColumns = @JoinColumn(name = "supervisor_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "tienda", length = 30)
    private List<Tiendas> tiendasACargo;

    @Column(nullable = false)
    private LocalDate fechaDeNacimiento;

    @Column(nullable = false)
    private Double salarioBase;

    @Column(nullable = false)
    private LocalDate fechaIngreso;
}