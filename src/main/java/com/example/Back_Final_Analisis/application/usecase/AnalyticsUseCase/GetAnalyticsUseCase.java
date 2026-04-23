package com.example.Back_Final_Analisis.application.usecase.AnalyticsUseCase;

import com.example.Back_Final_Analisis.domain.model.Sale;
import com.example.Back_Final_Analisis.domain.port.SaleRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GetAnalyticsUseCase {

    private final SaleRepositoryPort saleRepositoryPort;

    public GetAnalyticsUseCase(SaleRepositoryPort saleRepositoryPort) {
        this.saleRepositoryPort = saleRepositoryPort;
    }

    /** Resumen general */
    public Map<String, Object> getResumen() {
        List<Sale> sales = saleRepositoryPort.findAll();
        double totalMonto = sales.stream().mapToDouble(Sale::getTotal).sum();
        long totalProductosVendidos = sales.stream()
                .flatMap(s -> s.getItems().stream())
                .mapToLong(Sale.SaleItem::getQuantity)
                .sum();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("totalVentas", sales.size());
        result.put("totalMonto", totalMonto);
        result.put("totalProductosVendidos", totalProductosVendidos);
        result.put("totalVendedores", sales.stream().map(Sale::getVendorId).distinct().count());
        return result;
    }

    /** Ventas agrupadas por vendedor */
    public List<Map<String, Object>> getVentasPorVendedor() {
        List<Sale> sales = saleRepositoryPort.findAll();

        return sales.stream()
                .collect(Collectors.groupingBy(Sale::getVendorId))
                .entrySet().stream()
                .map(entry -> {
                    List<Sale> vendorSales = entry.getValue();
                    Sale first = vendorSales.get(0);
                    double totalMonto = vendorSales.stream().mapToDouble(Sale::getTotal).sum();

                    Map<String, Object> row = new LinkedHashMap<>();
                    row.put("vendorId", entry.getKey());
                    row.put("vendorName", first.getVendorName());
                    row.put("vendorCode", first.getVendorCode());
                    row.put("totalVentas", vendorSales.size());
                    row.put("totalMonto", totalMonto);
                    return row;
                })
                .sorted(Comparator.comparingDouble(m -> -((Double) m.get("totalMonto"))))
                .collect(Collectors.toList());
    }

    /** Ventas agrupadas por mes (formato yyyy-MM) */
    public List<Map<String, Object>> getVentasPorMes() {
        List<Sale> sales = saleRepositoryPort.findAll();

        return sales.stream()
                .collect(Collectors.groupingBy(s ->
                        s.getSaleDate().getYear() + "-" + String.format("%02d", s.getSaleDate().getMonthValue())))
                .entrySet().stream()
                .map(entry -> {
                    List<Sale> monthlySales = entry.getValue();
                    double totalMonto = monthlySales.stream().mapToDouble(Sale::getTotal).sum();

                    Map<String, Object> row = new LinkedHashMap<>();
                    row.put("mes", entry.getKey());
                    row.put("totalVentas", monthlySales.size());
                    row.put("totalMonto", totalMonto);
                    return row;
                })
                .sorted(Comparator.comparing(m -> (String) m.get("mes")))
                .collect(Collectors.toList());
    }

    /** Productos más vendidos (por cantidad) */
    public List<Map<String, Object>> getProductosMasVendidos() {
        List<Sale> sales = saleRepositoryPort.findAll();

        return sales.stream()
                .flatMap(s -> s.getItems().stream())
                .collect(Collectors.groupingBy(Sale.SaleItem::getProductId))
                .entrySet().stream()
                .map(entry -> {
                    List<Sale.SaleItem> items = entry.getValue();
                    Sale.SaleItem first = items.get(0);
                    int cantidadTotal = items.stream().mapToInt(Sale.SaleItem::getQuantity).sum();
                    double montoTotal = items.stream().mapToDouble(Sale.SaleItem::getSubtotal).sum();

                    Map<String, Object> row = new LinkedHashMap<>();
                    row.put("productId", entry.getKey());
                    row.put("productName", first.getProductName());
                    row.put("cantidadVendida", cantidadTotal);
                    row.put("montoTotal", montoTotal);
                    return row;
                })
                .sorted(Comparator.comparingInt(m -> -((Integer) m.get("cantidadVendida"))))
                .collect(Collectors.toList());
    }
}
