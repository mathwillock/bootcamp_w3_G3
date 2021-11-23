package com.bootcamp_w3_g3.controller;


import com.bootcamp_w3_g3.model.dtos.request.CompradorForm;
import com.bootcamp_w3_g3.model.entity.Comprador;
import com.bootcamp_w3_g3.service.CompradorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


/**
 * @author Hugo Damm
 */

@SpringBootTest
@AutoConfigureMockMvc
public class CompradorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompradorService compradorService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private CompradorForm payloadComprador(){
        return CompradorForm.builder()
                .codigo("C-1")
                .nome("Gabriel")
                .sobrenome("Arrascaeta")
                .endereco("Rua do Flamengo Sempre Campeão")
                .cpf("12312312356")
                .telefone("21 3425-7856")
                .build();
    }

    private CompradorForm payloadComprador2(){
        return CompradorForm.builder()
                .codigo("C-2")
                .nome("Bruno")
                .sobrenome("Henrique")
                .endereco("Rua da Libertadores 2021")
                .cpf("12312312392")
                .telefone("21 3425-7856")
                .build();
    }

    private void persisteComprador(CompradorForm compradorForm){
        Comprador comprador = Comprador.builder()
                .codigo(compradorForm.getCodigo())
                .nome(compradorForm.getNome())
                .sobrenome(compradorForm.getSobrenome())
                .cpf(compradorForm.getCpf())
                .telefone(compradorForm.getTelefone())
                .endereco(compradorForm.getEndereco())
                .build();

        this.compradorService.salvar(comprador);
    }

    /**
     * teste deve salvar um Comprador se o
     * payload estiver valido.
     */
    @Test
    void deveSalvarUmComprador() throws Exception {
        CompradorForm comprador = this.payloadComprador();
        String requestPayload = objectMapper.writeValueAsString(comprador);

        this.mockMvc.perform(post("http://localhost:8080/comprador/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isCreated()
        );
    }

    @Test
    void deveAlterarDadosDoComprador() throws Exception {
        CompradorForm compradorForm = this.payloadComprador2();
        this.persisteComprador(compradorForm);

        CompradorForm compradorAlterado = CompradorForm.builder()
                .codigo("C-2")
                .nome("Paulo")
                .sobrenome("Henrique")
                .cpf("456.234.543-92")
                .telefone("21 3425-7856")
                .endereco("Rua da Libertadores 2021")
                .build();

        String requestPayload = objectMapper.writeValueAsString(compradorAlterado);
        this.mockMvc.perform(put("http://localhost:8080/comprador/alterar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(compradorAlterado.getNome())));
    }
}