package com.igor.mercadinho.app.servicesTest;

import com.igor.mercadinho.app.exception.ProdutoResouceNotFoundException;
import com.igor.mercadinho.app.model.Produtos;
import com.igor.mercadinho.app.repository.ProdutoRepository;
import com.igor.mercadinho.app.services.ProdutosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void buscarProdutoPorId(){
        Produtos produtos1 = produtos.get(1);
        int idProduto = 1;
        produtos1.setId((long)idProduto);
        when(produtoRepository.findById(idProduto)).thenReturn(Optional.of(produtos1));


        Produtos produtosResponse = produtosService.buscarProdutoPorId(idProduto);
        RuntimeException produtoNaoExiste = assertThrows(ProdutoResouceNotFoundException.class,()->{
            produtosService.buscarProdutoPorId(3);
        });

        assertNotNull(produtosResponse);
        assertEquals(1,produtosResponse.getId());
        assertEquals("Produto não encontrado para o ID: "+3,produtoNaoExiste.getMessage());
        verify(produtoRepository, times(1)).findById(idProduto);

    }
}
