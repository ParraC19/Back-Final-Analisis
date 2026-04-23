package com.example.Back_Final_Analisis.application.usecase.UserUseCase;

import com.example.Back_Final_Analisis.domain.enums.Role;
import com.example.Back_Final_Analisis.domain.model.Seller;
import com.example.Back_Final_Analisis.domain.model.Supervisor;
import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.SellerRepositoryPort;
import com.example.Back_Final_Analisis.domain.port.SupervisorRepositoryPort;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import com.example.Back_Final_Analisis.infrastructure.adapter.input.dto.CreateUserRequestDTO;
import com.example.Back_Final_Analisis.infrastructure.adapter.input.dto.CreateUserResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final SellerRepositoryPort sellerRepositoryPort;
    private final SupervisorRepositoryPort supervisorRepositoryPort;
    private final BCryptPasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserRepositoryPort userRepositoryPort,
                             SellerRepositoryPort sellerRepositoryPort,
                             SupervisorRepositoryPort supervisorRepositoryPort,
                             BCryptPasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.sellerRepositoryPort = sellerRepositoryPort;
        this.supervisorRepositoryPort = supervisorRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponseDTO execute(CreateUserRequestDTO request) {

        // Verificar email único
        if (userRepositoryPort.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario registrado con ese correo.");
        }

        // Validar que venga el perfil correspondiente al rol
        if (request.getRole() == Role.VENDEDOR && request.getSellerInfo() == null) {
            throw new RuntimeException("Debes proporcionar 'sellerInfo' para el rol VENDEDOR.");
        }
        if (request.getRole() == Role.SUPERVISOR && request.getSupervisorInfo() == null) {
            throw new RuntimeException("Debes proporcionar 'supervisorInfo' para el rol SUPERVISOR.");
        }

        // 1. Crear el User (sin vendorCode aún, se genera tras obtener el ID)
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User savedUser = userRepositoryPort.save(user);

        // Generar vendorCode automático basado en el ID
        String prefix = request.getRole() == Role.VENDEDOR ? "VEN-" : "SUP-";
        String autoCode = prefix + savedUser.getId();
        savedUser.setVendorCode(autoCode);
        savedUser = userRepositoryPort.save(savedUser);

        // 2. Crear perfil según rol
        Seller savedSeller = null;
        Supervisor savedSupervisor = null;

        if (savedUser.getRole() == Role.VENDEDOR) {
            CreateUserRequestDTO.SellerInfoDTO info = request.getSellerInfo();
            Seller seller = Seller.builder()
                    .userId(savedUser.getId())
                    .userName(savedUser.getName())
                    .userEmail(savedUser.getEmail())
                    .vendorCode(savedUser.getVendorCode())
                    .documento(info.getDocumento())
                    .tienda(info.getTienda())
                    .fechaDeNacimiento(info.getFechaDeNacimiento())
                    .salarioBase(info.getSalarioBase())
                    .fechaIngreso(info.getFechaIngreso())
                    .build();
            savedSeller = sellerRepositoryPort.save(seller);
        }

        if (savedUser.getRole() == Role.SUPERVISOR) {
            CreateUserRequestDTO.SupervisorInfoDTO info = request.getSupervisorInfo();
            Supervisor supervisor = Supervisor.builder()
                    .userId(savedUser.getId())
                    .userName(savedUser.getName())
                    .userEmail(savedUser.getEmail())
                    .documento(info.getDocumento())
                    .tiendasACargo(info.getTiendasACargo())
                    .fechaDeNacimiento(info.getFechaDeNacimiento())
                    .salarioBase(info.getSalarioBase())
                    .fechaIngreso(info.getFechaIngreso())
                    .build();
            savedSupervisor = supervisorRepositoryPort.save(supervisor);
        }

        return CreateUserResponseDTO.builder()
                .userId(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .vendorCode(savedUser.getVendorCode())
                .role(savedUser.getRole())
                .seller(savedSeller)
                .supervisor(savedSupervisor)
                .build();
    }
}
