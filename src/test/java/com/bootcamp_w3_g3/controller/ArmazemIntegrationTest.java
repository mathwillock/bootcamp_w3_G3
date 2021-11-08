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
                .codigo("R-4")
                .nome("Marcelo")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante2(){
        return RepresentanteForm.builder()
                .codigo("R-5")
                .nome("Hugo")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante3(){
        return RepresentanteForm.builder()
                .codigo("R-6")
                .nome("Alex")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante4(){
        return RepresentanteForm.builder()
                .codigo("R-7")
                .nome("Matheus")
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

    private ArmazemForm payloadArmazem(ArmazemForm armazemForm, RepresentanteForm representanteForm) {
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem(armazemForm.getCodArmazem())
                .nome(armazemForm.getNome())
                .representante(representanteForm)
                .endereco(armazemForm.getEndereco())
                .numero(armazemForm.getNumero())
                .uf(armazemForm.getUf()).build();
    }

    private void persisteArmazem(ArmazemForm armazemForm) {
        Representante representante = this.representanteService.obter(armazemForm.getRepresentante().getCodigo());

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
        RepresentanteForm representanteForm = payloadRepresentante();
        this.persisteRepresentante(representanteForm);

        ArmazemForm armazemForm = ArmazemForm.builder()
                .codArmazem("A-8").nome("central").endereco("rua qualquer")
                .uf("RJ").numero(3).representante(representanteForm).build();

        String requestPayload = objectMapper.writeValueAsString(armazemForm);


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
        RepresentanteForm representante = this.payloadRepresentante2();
        this.persisteRepresentante(representante);

        ArmazemForm armazemForm = ArmazemForm.builder()
                .codArmazem("A-9").nome("central").endereco("rua qualquer")
                .uf("RJ").numero(3).representante(representante).build();

        this.persisteArmazem(armazemForm);

        this.mockMvc.perform(get("http://localhost:8080/armazem/listar"))
                .andExpect(status().isOk());


    }


    @Test
    void deveObterUmArmazem() throws Exception {
        RepresentanteForm representanteForm = this.payloadRepresentante3();
        this.persisteRepresentante(representanteForm);

        ArmazemForm armazemForm = ArmazemForm.builder()
                .codArmazem("A-10").nome("central").endereco("rua qualquer")
                .uf("RJ").numero(3).representante(representanteForm).build();

        this.persisteArmazem(armazemForm);
        this.mockMvc.perform(get("http://localhost:8080/armazem/obter/" + armazemForm.getCodArmazem()))
                .andExpect(status().isOk());
    }


    @Test
    void deveAtualizarDadosDoArmazem() throws Exception {
        RepresentanteForm representanteForm = this.payloadRepresentante4();
        this.persisteRepresentante(representanteForm);

        ArmazemForm armazemForm = ArmazemForm.builder()
                .codArmazem("A-11").nome("central").endereco("rua qualquer")
                .uf("RJ").numero(3).representante(representanteForm).build();

        this.persisteArmazem(armazemForm);

        ArmazemForm armazemAlterado = ArmazemForm.builder()
                .codArmazem("A-11").nome("oposto").endereco("rua qualquer")
                .uf("SP").numero(3).representante(representanteForm).build();

        String requestPayload = objectMapper.writeValueAsString(armazemAlterado);
        this.mockMvc.perform(put("http://localhost:8080/armazem/atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(armazemAlterado.getNome())));


    }


}
