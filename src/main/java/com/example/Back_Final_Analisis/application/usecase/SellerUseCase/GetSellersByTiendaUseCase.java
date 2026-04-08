package com.example.Back_Final_Analisis.application.usecase.SellerUseCase;

import com.example.Back_Final_Analisis.domain.enums.Tiendas;
import com.example.Back_Final_Analisis.domain.model.Seller;
import com.example.Back_Final_Analisis.domain.port.SellerRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSellersByTiendaUseCase {

    private final SellerRepositoryPort sellerRepositoryPort;

    public GetSellersByTiendaUseCase(SellerRepositoryPort sellerRepositoryPort) {
        this.sellerRepositoryPort = sellerRepositoryPort;
    }

    public List<Seller> execute(Tiendas tienda) {
        return sellerRepositoryPort.findByTienda(tienda);
    }
}