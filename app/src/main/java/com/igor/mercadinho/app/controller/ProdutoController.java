package com.igor.mercadinho.app.controller;

import java.util.List;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igor.mercadinho.app.exception.ProductWithDifferentIdentifier;
import com.igor.mercadinho.app.exception.ProdutoNameNotExistsException;
import com.igor.mercadinho.app.exception.ProdutoResouceNotFoundException;
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

    @GetMapping("buscar/produtos")
    public ResponseEntity<List<Produtos>> buscarProdutoPorString(@RequestParam String ref){
        List<Produtos> produtos = produtosService.buscarProdutoPorString(ref);
        if(produtos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtos);
    }

   
    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Produtos> deletarProdutoPorIDcomNome(@PathVariable int id, String nomeProduto){
        try{
            Produtos produto = produtosService.deletarProdutoPorIDcomNome(id, nomeProduto);
            return ResponseEntity.ok(produto);
        }
        catch(ProdutoResouceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch(ProdutoNameNotExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        
    }

    @PutMapping("alterar/{id}")
    public ResponseEntity<Produtos> alterarProduto(@PathVariable int id, @RequestBody Produtos alteracoesDoProduto){
        try{
            Produtos produto = produtosService.alterarProduto(id, alteracoesDoProduto);
            return ResponseEntity.ok(produto);
        }
        catch(ProdutoResouceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch(ProductWithDifferentIdentifier e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("buscar/preco/{preco}")
    public ResponseEntity<List<Produtos>> buscarProdutoPreco(@PathVariable BigDecimal preco){
        try{
            List<Produtos> produto = produtosService.buscarProdutoPreco(preco);
            return ResponseEntity.ok(produto);  
        }
        catch(ProdutoResouceNotFoundException e){
            return ResponseEntity.status(204).body(null);
        }
    }
}
