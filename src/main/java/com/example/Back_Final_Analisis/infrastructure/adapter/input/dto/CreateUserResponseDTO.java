package com.example.Back_Final_Analisis.infrastructure.adapter.input.dto;

import com.example.Back_Final_Analisis.domain.enums.Role;
import com.example.Back_Final_Analisis.domain.model.Seller;
import com.example.Back_Final_Analisis.domain.model.Supervisor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateUserResponseDTO {

    private Long userId;
    private String name;
    private String email;
    private String vendorCode;
    private Role role;

    // Solo viene relleno si role = VENDEDOR
    private Seller seller;

    // Solo viene relleno si role = SUPERVISOR
    private Supervisor supervisor;
}
