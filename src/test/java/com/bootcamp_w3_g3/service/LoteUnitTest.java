package com.bootcamp_w3_g3.service;


import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.repository.LoteRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Joaquim Borges
 * @author Matheus Willock
 */

public class LoteUnitTest {
    @Autowired
    private LoteService loteService;

    private ProdutoService produtoService = Mockito.mock(ProdutoService.class);
    private final LoteRepository loteRepository = Mockito.mock(LoteRepository.class);

    Produto produto = Produto.builder()
            .codigoDoProduto(123)
            .nome("carne")
            .preco(60.0)
            .build()
    ;

    Lote lote = Lote.builder()
            .numero(10)
            .dataDeValidade(LocalDate.now())
            .produto(produto)
            .quantidadeAtual(5)
            .build()
    ;

    Lote lote2 = Lote.builder()
            .numero(9)
            .dataDeValidade(LocalDate.now())
            .produto(produto)
            .quantidadeAtual(5)
            .build()
    ;

    /**
     * teste deve salvar um lote
     */
   @Test
   void salvarLoteTest(){

        Mockito.when(loteRepository.save(Mockito.any(Lote.class))).thenReturn(lote);
        Mockito.when(produtoService.obter(Mockito.any(Integer.class))).thenReturn(produto);
        produto.setCodLote(lote.getNumero());
        Mockito.when(produtoService.atualizar(Mockito.any(Produto.class))).thenReturn(produto);

        loteService = new LoteService(loteRepository, produtoService);
        Lote loteSalvo = loteService.salvar(lote);

        assertNotNull(loteSalvo);
        assertEquals(loteSalvo,lote);
   }

   @Test
   void obterLoteTest(){
      Mockito.when(loteRepository.findByNumero(Mockito.any(Integer.class))).thenReturn(lote);

      loteService = new LoteService(loteRepository, produtoService);
      Lote getLote = loteService.obter(lote.getNumero());

      assertEquals(lote.getNumero(), getLote.getNumero());
   }


   @Test
   void obterTodosLotesTest(){
      List<Lote> lotes = new ArrayList<>();

      Mockito.when(loteRepository.findAll()).thenReturn(lotes);
      loteService = new LoteService(loteRepository, produtoService);

      lotes.add(lote);
      lotes.add(lote2);

      List<Lote> loteList = loteService.listar();

      assertEquals(loteList.size(), lotes.size());
   }

    @Test
    void atualizarLotetest() {

       lote.setNumero(20);
       lote.setQuantidadeAtual(2);

       Mockito.when(loteRepository.findByNumero(Mockito.any(Integer.class))).thenReturn(lote);
       Mockito.when(loteRepository.save(Mockito.any(Lote.class))).thenReturn(lote);

       loteService = new LoteService(loteRepository, produtoService);
       Lote loteUpdate = loteService.atualizar(lote);

       assertNotNull(lote);

    }



}
