package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Itens;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
import com.bootcamp_w3_g3.repository.ItensRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Criado teste unitário referente a classe itensService.
 * Desenvolvido testes para o CRUD.
 * @author Alex Cruz
 */
public class ItensUnitTest{

    ItensService itensService;

    ItensRepository itensRepository = Mockito.mock(ItensRepository.class);

    Produto produto = Produto.builder()
            .codigoDoProduto(25)
            .nome("Batata Doce")
            .preco(12.69)
            .temperaturaIndicada(18.5)
            .tipoProduto(TipoProduto.FRESCOS)
            .build();

    Produto produto2 = Produto.builder()
            .codigoDoProduto(25)
            .nome("Fruta Pão")
            .preco(9.69)
            .temperaturaIndicada(12.5)
            .tipoProduto(TipoProduto.CONGELADOS)
            .build();
    Itens itens  = Itens.builder()
            .produto(produto)
            .quantidade(25)
            .build();

    Itens itens2  = Itens.builder()
            .produto(produto2)
            .quantidade(52)
            .build();

    List<Itens> itensList = new ArrayList<>();
    List<Itens> comboList = new ArrayList<>();

    Produto produto10 = Produto.builder()
            .codigoDoProduto(2002)
            .nome("Batata Doce")
            .preco(12.69)
            .temperaturaIndicada(18.5)
            .tipoProduto(TipoProduto.FRESCOS)
            .build();

    Produto produto20 = Produto.builder()
            .codigoDoProduto(2001)
            .nome("Fruta Pão")
            .preco(9.69)
            .temperaturaIndicada(12.5)
            .tipoProduto(TipoProduto.CONGELADOS)
            .build();

    Produto produto30 = Produto.builder()
            .codigoDoProduto(2003)
            .nome("Fruta Pão")
            .preco(9.69)
            .temperaturaIndicada(12.5)
            .tipoProduto(TipoProduto.CONGELADOS)
            .build();

    Produto produto40 = Produto.builder()
            .codigoDoProduto(10223)
            .nome("Fruta Pão")
            .preco(9.69)
            .temperaturaIndicada(12.5)
            .tipoProduto(TipoProduto.CONGELADOS)
            .build();

    Itens itens10  = Itens.builder()
            .produto(produto10)
            .quantidade(25)
            .build();

    Itens itens20  = Itens.builder()
            .produto(produto20)
            .quantidade(52)
            .build();

    Itens itens30  = Itens.builder()
            .produto(produto30)
            .quantidade(52)
            .build();

    Itens itens40  = Itens.builder()
            .produto(produto40)
            .quantidade(25)
            .build();


    @Test
    void salvarCarrinhoTest(){
        Mockito.when(itensRepository.save(Mockito.any(Itens.class))).thenReturn(itens);

        itensService = new ItensService(itensRepository);
        Itens salvo = itensService.salvar(itens);

        Mockito.verify(itensRepository, Mockito.times(1)).save(itens);

        assertNotNull(salvo);
    }

    @Test
    void listarCarrinhoTest(){
        itensList.add(itens);
        itensList.add(itens2);
        Mockito.when(itensRepository.findAll()).thenReturn(itensList);

        itensService = new ItensService(itensRepository);
        List<Itens> lista = itensService.listar();

        Mockito.verify(itensRepository, Mockito.times(1)).findAll();

        assertEquals(lista.size(),itensList.size());

    }

    @Test
    void entregarCombo(){
        comboList.add(itens20);
        comboList.add(itens30);
        comboList.add(itens10);
        comboList.add(itens40);

        Mockito.when(itensRepository.findAll()).thenReturn(comboList);


        itensService = new ItensService(itensRepository);
        List<Itens> lista = itensService.listarCombos("carne", "integral", "uva");

        assertNotEquals(comboList, lista);
        assertNotEquals(comboList.size(), lista.size());

    }

}
