package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.SaleUseCase.CreateSaleUseCase;
import com.example.Back_Final_Analisis.application.usecase.SaleUseCase.GetAllSalesUseCase;
import com.example.Back_Final_Analisis.application.usecase.SaleUseCase.GetSaleByIdUseCase;
import com.example.Back_Final_Analisis.domain.model.Sale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Ventas", description = "Registro y consulta de ventas")
public class SaleController {

    private final CreateSaleUseCase createSaleUseCase;
    private final GetAllSalesUseCase getAllSalesUseCase;
    private final GetSaleByIdUseCase getSaleByIdUseCase;

    public SaleController(CreateSaleUseCase createSaleUseCase,
                          GetAllSalesUseCase getAllSalesUseCase,
                          GetSaleByIdUseCase getSaleByIdUseCase) {
        this.createSaleUseCase = createSaleUseCase;
        this.getAllSalesUseCase = getAllSalesUseCase;
        this.getSaleByIdUseCase = getSaleByIdUseCase;
    }

    @Operation(summary = "Registrar una venta")
    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createSaleUseCase.execute(sale));
    }

    @Operation(summary = "Listar todas las ventas")
    @GetMapping
    public ResponseEntity<List<Sale>> getAll() {
        return ResponseEntity.ok(getAllSalesUseCase.execute());
    }

    @Operation(summary = "Obtener venta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getSaleByIdUseCase.execute(id));
    }
}
