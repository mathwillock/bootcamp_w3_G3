package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
import com.bootcamp_w3_g3.repository.LoteRepository;
import com.bootcamp_w3_g3.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Criado teste unitário referente a classe ProdutosService.
 * Desenvolvido testes para o CRUD.
 * @author Alex Cruz
 */

public class ProdutoServiceUnitTest {

    ProdutoService produtoService;

    ProdutoRepository produtoRepository = Mockito.mock(ProdutoRepository.class);

    LoteService loteService = Mockito.mock(LoteService.class);


    Produto produto = Produto.builder()
            .codigoDoProduto(123)
            .nome("carne")
            .preco(60.0)
            .tipoProduto(TipoProduto.CONGELADOS)
            .build()
    ;

    Produto produto2 = Produto.builder()
            .codigoDoProduto(123)
            .nome("Arroz")
            .preco(60.0)
            .tipoProduto(TipoProduto.FRESCOS)
            .build()
    ;

    List<Produto> produtosList = new ArrayList<>();

    Lote lote = Lote.builder()
            .numero(10)
            .dataDeValidade(LocalDate.now())
            .produto(produto)
            .quantidadeAtual(5)
            .build()
    ;

    @Test
    void salvarTest(){
        Mockito.when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(produto);

        produtoService = new ProdutoService(produtoRepository);
        Produto salvo = produtoService.salvar(produto);

        Mockito.verify(produtoRepository, Mockito.times(1)).save(produto);

        assertNotNull(salvo);
    }

    @Test
    void obterTest(){
        Mockito.when(produtoRepository.findByCodigoDoProduto(Mockito.any(Integer.class))).thenReturn(produto);

        produtoService = new ProdutoService(produtoRepository);
        Produto obtido = produtoService.obter(produto.getCodigoDoProduto());

        Mockito.verify(produtoRepository,
                Mockito.times(1))
                .findByCodigoDoProduto(produto.getCodigoDoProduto());

        assertEquals(obtido.getPreco(), produto.getPreco());
    }


    @Test
    void obterLoteTest() {

        produto.setCodLote(lote.getNumero());

        Mockito.when(loteService.obter(Mockito.any(Integer.class))).thenReturn(lote);

        produtoService = new ProdutoService(produtoRepository, loteService);
        Lote loteEncontrado = produtoService.obterLote(produto.getCodLote());

        assertNotNull(loteEncontrado);
        assertEquals(lote.getNumero(), produto.getCodLote());

    }



    @Test
    void listarPorCategoriaTest(){
        produtosList.add(produto);
        produtosList.add(produto2);
        Mockito.when(produtoRepository.findAllByTipoProduto(Mockito.any(TipoProduto.class))).thenReturn(produtosList);

        produtoService = new ProdutoService(produtoRepository);
        List<Produto> tipoProdutoRetornado = produtoService.listarPorCategoria(produto.getTipoProduto());

        Mockito.verify(produtoRepository, Mockito.times(1)).findAllByTipoProduto(produto.getTipoProduto());

        assertEquals(tipoProdutoRetornado.size(),produtosList.size());
    }

    @Test
    void listarTest(){
        produtosList.add(produto);
        produtosList.add(produto2);
        Mockito.when(produtoRepository.findAll()).thenReturn(produtosList);

        produtoService = new ProdutoService(produtoRepository);
        List<Produto> lista = produtoService.listar();

        Mockito.verify(produtoRepository, Mockito.times(1)).findAll();

        assertEquals(lista.size(),produtosList.size());

    }

    @Test
    void atualizarTest(){
        produto.setPreco(15.05);
        produto.setTemperaturaIndicada(15.00);
        Mockito.when(produtoRepository.findByCodigoDoProduto(Mockito.any(Integer.class))).thenReturn(produto);
        Mockito.when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(produto);

        produtoService = new ProdutoService(produtoRepository);
        Produto atualizado = produtoService.atualizar(produto);

        Mockito.verify(produtoRepository, Mockito.times(1)).findByCodigoDoProduto(produto.getCodigoDoProduto());
        Mockito.verify(produtoRepository, Mockito.times(1)).save(produto);

        assertNotNull(atualizado);
        assertEquals(atualizado.getPreco(), produto.getPreco());

    }

    @Test
    void apagarTest(){
        produto.setCodigoDoProduto(23);
        Mockito.when(produtoRepository.deleteProdutosByCodigoDoProduto(Mockito.any(Integer.class))).thenReturn(produto);

        produtoService = new ProdutoService(produtoRepository);
        Produto deletado = produtoService.apagar(produto.getId());

        Mockito.verify(produtoRepository, Mockito.times(1)).deleteById(produto.getId());

        assertNotEquals(deletado, produto);
    }
}
