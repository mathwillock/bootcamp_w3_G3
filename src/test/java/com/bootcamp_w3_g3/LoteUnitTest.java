package com.bootcamp_w3_g3;

import com.bootcamp_w3_g3.model.entity.Dimensao;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.repository.LoteRepository;
import com.bootcamp_w3_g3.service.LoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Joaquim Borges
 */

public class LoteUnitTest {

    private LoteService loteService;

    @Mock
    private LoteRepository loteRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        loteService = new LoteService(loteRepository);
    }


    /**
     * teste deve criar um lote caso o payload seja valido.
     */
    @Test
    void should_create_whenValidPayload(){
        Lote loteForm = new Lote(10, LocalDate.now(),
                new Dimensao(2.1, 1.1, 2.0), 10, new ArrayList<>());

        Mockito.when(loteService.obter(loteForm.getNumero()))
                .thenReturn(new Lote());

        loteService.salvar(loteForm);
        Mockito.verify(loteRepository).save(Mockito.any());
    }

}
