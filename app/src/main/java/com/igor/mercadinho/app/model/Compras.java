package com.igor.mercadinho.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "compras")
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produto;

    private int quantidadeItens;

    @Column(name = "data_compra")
    private LocalDateTime dataCompra = LocalDateTime.now();

    @Column(name = "valor_da_compra", nullable = false)
    private BigDecimal valorDaCompra;

    @Column(name = "descontos")
    private double descontosNaCompra;

    @Override
    public String toString() {
        return "Compras{" +
                "usuario=" + usuario +
                ", produto=" + produto +
                ", quantidadeItens=" + quantidadeItens +
                ", dataCompra=" + dataCompra +
                ", valorDaCompra=" + valorDaCompra +
                ", descontosNaCompra=" + descontosNaCompra +
                ", id=" + id +
                '}';
    }
}


