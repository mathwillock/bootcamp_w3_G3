package com.bootcamp_w3_g3;


import com.bootcamp_w3_g3.model.entity.Dimensao;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.repository.LoteRepository;

import com.bootcamp_w3_g3.service.LoteService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Joaquim Borges
 */

public class LoteUnitTest {

    @Autowired
    private LoteService loteService;

    @Mock
    private LoteRepository loteRepository;


    /**
     * teste deve salvar um lote
     */
//    @Test
//    void should_save_lote_whenPayloadIsValid(){
//        Lote lote = Lote.builder()
//                .id(1L)
//                .numero(10)
//                .dataDeValidade(LocalDate.now())
//                .dimensao(6.0)
//                .produtos(new ArrayList<>())
//                .quantidadeDeIntens(5)
//                .build();
//
//        Mockito.when(loteRepository.save(lote)).thenReturn(lote);
//        Assertions.assertThat(loteService.salvar(lote)).isEqualTo(lote);
//    }


}
