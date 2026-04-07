package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.OrderUseCase.CreateOrderUseCase;
import com.example.Back_Final_Analisis.application.usecase.OrderUseCase.GetAllOrdersUseCase;
import com.example.Back_Final_Analisis.domain.model.Order;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Controlador de Órdenes", description = "Gestión de pedidos y órdenes de compra")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetAllOrdersUseCase getAllOrdersUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getAllOrdersUseCase = getAllOrdersUseCase;
    }

    @Operation(summary = "Crear una nueva orden")
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        // La lógica de negocio establece el estado PENDIENTE y calcula totales
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrderUseCase.execute(order));
    }

    @Operation(summary = "Listar todas las órdenes")
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(getAllOrdersUseCase.execute());
    }
}