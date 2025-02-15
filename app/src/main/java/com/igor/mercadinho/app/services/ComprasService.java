package com.igor.mercadinho.app.services;

import com.igor.mercadinho.app.dtos.compras.ComprasDTO;
import com.igor.mercadinho.app.dtos.compras.ComprasDtoRequest;
import com.igor.mercadinho.app.model.Compras;
import com.igor.mercadinho.app.model.ItemCompra;
import com.igor.mercadinho.app.model.Produtos;
import com.igor.mercadinho.app.model.Usuario;
import com.igor.mercadinho.app.repository.ComprasRepository;
import com.igor.mercadinho.app.repository.ItemCompraRepository;
import com.igor.mercadinho.app.repository.ProdutoRepository;
import com.igor.mercadinho.app.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComprasService {

    private final ComprasRepository comprasRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemCompraRepository itemCompraRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ComprasService(
            ComprasRepository comprasRepository,
            ProdutoRepository produtoRepository,
            ItemCompraRepository itemCompraRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.comprasRepository = comprasRepository;
        this.produtoRepository = produtoRepository;
        this.itemCompraRepository = itemCompraRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Compras iniciarCompra(String emailUsuario, ComprasDtoRequest compraRequest) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Compras novaCompra = new Compras();
        novaCompra.setUsuario(usuario);
        novaCompra.setDataCompra(LocalDateTime.now());
        novaCompra.setValorDaCompra(BigDecimal.ZERO);
        novaCompra.setQuantidadeItens(0);

        novaCompra = comprasRepository.saveAndFlush(novaCompra);

        for (ComprasDTO itemDto : compraRequest.getItens()) {
            Produtos produto = produtoRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setProduto(produto);
            itemCompra.setQuantidade(itemDto.getQuantidade());
            itemCompra.setPrecoUnitario(produto.getPreco());

            adicionarItem(novaCompra.getId(), itemCompra);
        }

        atualizarValorTotal(novaCompra);
        atualizarQuantidadeItens(novaCompra);

        return comprasRepository.findById(novaCompra.getId())
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    public Compras adicionarItem(Long compraId, ItemCompra itemCompra) {
        Compras compra = comprasRepository.findById(compraId)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

        itemCompra.setCompra(compra);
        itemCompraRepository.save(itemCompra);

        compra.getItens().add(itemCompra);

        atualizarValorTotal(compra);
        atualizarQuantidadeItens(compra);

        return comprasRepository.save(compra);
    }

    public Compras removerItem(Long compraId, Long itemId) {
        ItemCompra item = itemCompraRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        Compras compra = item.getCompra();
        itemCompraRepository.delete(item);

        atualizarValorTotal(compra);
        return comprasRepository.save(compra);
    }

    private void atualizarValorTotal(Compras compra) {
        BigDecimal totalCompra = compra.getItens().stream()
                .map(ItemCompra::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        compra.setValorDaCompra(totalCompra);
        compra.setQuantidadeItens(compra.getItens().size());
    }

    public Compras salvarCompra(Compras compra) {
        for (ItemCompra item : compra.getItens()) {
            compra.adicionarItem(item); // Usa o método para adicionar itens
        }

        return comprasRepository.save(compra);
    }
    private void atualizarQuantidadeItens(Compras compra) {
        int quantidadeItens = compra.getItens().stream()
                .mapToInt(ItemCompra::getQuantidade)
                .sum();

        compra.setQuantidadeItens(quantidadeItens);
    }
}
