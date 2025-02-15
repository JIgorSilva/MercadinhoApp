package com.igor.mercadinho.app.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.mercadinho.app.model.ItemCompra;
import com.igor.mercadinho.app.repository.ItemCompraRepository;

@Service
public class ItemCompraService {

    @Autowired
    private ItemCompraRepository itemCompraRepository;


    public BigDecimal calcularSubtotal(Long itemId) {
        ItemCompra itemCompra = itemCompraRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        return itemCompra.calcularSubtotal();
    }

    public BigDecimal calcularValorTotal(Long compraId) {
        List<ItemCompra> itens = itemCompraRepository.findByCompraId(compraId);

        return itens.stream()
                .map(ItemCompra::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
