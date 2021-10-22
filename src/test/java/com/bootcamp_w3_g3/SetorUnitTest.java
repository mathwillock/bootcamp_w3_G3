package com.bootcamp_w3_g3;

import com.bootcamp_w3_g3.model.entity.Dimensao;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import com.bootcamp_w3_g3.repository.SetorRepository;
import com.bootcamp_w3_g3.service.SetorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hugo damm
 */

public class SetorUnitTest {

    private SetorService setorService;
    private SetorRepository setorRepository = Mockito.mock(SetorRepository.class);

    Setor setor1 = new Setor(123,"Setor123", "Frescos", 10.0, 20.0, new Dimensao(10.0,10.0,10.0), new Representante("João", "Paulo", "123.123.122-92", "21 9 9956-6538", "Rua A"));
    Setor setor2 = new Setor(124,"Setor124", "Congelados", 0.0, 5.0, new Dimensao(20.0,20.0,20.0), new Representante("Ernani", "Santos", "123.345.678-92", "11 9 7867-3456", "Rua B"));
    List<Setor> setorList = new ArrayList<>();

    @Test
    void salvarSetorTest(){

        Mockito.when(setorRepository.save(Mockito.any(Setor.class))).thenReturn(setor2);

        setorService = new SetorService(setorRepository);
        setorService.salvar(setor2);

        assertNotNull(setor2);
    }

    @Test
    void obterSetorTest(){
        setor1.setCodigo(123);
        Mockito.when(setorRepository.findByCodigo(Mockito.any(Integer.class))).thenReturn(setor1);

        setorService = new SetorService(setorRepository);
        Setor obtido = setorService.obter(setor1.getCodigo());

        Mockito.verify(setorRepository,
                Mockito.times(1)).findByCodigo(setor1.getCodigo());

        assertEquals(obtido.getCodigo(), setor1.getCodigo());
    }

    @Test
    void listarSetorTest(){
        setorList.add(setor1);
        setorList.add(setor2);

        Mockito.when(setorRepository.findAll()).thenReturn(setorList);

        setorService = new SetorService(setorRepository);
        List<Setor> lista = setorService.listar();

        Mockito.verify(setorRepository, Mockito.times(1)).findAll();

        assertEquals(lista.size(), setorList.size());

    }

    @Test
    void atualizarSetorTest(){
        setor1.setCodigo(1234);
        setor1.setNome("Setor1234");
        setor1.setTipoProduto("Frescos01");

        Mockito.when(setorRepository.findByCodigo(Mockito.any(Integer.class))).thenReturn(setor1);
        Mockito.when(setorRepository.save(Mockito.any(Setor.class))).thenReturn(setor1);

        setorService = new SetorService(setorRepository);
        Setor uptaded = setorService.atualizar(setor1);

        Mockito.verify(setorRepository, Mockito.times(1)).findByCodigo(setor1.getCodigo());
        Mockito.verify(setorRepository, Mockito.times(1)).save(setor1);

        assertNotNull(uptaded);
        assertEquals(uptaded.getCodigo(), setor1.getCodigo());

    }

}
