package com.igor.mercadinho.app.servicesTest;

import com.igor.mercadinho.app.model.Produtos;
import com.igor.mercadinho.app.repository.ProdutoRepository;
import com.igor.mercadinho.app.services.ProdutosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {

    @InjectMocks
    private ProdutosService produtosService;

    @Mock
    private ProdutoRepository produtoRepository;

    private Produtos produto; 
    private List<Produtos> produtos;

    @BeforeEach
    void setup() {
        
        MockitoAnnotations.openMocks(this);
        produtos = new ArrayList<>();

        produto = new Produtos();
        produto.setId(1L); // Produto sem ID
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição do Produto Teste");
        produto.setQuantidade(5);
        produto.setPreco(new BigDecimal("10.50"));

       Produtos produtos3 = new Produtos();
        produtos3.setId(3L); // Produto sem ID
        produtos3.setNome("Produto Teste3");
        produtos3.setDescricao("Descrição do Produto Teste3");
        produtos3.setQuantidade(67);
        produtos3.setPreco(new BigDecimal("17.50"));


        Produtos produto2 = new Produtos();
        produto2.setId(2L);
        produto2.setNome("Produto Teste2");
        produto2.setDescricao("Descrição do Produto Teste2");
        produto2.setQuantidade(10);
        produto2.setPreco(new BigDecimal("11.50"));

        produtos.add(produto);
        produtos.add(produto2);
        produtos.add(produtos3);
        
    }

    @Test
    void deveCriarProdutoQuandoForNull() {
        // Configuração do mock
        Produtos produtos1 = new Produtos();
        Mockito.when(produtoRepository.save(produtos1)).thenReturn(produto);
        

        // Ação
        Produtos resultado;
        resultado = produtosService.criarProduto(produtos1);

        // Verificações
        assertSame(produto,resultado);
         // não deve ser nulo
        assertEquals("Produto Teste", resultado.getNome()); // nome igual ao esperado
        verify(produtoRepository, times(1)).save(produtos1); // O save deve ser chamado
    }

    @Test
    void listarTodosProdutos(){
        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produtos>produtos = produtosService.listarTodosProdutos();

        assertNotNull(produtos);
        assertEquals(2, produtos.get(1).getId());
        assertEquals("Produto Teste", produtos.get(0).getNome());
        assertEquals("Produto Teste2", produtos.get(1).getNome());
    }

    @Test
    void buscaProdutoPorId() {
        // Configuração do mock para buscar pelo ID 1L
        Optional<Produtos> optionalProduto = Optional.of(produto);
        Mockito.when(produtoRepository.findById(1L)).thenReturn(optionalProduto);

        // Ação: buscar produto pelo ID usando o serviço
        Produtos produtoEncontrado = produtosService.buscarProdutoPorId(1L);

        // Verificações
        assertNotNull(produtoEncontrado); // Verifica se o produto não é nulo
        assertEquals(1L, produtoEncontrado.getId()); // Verifica se o ID está correto
        assertEquals("Produto Teste", produtoEncontrado.getNome()); // Nome do produto
        verify(produtoRepository, times(1)).findById(1L); // Garante que o método foi chamado
    }


}
