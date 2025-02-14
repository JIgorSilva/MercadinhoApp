package com.igor.mercadinho.app.dtos.compras;

import com.igor.mercadinho.app.model.ItemCompra;

import java.math.BigDecimal;

public class ItemCompraDto {

    private Long id;
    private String nomeProduto;
    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    public ItemCompraDto(ItemCompra item) {
        this.id = item.getId();
        this.nomeProduto = item.getProduto().getNome();
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPrecoUnitario();
        this.subtotal = item.calcularSubtotal();
    }

    public Long getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}

