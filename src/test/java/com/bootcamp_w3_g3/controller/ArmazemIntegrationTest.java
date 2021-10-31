package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.ArmazemForm;
import com.bootcamp_w3_g3.model.dtos.request.RepresentanteForm;
import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.service.ArmazemService;
import com.bootcamp_w3_g3.service.RepresentanteService;
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
 * @autor Joaquim Borges
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ArmazemIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArmazemService armazemService;

    @Autowired
    private RepresentanteService representanteService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private RepresentanteForm payloadRepresentante(){
        return RepresentanteForm.builder()
                .codigo("R-123")
                .nome("Joao")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private void persisteRepresentante(RepresentanteForm representanteForm) {
        Representante representante = Representante.builder()
                .nome(representanteForm.getNome())
                .sobrenome(representanteForm.getSobrenome())
                .cpf(representanteForm.getCpf())
                .telefone(representanteForm.getTelefone())
                .endereco(representanteForm.getEndereco())
                .codigo(representanteForm.getCodigo()).build();

        this.representanteService.salvar(representante);
    }

    private ArmazemForm payloadArmazem() {
        RepresentanteForm representanteForm = this.payloadRepresentante();
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-212")
                .nome("armazem central")
                .representanteForm(representanteForm)
                .endereco("qualquer lugar")
                .numero(100)
                .uf("SP").build();
    }

    private void persisteArmazem(ArmazemForm armazemForm, RepresentanteForm representanteForm) {

        this.persisteRepresentante(representanteForm);

        Representante representante = this.representanteService.obter(representanteForm.getCodigo());

        Armazem armazem = Armazem.builder()
                .codArmazem(armazemForm.getCodArmazem())
                .nome(armazemForm.getNome())
                .representante(representante)
                .endereco(armazemForm.getEndereco())
                .uf(armazemForm.getUf())
                .build();

        this.armazemService.criarArmazem(armazem);
    }




    /**
     * teste deve cadastrar um armazem se o payload estiver valido
     * e se o representante já estiver cadastrado no banco de dados
     */
    @Test
    void deveCadastrarUmArmazem() throws Exception {

        ArmazemForm armazem = this.payloadArmazem();
        String requestPayload = objectMapper.writeValueAsString(armazem);

        this.mockMvc.perform(post("http://localhost:8080/armazem/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isCreated());

    }


    /**
     * teste deve listar todos os armazens
     * cadastrados no banco de dados.
     */
    @Test
    void deveListarTodosOsArmazens() throws Exception {
        RepresentanteForm representante = this.payloadRepresentante();
        ArmazemForm armazem = ArmazemForm.builder()
                .codArmazem("AR-212")
                .nome("armazem central")
                .representanteForm(representante)
                .endereco("qualquer lugar")
                .numero(100)
                .uf("SP").build();

        this.persisteArmazem(armazem, representante);

        this.mockMvc.perform(get("http://localhost:8080/armazem/listar"))
                .andExpect(status().isOk());


    }


}
