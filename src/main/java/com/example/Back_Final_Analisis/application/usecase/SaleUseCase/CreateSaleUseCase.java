package com.example.Back_Final_Analisis.application.usecase.SaleUseCase;

import com.example.Back_Final_Analisis.domain.enums.SaleType;
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
        if (sale.getSaleType() == SaleType.LOCAL && sale.getTienda() == null) {
            throw new RuntimeException("La tienda es requerida para ventas LOCAL.");
        }
        if (sale.getSaleType() == SaleType.DOMICILIO &&
                (sale.getAddress() == null || sale.getAddress().isBlank())) {
            throw new RuntimeException("La dirección es requerida para ventas DOMICILIO.");
        }

        User vendor = userRepositoryPort.findById(sale.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado: " + sale.getVendorId()));
        sale.setVendorName(vendor.getName());
        sale.setVendorCode(vendor.getVendorCode());

        List<Sale.SaleItem> enriched = sale.getItems().stream().map(item -> {
            Product product = productRepositoryPort.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getProductId()));
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para: " + product.getName());
            }
            item.setProductName(product.getName());
            item.setUnitPrice(product.getPrice());
            item.setSubtotal(product.getPrice() * item.getQuantity());
            product.setStock(product.getStock() - item.getQuantity());
            productRepositoryPort.save(product);
            return item;
        }).toList();

        sale.setItems(enriched);
        sale.setTotal(enriched.stream().mapToDouble(Sale.SaleItem::getSubtotal).sum());
        return saleRepositoryPort.save(sale);
    }
}
