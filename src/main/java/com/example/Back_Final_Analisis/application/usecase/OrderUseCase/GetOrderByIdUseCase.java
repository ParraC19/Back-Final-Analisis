package com.example.Back_Final_Analisis.application.usecase.OrderUseCase;

import com.example.Back_Final_Analisis.domain.model.Order;
import com.example.Back_Final_Analisis.domain.port.OrderRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetOrderByIdUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public GetOrderByIdUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    public Order execute(Long id) {
        return orderRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }
}