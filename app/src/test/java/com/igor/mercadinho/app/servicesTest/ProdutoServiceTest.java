package com.igor.mercadinho.app.servicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.igor.mercadinho.app.model.Produtos;
import com.igor.mercadinho.app.repository.ProdutoRepository;
import com.igor.mercadinho.app.services.ProdutosService;

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
        produto.setId(null); // Produto sem ID
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição do Produto Teste");
        produto.setQuantidade(5);
        produto.setPreco(new BigDecimal("10.50"));

        Produtos produto2 = new Produtos();
        produto2.setId(null); 
        produto2.setNome("Produto Teste2");
        produto2.setDescricao("Descrição do Produto Teste2");
        produto2.setQuantidade(10);
        produto2.setPreco(new BigDecimal("11.50"));

        produtos.add(produto);
        produtos.add(produto2);
        
    }

    @Test
    void deveCriarProdutoQuandoForNull() {
        // Configuração do mock
        when(produtoRepository.save(produto)).thenReturn(produto);
        

        // Ação
        Produtos resultado = produtosService.criarProduto(produto);

        // Verificações
        assertNotNull(resultado); // não deve ser nulo
        assertEquals("Produto Teste", resultado.getNome()); // nome igual ao esperado
        verify(produtoRepository, times(1)).save(produto); // O save deve ser chamado
    }

    @Test
    void listarTodosProdutos(){
        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produtos>produtos = produtosService.listarTodosProdutos();

        assertNotNull(produtos);
        assertEquals(2, produtos.size());
        assertEquals("Produto Teste", produtos.get(0).getNome());
        assertEquals("Produto Teste2", produtos.get(1).getNome());
    }
}
