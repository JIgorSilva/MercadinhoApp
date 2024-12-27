package com.igor.mercadinho.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igor.mercadinho.app.model.Produtos;
import com.igor.mercadinho.app.services.ProdutosService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutosService produtosService;

    @GetMapping("/lista")
    public ResponseEntity<List<Produtos>> listarProdutos() {
        List<Produtos> produtosLista = produtosService.listarTodosProdutos();
        return ResponseEntity.ok(produtosLista);
    }

    @PostMapping("criar/{produto}")
    public ResponseEntity<Produtos> criarProduto(@RequestBody Produtos produto) {
        produtosService.criarProduto(produto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("buscar/id")
    public ResponseEntity<Produtos> buscarProdutoPorId(@RequestParam int id) {
        Produtos produtos = produtosService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produtos);
    }
    /*
     * criar metodo que retorna uma array list de todos os produtos contendo aquela
     * palavra, ignorando escrita
     */

}