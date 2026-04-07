package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.SaleUseCase.CreateSaleUseCase;
import com.example.Back_Final_Analisis.application.usecase.SaleUseCase.GetAllSalesUseCase;
import com.example.Back_Final_Analisis.application.usecase.SaleUseCase.GetSaleByIdUseCase;
import com.example.Back_Final_Analisis.domain.model.Sale;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Controlador de Ventas", description = "Operaciones relacionadas con el registro y consulta de ventas")
public class SaleController {

    private final CreateSaleUseCase createSaleUseCase;
    private final GetAllSalesUseCase getAllSalesUseCase;
    private final GetSaleByIdUseCase getSaleByIdUseCase;

    public SaleController(
            CreateSaleUseCase createSaleUseCase,
            GetAllSalesUseCase getAllSalesUseCase,
            GetSaleByIdUseCase getSaleByIdUseCase) {
        this.createSaleUseCase = createSaleUseCase;
        this.getAllSalesUseCase = getAllSalesUseCase;
        this.getSaleByIdUseCase = getSaleByIdUseCase;
    }

    @Operation(summary = "Registrar una nueva venta")
    @PostMapping
    public ResponseEntity<Sale> createSale(@Valid @RequestBody Sale sale) {
        // El caso de uso ya calcula totales y enriquece nombres de productos/vendedor
        return ResponseEntity.status(HttpStatus.CREATED).body(createSaleUseCase.execute(sale));
    }

    @Operation(summary = "Listar todas las ventas registradas")
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(getAllSalesUseCase.execute());
    }

    @Operation(summary = "Obtener detalle de una venta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(getSaleByIdUseCase.execute(id));
    }
}