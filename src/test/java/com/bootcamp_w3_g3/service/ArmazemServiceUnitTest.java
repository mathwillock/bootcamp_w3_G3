package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import com.bootcamp_w3_g3.repository.ArmazemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArmazemServiceUnitTest {

    private ArmazemService armazemService;
    private final ArmazemRepository armazemRepository = Mockito.mock(ArmazemRepository.class);

    Representante representante = Representante.builder()
            .nome("Alex").sobrenome("Cruz").cpf("2345678910").telefone("5555555").endereco("Rua Joao neves 18").build()
    ;

    Armazem armazem1 = Armazem.builder()
            .codArmazem("Ar-123")
            .representante(representante)
            .nome("AR1")
            .endereco("rua 10")
            .uf("SP").build()
    ;

    Armazem armazem2= Armazem.builder()
            .codArmazem("Ar-123")
            .representante(representante)
            .nome("AR1")
            .endereco("rua 10")
            .uf("SP").build()
    ;


    @Test
    void salvarArmazemTest(){

        Mockito.when(armazemRepository.save(Mockito.any(Armazem.class))).thenReturn(armazem1);

        armazemService = new ArmazemService(armazemRepository);
        armazemService.criarArmazem(armazem1);

        assertNotNull(armazem1);
    }


    @Test
    void obterArmazemTest(){
        Mockito.when(armazemRepository.findByCodArmazem(Mockito.any(String.class))).thenReturn(armazem1);

        armazemService = new ArmazemService(armazemRepository);
        Armazem obtido = armazemService.obterArmazem(armazem1.getCodArmazem());

        Mockito.verify(armazemRepository,
                Mockito.times(1)).findByCodArmazem(armazem1.getCodArmazem());

        assertEquals(obtido.getCodArmazem(), armazem1.getCodArmazem());
    }


    @Test
    void deletarArmazemTest() {
        Mockito.when(armazemRepository.deleteByCodArmazem(Mockito.any(String.class))).thenReturn(null);

        armazemService = new ArmazemService(armazemRepository);
        Armazem armazemDeletado = armazemService.deletarArmazem(armazem1.getId());

        assertNull(armazemDeletado);

    }



}
