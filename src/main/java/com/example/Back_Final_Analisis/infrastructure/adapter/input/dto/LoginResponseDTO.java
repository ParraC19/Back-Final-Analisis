package com.example.Back_Final_Analisis.infrastructure.adapter.input.dto;

import com.example.Back_Final_Analisis.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String vendorCode;
    private Role role;
    private String message;
}
