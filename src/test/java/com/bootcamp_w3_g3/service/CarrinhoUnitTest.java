package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Carrinho;
import com.bootcamp_w3_g3.model.entity.Comprador;
import com.bootcamp_w3_g3.repository.CarrinhoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Criado teste unitário referente a classe carrinhoService.
 * Desenvolvido testes para o CRUD.
 * @autor Alex Cruz
 */
public class CarrinhoUnitTest{

    CarrinhoService carrinhoService;

    CarrinhoRepository carrinhoRepository = Mockito.mock(CarrinhoRepository.class);

    Carrinho carrinho  = Carrinho.builder()
            .usuario("Alex")
            .senha("123456789")
            .build();

    Carrinho carrinho2  = Carrinho.builder()
            .usuario("Alex2")
            .senha("123456789")
            .build();

    List<Carrinho> carrinhoList = new ArrayList<>();

    @Test
    void salvarCarrinhoTest(){
        Mockito.when(carrinhoRepository.save(Mockito.any(Carrinho.class))).thenReturn(carrinho);

        carrinhoService = new CarrinhoService(carrinhoRepository);
        Carrinho salvo = carrinhoService.salvar(carrinho);

        Mockito.verify(carrinhoRepository, Mockito.times(1)).save(carrinho);

        assertNotNull(salvo);
    }

    @Test
    void listarCarrinhoTest(){
        carrinhoList.add(carrinho);
        carrinhoList.add(carrinho2);
        Mockito.when(carrinhoRepository.findAll()).thenReturn(carrinhoList);

        carrinhoService = new CarrinhoService(carrinhoRepository);
        List<Carrinho> lista = carrinhoService.listar();

        Mockito.verify(carrinhoRepository, Mockito.times(1)).findAll();

        assertEquals(lista.size(),carrinhoList.size());

    }

    @Test
    void atualizarCarrinhoTest(){
        carrinho.setId(22222L);
        carrinho.setCodigo("25");
        carrinho.setTelefone("777777777");
        carrinho.setEndereco("Rua A");
        Mockito.when(carrinhoRepository.getByCodigo(Mockito.any(String.class))).thenReturn(carrinho);
        Mockito.when(carrinhoRepository.save(Mockito.any(Carrinho.class))).thenReturn(carrinho);

        carrinhoService = new CarrinhoService(carrinhoRepository);
        Carrinho carrinhoAtualizado = carrinhoService.atualizar(carrinho);

        Mockito.verify(carrinhoRepository, Mockito.times(1)).getByCodigo(carrinho.getCodigo());
        Mockito.verify(carrinhoRepository, Mockito.times(1)).save(carrinho);

        assertNotNull(carrinhoAtualizado);
        assertEquals(carrinhoAtualizado.getUsuario(), carrinho.getUsuario()getTelefone());

    }
}

