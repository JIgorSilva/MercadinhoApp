package com.igor.mercadinho.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.mercadinho.app.exception.ProdutoResouceNotFoundException;
import com.igor.mercadinho.app.model.Produtos;
import com.igor.mercadinho.app.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("buscar")
    public ResponseEntity<List<Produtos>> listarProdutos() {
        List<Produtos> produtosLista = produtoRepository.findAll();
        return ResponseEntity.ok(produtosLista);
    }

    @PostMapping()
    public Produtos criarProduto(@RequestBody Produtos produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<Produtos> buscarProdutoPorId(@PathVariable int id) {
        Produtos produtos = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoResouceNotFoundException("ID do produto não existe " + id));
        return ResponseEntity.ok(produtos);
    }
    /*
     * criar metodo que retorna uma array list de todos os produtos contendo aquela
     * palavra, ignorando escrita
     */

}