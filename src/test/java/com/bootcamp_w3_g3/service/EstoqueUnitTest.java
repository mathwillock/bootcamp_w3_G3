package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Estoque;
import com.bootcamp_w3_g3.repository.EstoqueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Marcelo de Oliveira Santos
 */
public class EstoqueUnitTest
{

    private final EstoqueRepository estoqueRepository = Mockito.mock(EstoqueRepository.class);


    @Autowired
    private Estoque estoque;
    private EstoqueService estoqueService;

    @Test
    void apagarEstoqueTest()
    {
        estoque.setCodEstoque(23);
        Mockito.when(estoqueRepository.deleteByCodEstoque(Mockito.any(Integer.class))).thenReturn(null);

        estoqueService = new EstoqueService(estoqueRepository);
        Estoque deletado = estoqueService.apagar(estoque.getCodEstoque());

        Mockito.verify(estoqueRepository, Mockito.times(1)).deleteByCodEstoque((estoque.getCodEstoque()));

        assertNotEquals(deletado,estoque);
    }



    @Test
    void atualizarEstoqueTest()
    {
        estoque.setQuantidade(23d);
        estoque.setCodEstoque(4);
        Mockito.when(estoqueRepository.getById(Mockito.any(Long.class))).thenReturn(estoque);
        Mockito.when(estoqueRepository.save(Mockito.any(Estoque.class))).thenReturn(estoque);

        estoqueService = new EstoqueService(estoqueRepository);
        Estoque atualizado = estoqueService.atualizar(estoque);

        Mockito.verify(estoqueRepository, Mockito.times(1)).getById(estoque.getId());
        Mockito.verify(estoqueRepository, Mockito.times(1)).save(estoque);

        assertNotNull(atualizado);
        assertEquals(atualizado.getQuantidade(), estoque.getQuantidade());
        assertEquals(atualizado.getTipoDeProduto(), estoque.getTipoDeProduto());


    }

    @Test
    void obterEstoqueTest()
    {

        Mockito.when(estoqueRepository.findByCodEstoque(Mockito.any(Integer.class))).thenReturn(estoque);
        estoqueService = new EstoqueService(estoqueRepository);

        estoque = estoqueService.obter(estoque.getCodEstoque());

        assertNotNull(estoque);
    }

    void salvarEstoqueTest()
    {
        Mockito.when(estoqueRepository.save(Mockito.any(Estoque.class))).thenReturn(estoque);

        estoqueService = new EstoqueService(estoqueRepository);
        Estoque salvo = estoqueService.salvar(estoque);

        Mockito.verify(estoqueRepository, Mockito.times(1)).save(estoque);

        assertNotNull(salvo);
    }

}
