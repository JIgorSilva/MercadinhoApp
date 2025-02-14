package com.igor.mercadinho.app.dtos.compras;

import com.igor.mercadinho.app.model.Compras;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ComprasDtoResponse {

    private Long id;
    private String nomeUsuario;
    private LocalDateTime dataCompra;
    private BigDecimal valorDaCompra;
    private double descontosNaCompra;
    private List<ItemCompraDto> itens;

    public ComprasDtoResponse(Compras compra) {
        this.id = compra.getId();
        this.nomeUsuario = compra.getUsuario().getNome();
        this.dataCompra = compra.getDataCompra();
        this.valorDaCompra = compra.getValorDaCompra();
        this.descontosNaCompra = compra.getDescontosNaCompra();
        this.itens = compra.getItens().stream()
                .map(ItemCompraDto::new) // Converte os itens para DTO
                .collect(Collectors.toList());

    }

    public Long getId() {
        return id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public BigDecimal getValorDaCompra() {
        return valorDaCompra;
    }

    public double getDescontosNaCompra() {
        return descontosNaCompra;
    }

    public List<ItemCompraDto> getItens() {
        return itens;
    }
}