package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.AnalyticsUseCase.GetAnalyticsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@Tag(name = "Analytics", description = "Estadísticas de ventas para el dashboard")
public class AnalyticsController {

    private final GetAnalyticsUseCase getAnalyticsUseCase;

    public AnalyticsController(GetAnalyticsUseCase getAnalyticsUseCase) {
        this.getAnalyticsUseCase = getAnalyticsUseCase;
    }

    @Operation(summary = "Resumen general", description = "Total de ventas, monto total, productos vendidos y vendedores activos")
    @GetMapping("/resumen")
    public ResponseEntity<Map<String, Object>> getResumen() {
        return ResponseEntity.ok(getAnalyticsUseCase.getResumen());
    }

    @Operation(summary = "Ventas por vendedor", description = "Agrupadas por vendedor con total de ventas y monto, ordenadas de mayor a menor monto")
    @GetMapping("/ventas-por-vendedor")
    public ResponseEntity<List<Map<String, Object>>> getVentasPorVendedor() {
        return ResponseEntity.ok(getAnalyticsUseCase.getVentasPorVendedor());
    }

    @Operation(summary = "Ventas por mes", description = "Agrupadas por mes (yyyy-MM) con total de ventas y monto")
    @GetMapping("/ventas-por-mes")
    public ResponseEntity<List<Map<String, Object>>> getVentasPorMes() {
        return ResponseEntity.ok(getAnalyticsUseCase.getVentasPorMes());
    }

    @Operation(summary = "Productos más vendidos", description = "Ordenados por cantidad vendida de mayor a menor")
    @GetMapping("/productos-mas-vendidos")
    public ResponseEntity<List<Map<String, Object>>> getProductosMasVendidos() {
        return ResponseEntity.ok(getAnalyticsUseCase.getProductosMasVendidos());
    }
}
