package com.igor.mercadinho.app.model;

import com.igor.mercadinho.app.exception.CompraInvalidaItensNullException;
import com.igor.mercadinho.app.exception.global.CompraInvalidadeNotFoundPrecoUnitario;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemCompra> itens = new ArrayList<>();

    private int quantidadeItens;

    @Column(name = "data_compra")
    private LocalDateTime dataCompra = LocalDateTime.now();

    @Column(name = "valor_da_compra", nullable = false)
    private BigDecimal valorDaCompra;

    @Column(name = "descontos")
    private double descontosNaCompra;
    public  Compras(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompra> itens) {
        this.itens = itens;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public BigDecimal getValorDaCompra() {
        return valorDaCompra;
    }

    public void setValorDaCompra(BigDecimal valorDaCompra) {
        this.valorDaCompra = valorDaCompra;
    }

    public double getDescontosNaCompra() {
        return descontosNaCompra;
    }

    public void setDescontosNaCompra(double descontosNaCompra) {
        this.descontosNaCompra = descontosNaCompra;
    }

    public Compras(Long id, Usuario usuario, List<ItemCompra> itens, int quantidadeItens, LocalDateTime dataCompra, BigDecimal valorDaCompra, double descontosNaCompra) {
        this.id = id;
        this.usuario = usuario;
        this.itens = itens;
        this.quantidadeItens = quantidadeItens;
        this.dataCompra = dataCompra;
        this.valorDaCompra = valorDaCompra;
        this.descontosNaCompra = descontosNaCompra;
    }

    public void adicionarItem(ItemCompra item) {
        item.setCompra(this);
        this.itens.add(item);
    }
    /*
    public void removerItem(ItemCompra item) {
        this.itens.remove(item);
        item.setCompra(null);
    }*/
    public BigDecimal descontoDaCompra(int quantidadeItens, BigDecimal precoOriginal, BigDecimal precoEnviado) {
        // preço total com o preço original
        BigDecimal totalOriginal = precoOriginal.multiply(BigDecimal.valueOf(quantidadeItens));

        // preço total com o preço json
        BigDecimal totalEnviado = precoEnviado.multiply(BigDecimal.valueOf(quantidadeItens));

        // desconto
        BigDecimal desconto = totalOriginal.subtract(totalEnviado);

        // Converte e armazena
        this.descontosNaCompra = desconto.doubleValue();

        return desconto;
    }

    public void validarQuantidadeDeItensNaCompra() {
        if (itens == null || itens.isEmpty()) {
            throw new CompraInvalidaItensNullException("A compra deve conter pelo menos um item.");
        }

        for (ItemCompra item : itens) {
            if (item.getQuantidade() <= 0) {
                throw new CompraInvalidaItensNullException("Ação invalida, não existem itens na compra");
            }
        }
    }

    public BigDecimal validaPrecoDaCompra(BigDecimal preco) {
        BigDecimal precounitario = new BigDecimal(0);
        for (ItemCompra item : itens) {
            if (item.getPrecoUnitario().intValue() <= 0) {
                throw new CompraInvalidadeNotFoundPrecoUnitario("Ação invalida, não existe preço no produto.");
            }
            precounitario = item.getPrecoUnitario();
        }
        return precounitario;
    }
}


