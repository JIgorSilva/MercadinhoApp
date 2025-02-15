package com.igor.mercadinho.app.dtos.compras;

import java.util.List;

public class ComprasDtoRequest {
    private List<ComprasDTO> itens;

    public List<ComprasDTO> getItens() {
        return itens;
    }

    public void setItens(List<ComprasDTO> itens) {
        this.itens = itens;
    }
}
