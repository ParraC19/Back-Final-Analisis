package com.example.Back_Final_Analisis.application.usecase.OrderUseCase;

import com.example.Back_Final_Analisis.domain.enums.OrderStatus;
import com.example.Back_Final_Analisis.domain.model.Order;
import com.example.Back_Final_Analisis.domain.model.Product;
import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.OrderRepositoryPort;
import com.example.Back_Final_Analisis.domain.port.ProductRepositoryPort;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    public CreateOrderUseCase(OrderRepositoryPort orderRepositoryPort,
                              UserRepositoryPort userRepositoryPort,
                              ProductRepositoryPort productRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
    }

    public Order execute(Order order) {
        // Validar vendedor
        User vendor = userRepositoryPort.findById(order.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con id: " + order.getVendorId()));

        order.setVendorName(vendor.getName());
        order.setStatus(OrderStatus.PENDIENTE);

        // Enriquecer items
        List<Order.OrderItem> enrichedItems = order.getItems().stream().map(item -> {
            Product product = productRepositoryPort.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + item.getProductId()));

            item.setProductName(product.getName());
            item.setUnitPrice(product.getPrice());
            item.setSubtotal(product.getPrice() * item.getQuantity());
            return item;
        }).toList();

        order.setItems(enrichedItems);

        double total = enrichedItems.stream()
                .mapToDouble(Order.OrderItem::getSubtotal)
                .sum();
        order.setTotal(total);

        return orderRepositoryPort.save(order);
    }
}