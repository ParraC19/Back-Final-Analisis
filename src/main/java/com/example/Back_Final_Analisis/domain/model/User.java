package com.example.Back_Final_Analisis.domain.model;

import com.example.Back_Final_Analisis.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 100, message = "El nombre no puedo exceder los 100 carácteres")
    private String name;

    @NotBlank(message = "El correo es requerido")
    @Email(message = "El formato del correo es inválido")
    @Size(max = 100, message = "El correo no puede exceder los 100 carácteres")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 6, max = 20, message = "La contraseña tiene que tener entre 6 y carácteres")
    private String password;

    @NotNull(message = "El rol es requerido")
    private Role role;
}