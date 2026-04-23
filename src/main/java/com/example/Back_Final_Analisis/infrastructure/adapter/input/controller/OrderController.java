package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.OrderUseCase.CreateOrderUseCase;
import com.example.Back_Final_Analisis.application.usecase.OrderUseCase.GetAllOrdersUseCase;
import com.example.Back_Final_Analisis.application.usecase.OrderUseCase.GetOrderByIdUseCase;
import com.example.Back_Final_Analisis.application.usecase.OrderUseCase.UpdateOrderStatusUseCase;
import com.example.Back_Final_Analisis.domain.model.Order;
import com.example.Back_Final_Analisis.infrastructure.adapter.input.dto.UpdateOrderStatusRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Controlador de Órdenes", description = "Gestión de pedidos y órdenes de compra")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase,
                           GetAllOrdersUseCase getAllOrdersUseCase,
                           GetOrderByIdUseCase getOrderByIdUseCase,
                           UpdateOrderStatusUseCase updateOrderStatusUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getAllOrdersUseCase = getAllOrdersUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
    }

    @Operation(summary = "Crear una nueva orden")
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrderUseCase.execute(order));
    }

    @Operation(summary = "Listar todas las órdenes")
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(getAllOrdersUseCase.execute());
    }

    @Operation(summary = "Obtener detalle de una orden por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(getOrderByIdUseCase.execute(id));
    }

    @Operation(summary = "Actualizar estado de una orden", description = "Cambia el estado de PENDIENTE a CONFIRMADO o CANCELADO")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                   @Valid @RequestBody UpdateOrderStatusRequestDTO request) {
        return ResponseEntity.ok(updateOrderStatusUseCase.execute(id, request.getStatus()));
    }
}
