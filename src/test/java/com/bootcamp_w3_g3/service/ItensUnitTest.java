package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Carrinho;
import com.bootcamp_w3_g3.model.entity.Itens;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.repository.CarrinhoRepository;
import com.bootcamp_w3_g3.repository.ItensRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Criado teste unitário referente a classe itensService.
 * Desenvolvido testes para o CRUD.
 * @autor Alex Cruz
 */
public class ItensUnitTest{

    ItensService itensService;

    ItensRepository itensRepository = Mockito.mock(ItensRepository.class);

    Itens itens  = Itens.builder()
            .produto(new Produto())
            .quantidade(25)
            .build();

    Itens itens2  = Itens.builder()
            .produto(new Produto())
            .quantidade(52)
            .build();

    List<Itens> itensList = new ArrayList<>();

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
    void atualizarCarrinhoTest(){
        itens.setId(22222L);
        itens.setCodigo("25");
        itens.setTelefone("777777777");
        itens.setEndereco("Rua A");
        Mockito.when(itensRepository.findByUsuario(Mockito.any(String.class))).thenReturn(itens);
        Mockito.when(itensRepository.save(Mockito.any(Itens.class))).thenReturn(itens);

        itensService = new ItensService(itensRepository);
        Itens carrinhoAtualizado = itensService.atualizar(itens);

        Mockito.verify(itensRepository, Mockito.times(1)).findByUsuario(itens.getCodigo());
        Mockito.verify(itensRepository, Mockito.times(1)).save(itens);

        assertNotNull(carrinhoAtualizado);
        assertEquals(carrinhoAtualizado.getUsuario(), carrinho.getUsuario()getTelefone());

    }
}
