package com.example.Back_Final_Analisis.domain.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private Integer stock;
}
