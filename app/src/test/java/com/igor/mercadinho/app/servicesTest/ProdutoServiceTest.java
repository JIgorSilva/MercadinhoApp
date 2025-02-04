package com.igor.mercadinho.app.servicesTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.igor.mercadinho.app.exception.ProdutoResouceNotFoundException;
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

    @Test
    void buscarProdutoPorString() {
        //arrange
        Mockito.when(produtoRepository.findAll()).thenReturn(produtos);
        String referencia = "Produto Teste";
        //act
        List<Produtos> produtosReferencia = produtosService.buscarProdutoPorString(referencia);
        //assert
        assertNotNull(produtosReferencia);
        assertEquals(referencia, produtosReferencia.get(0).getNome());
        Assertions.assertSame(produtosReferencia.get(0),produtos.get(0));

    }

    @Test
    void deletarIdComNome(){
        Produtos produtoIdNome = new Produtos();
        produtoIdNome.setId(1L);
        produtoIdNome.setNome("coca");

        final int idProduto = produtoIdNome.getId().intValue();
        String nomeProdutoIncorrer = "nome incorreto";
        ProdutoResouceNotFoundException exceptionNomeIncorreto = assertThrows(ProdutoResouceNotFoundException.class,
                ()->produtosService.deletarProdutoPorIDcomNome(idProduto,nomeProdutoIncorrer));

        Mockito.when(produtoRepository.findById(produtoIdNome.getId().intValue())).thenReturn(Optional.of(produtoIdNome));
        produtoIdNome = produtosService.deletarProdutoPorIDcomNome(produtoIdNome.getId().intValue(),produtoIdNome.getNome());

        assertNotNull(produtoIdNome);
        assertEquals("coca",produtoIdNome.getNome());
        assertEquals("ID não existe: " + idProduto,exceptionNomeIncorreto.getMessage());

    }

    @Test
    void alterarProduto_eSalvar(){
        Produtos produtoAlterado = new Produtos();
        produtoAlterado.setId(1L);
        produtoAlterado.setNome("GG");
        produtoAlterado.setDescricao("mega");
        produtoAlterado.setQuantidade(13);
        produtoAlterado.setPreco(new BigDecimal("11.50"));
        produto.setId(1L);

        Mockito.when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));
        Mockito.when(produtoRepository.save(Mockito.any(Produtos.class))).thenReturn(produtoAlterado);

        produto = produtosService.alterarProduto(1,produtoAlterado);

        assertEquals(produtoAlterado,produto);
        assertNotNull(produtoAlterado);
        assertEquals(produtoAlterado.getNome(), produto.getNome());
        assertEquals(produtoAlterado.getDescricao(), produto.getDescricao());
        assertEquals(produtoAlterado.getQuantidade(), produto.getQuantidade());
        assertEquals(produtoAlterado.getPreco(), produto.getPreco());

    }
}
