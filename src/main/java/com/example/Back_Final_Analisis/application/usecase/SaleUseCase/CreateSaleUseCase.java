package com.example.Back_Final_Analisis.application.usecase.SaleUseCase;

import com.example.Back_Final_Analisis.domain.model.Product;
import com.example.Back_Final_Analisis.domain.model.Sale;
import com.example.Back_Final_Analisis.domain.model.User;
import com.example.Back_Final_Analisis.domain.port.ProductRepositoryPort;
import com.example.Back_Final_Analisis.domain.port.SaleRepositoryPort;
import com.example.Back_Final_Analisis.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateSaleUseCase {

    private final SaleRepositoryPort saleRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    public CreateSaleUseCase(SaleRepositoryPort saleRepositoryPort,
                             UserRepositoryPort userRepositoryPort,
                             ProductRepositoryPort productRepositoryPort) {
        this.saleRepositoryPort = saleRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
    }

    public Sale execute(Sale sale) {
        // Validar y enriquecer datos del vendedor
        User vendor = userRepositoryPort.findById(sale.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con id: " + sale.getVendorId()));

        sale.setVendorName(vendor.getName());
        sale.setVendorCode(vendor.getVendorCode());

        // Enriquecer items con precio, validar stock y calcular subtotales
        List<Sale.SaleItem> enrichedItems = sale.getItems().stream().map(item -> {
            Product product = productRepositoryPort.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + item.getProductId()));

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para el producto '" + product.getName()
                        + "'. Disponible: " + product.getStock() + ", solicitado: " + item.getQuantity());
            }

            item.setProductName(product.getName());
            item.setUnitPrice(product.getPrice());
            item.setSubtotal(product.getPrice() * item.getQuantity());

            // Descontar stock
            product.setStock(product.getStock() - item.getQuantity());
            productRepositoryPort.save(product);

            return item;
        }).toList();

        sale.setItems(enrichedItems);

        // Calcular total
        double total = enrichedItems.stream()
                .mapToDouble(Sale.SaleItem::getSubtotal)
                .sum();
        sale.setTotal(total);

        return saleRepositoryPort.save(sale);
    }
}