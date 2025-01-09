package com.igor.mercadinho.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.mercadinho.app.exception.ProductWithDifferentIdentifier;
import com.igor.mercadinho.app.exception.ProdutoAlreadyExistsException;
import com.igor.mercadinho.app.exception.ProdutoNameNotExistsException;
import com.igor.mercadinho.app.exception.ProdutoNameNotExistsException;
import com.igor.mercadinho.app.exception.ProdutoResouceNotFoundException;
import com.igor.mercadinho.app.model.Produtos;
import com.igor.mercadinho.app.repository.ProdutoRepository;

@Service
public class ProdutosService {
    @Autowired
    ProdutoRepository produtoRepository;

    public Produtos criarProduto(Produtos produto) {
        if (produto.getId() == null) {
            produtoRepository.save(produto);
        } else {
            new ProdutoAlreadyExistsException("Produto ja existe no estoque" + produto);
        }
        return produto;
    }

    public List<Produtos> listarTodosProdutos() {
        List<Produtos> listarProdutos = produtoRepository.findAll();
        return listarProdutos;
    }

    public Produtos buscarProdutoPorId(int id) {
        Produtos produtos = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoResouceNotFoundException("Produto não encontrado para o ID: " + id));

        return produtos;
    }

     public List<Produtos> buscarProdutoPorString(String referencia){
        referencia = referencia.toLowerCase();
        List<Produtos> listaProdutos = produtoRepository.findAll();
        List<Produtos> filtroProduto = new ArrayList<>();
        for(Produtos produto : listaProdutos){
            if(produto.getNome().toLowerCase().contains(referencia) || produto.getDescricao().toLowerCase().contains(referencia)){
                filtroProduto.add(produto);
            }
        }
        return filtroProduto;
    }

    public Produtos deletarProdutoPorIDcomNome(int id, String nomeProduto){
        Produtos produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoResouceNotFoundException("ID não existe: " + id));
        if(!produto.getNome().toLowerCase().equals(nomeProduto)){
            throw new ProdutoNameNotExistsException("O nome do produto não corresponde ao ID fornecido.");
        }
        
        produtoRepository.delete(produto);
        return produto;
        
    }

    public Produtos alterarProduto(int id, Produtos alteracoes){
        Produtos produto = produtoRepository.findById(id)
            .orElseThrow(() -> new ProdutoResouceNotFoundException("ID não existe: " + id));
        if(!alteracoes.getId().equals(produto.getId())){
            throw new ProductWithDifferentIdentifier("O ID do produto enviado não corresponde ao ID: " + id);
        }
        produto.setNome(alteracoes.getNome());
        produto.setDescricao(alteracoes.getDescricao());
        produto.setPreco(alteracoes.getPreco());
        produto.setQuantidade(alteracoes.getQuantidade());
        return produtoRepository.save(produto);
    }
}
