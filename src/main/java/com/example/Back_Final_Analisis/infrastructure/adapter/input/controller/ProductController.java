package com.example.Back_Final_Analisis.infrastructure.adapter.input.controller;

import com.example.Back_Final_Analisis.application.usecase.ProductUseCase.GetAllProductsUseCase;
import com.example.Back_Final_Analisis.application.usecase.ProductUseCase.GetProductByIdUseCase;
import com.example.Back_Final_Analisis.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Productos", description = "Catálogo de productos")
public class ProductController {

    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;

    public ProductController(GetAllProductsUseCase getAllProductsUseCase,
                             GetProductByIdUseCase getProductByIdUseCase) {
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
    }

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(getAllProductsUseCase.execute());
    }

    @Operation(summary = "Obtener producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getProductByIdUseCase.execute(id));
    }
}
