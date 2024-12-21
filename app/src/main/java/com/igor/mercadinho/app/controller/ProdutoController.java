package com.igor.mercadinho.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.mercadinho.app.model.Produtos;
import com.igor.mercadinho.app.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produtos> listarProdutos() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public Produtos criarProduto(@RequestBody Produtos produto) {
        return produtoRepository.save(produto);
    }
}
