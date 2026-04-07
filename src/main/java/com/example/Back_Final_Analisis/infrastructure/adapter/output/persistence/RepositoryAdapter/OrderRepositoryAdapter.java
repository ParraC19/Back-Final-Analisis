package com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.RepositoryAdapter;

import com.example.Back_Final_Analisis.domain.model.Order;
import com.example.Back_Final_Analisis.domain.port.OrderRepositoryPort;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.Entity.OrderEntity;
import com.example.Back_Final_Analisis.infrastructure.adapter.output.persistence.JpaRepo.JpaOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final JpaOrderRepository jpaOrderRepository;

    public OrderRepositoryAdapter(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = mapToEntity(order);
        OrderEntity saved = jpaOrderRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaOrderRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Order> findAll() {
        return jpaOrderRepository.findAll().stream().map(this::mapToDomain).toList();
    }

    @Override
    public List<Order> findByVendorId(Long vendorId) {
        return jpaOrderRepository.findByVendorId(vendorId).stream().map(this::mapToDomain).toList();
    }

    private OrderEntity mapToEntity(Order order) {
        List<OrderEntity.OrderItemEmbeddable> itemEntities = order.getItems().stream()
                .map(item -> OrderEntity.OrderItemEmbeddable.builder()
                        .productId(item.getProductId())
                        .productName(item.getProductName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .subtotal(item.getSubtotal())
                        .build())
                .toList();

        return OrderEntity.builder()
                .id(order.getId())
                .vendorId(order.getVendorId())
                .vendorName(order.getVendorName())
                .orderDate(order.getOrderDate())
                .items(itemEntities)
                .total(order.getTotal())
                .status(order.getStatus())
                .build();
    }

    private Order mapToDomain(OrderEntity entity) {
        List<Order.OrderItem> items = entity.getItems().stream()
                .map(item -> Order.OrderItem.builder()
                        .productId(item.getProductId())
                        .productName(item.getProductName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .subtotal(item.getSubtotal())
                        .build())
                .toList();

        return Order.builder()
                .id(entity.getId())
                .vendorId(entity.getVendorId())
                .vendorName(entity.getVendorName())
                .orderDate(entity.getOrderDate())
                .items(items)
                .total(entity.getTotal())
                .status(entity.getStatus())
                .build();
    }
}
