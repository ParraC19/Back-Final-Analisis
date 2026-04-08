package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.SellerUseCase.*;
import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import com.example.Back_Final_Analisis.domain.model.Seller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
@Tag(name = "Controlador de Vendedores", description = "Gestión de perfiles de vendedores")
public class SellerController {

    private final CreateSellerUseCase createSellerUseCase;
    private final GetAllSellersUseCase getAllSellersUseCase;
    private final GetSellerByIdUseCase getSellerByIdUseCase;
    private final GetSellersByTiendaUseCase getSellersByTiendaUseCase;

    public SellerController(CreateSellerUseCase createSellerUseCase,
                            GetAllSellersUseCase getAllSellersUseCase,
                            GetSellerByIdUseCase getSellerByIdUseCase,
                            GetSellersByTiendaUseCase getSellersByTiendaUseCase) {
        this.createSellerUseCase = createSellerUseCase;
        this.getAllSellersUseCase = getAllSellersUseCase;
        this.getSellerByIdUseCase = getSellerByIdUseCase;
        this.getSellersByTiendaUseCase = getSellersByTiendaUseCase;
    }

    @Operation(summary = "Registrar perfil de vendedor a un usuario existente")
    @PostMapping
    public ResponseEntity<Seller> createSeller(@Valid @RequestBody Seller seller) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createSellerUseCase.execute(seller));
    }

    @Operation(summary = "Listar todos los vendedores")
    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        return ResponseEntity.ok(getAllSellersUseCase.execute());
    }

    @Operation(summary = "Obtener vendedor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        return ResponseEntity.ok(getSellerByIdUseCase.execute(id));
    }

    @Operation(summary = "Obtener vendedores por tienda")
    @GetMapping("/tienda/{tienda}")
    public ResponseEntity<List<Seller>> getSellersByTienda(@PathVariable Tiendas tienda) {
        return ResponseEntity.ok(getSellersByTiendaUseCase.execute(tienda));
    }
}