package com.example.Back_Final_Analisis.application.usecase.OrderUseCase;

import com.example.Back_Final_Analisis.domain.model.Order;
import com.example.Back_Final_Analisis.domain.port.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllOrdersUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public GetAllOrdersUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    public List<Order> execute() {
        return orderRepositoryPort.findAll();
    }
}