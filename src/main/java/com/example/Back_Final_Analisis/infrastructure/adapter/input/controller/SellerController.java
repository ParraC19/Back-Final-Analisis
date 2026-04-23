package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.SellerUseCase.GetAllSellersUseCase;
import com.example.Back_Final_Analisis.application.usecase.SellerUseCase.GetSellerByIdUseCase;
import com.example.Back_Final_Analisis.application.usecase.SellerUseCase.GetSellersByTiendaUseCase;
import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import com.example.Back_Final_Analisis.domain.model.Seller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
@Tag(name = "Controlador de Vendedores", description = "Gestión de perfiles de vendedores")
public class SellerController {

    private final GetAllSellersUseCase getAllSellersUseCase;
    private final GetSellerByIdUseCase getSellerByIdUseCase;
    private final GetSellersByTiendaUseCase getSellersByTiendaUseCase;

    public SellerController(GetAllSellersUseCase getAllSellersUseCase,
                            GetSellerByIdUseCase getSellerByIdUseCase,
                            GetSellersByTiendaUseCase getSellersByTiendaUseCase) {
        this.getAllSellersUseCase = getAllSellersUseCase;
        this.getSellerByIdUseCase = getSellerByIdUseCase;
        this.getSellersByTiendaUseCase = getSellersByTiendaUseCase;
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
