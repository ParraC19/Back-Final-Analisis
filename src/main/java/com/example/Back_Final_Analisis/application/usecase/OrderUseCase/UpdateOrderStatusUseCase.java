package com.example.Back_Final_Analisis.application.usecase.OrderUseCase;

import com.example.Back_Final_Analisis.domain.enums.OrderStatus;
import com.example.Back_Final_Analisis.domain.model.Order;
import com.example.Back_Final_Analisis.domain.port.OrderRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderStatusUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public UpdateOrderStatusUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    public Order execute(Long orderId, OrderStatus newStatus) {
        Order order = orderRepositoryPort.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + orderId));

        if (order.getStatus() != OrderStatus.PENDIENTE) {
            throw new RuntimeException("Solo se pueden actualizar órdenes en estado PENDIENTE. Estado actual: " + order.getStatus());
        }

        if (newStatus != OrderStatus.CONFIRMADO && newStatus != OrderStatus.CANCELADO) {
            throw new RuntimeException("El nuevo estado debe ser CONFIRMADO o CANCELADO.");
        }

        order.setStatus(newStatus);
        return orderRepositoryPort.save(order);
    }
}
