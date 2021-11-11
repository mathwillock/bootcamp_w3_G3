package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.RepresentanteForm;
import com.bootcamp_w3_g3.model.entity.Representante;
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

import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

/**
 * @autor Joaquim Borges
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RepresentanteIntTest {

    @Autowired
    private MockMvc mockMvc;

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
                .codigo("R-1")
                .nome("Pedro")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante2(){
        return RepresentanteForm.builder()
                .codigo("R-2")
                .nome("Joao")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante3(){
        return RepresentanteForm.builder()
                .codigo("R-3")
                .nome("Paulo")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante4(){
        return RepresentanteForm.builder()
                .codigo("R-4")
                .nome("Alex")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante5(){
        return RepresentanteForm.builder()
                .codigo("R-60")
                .nome("Alexia")
                .sobrenome("Gomez")
                .endereco("rua morundinumoraninguem")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private Representante converte(RepresentanteForm representanteForm){
        return Representante.builder()
                .nome(representanteForm.getNome())
                .sobrenome(representanteForm.getSobrenome())
                .cpf(representanteForm.getCpf())
                .telefone(representanteForm.getTelefone())
                .endereco(representanteForm.getEndereco())
                .codigo(representanteForm.getCodigo()).build();
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

    /**
     * teste deve salvar um representante se o
     * payload estiver valido.
     */

    @Test
    void deveSalvarRepresentante() throws Exception {
        RepresentanteForm representante = this.payloadRepresentante();
        String requestPayload = objectMapper.writeValueAsString(representante);

        this.mockMvc.perform(post("http://localhost:8080/representante/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isCreated());
    }

    /**
     * teste deve obter um representante caso dele
     * exista no banco de dados.
     */

    @Test
    void deveObterUmRepresentante() throws Exception {
        RepresentanteForm representanteForm = this.payloadRepresentante2();
        this.persisteRepresentante(representanteForm);

        this.mockMvc.perform(get("http://localhost:8080/representante/obter/" + representanteForm.getCodigo()))
                .andExpect(status().isOk());
    }

    /**
     * teste deve alterar os dados do representante caso o
     * payload seja valido e tenha o mesmo codigo de registro
     */

    @Test
    void deveAlterarDadosDoRepresentante() throws Exception {

        RepresentanteForm representanteForm = this.payloadRepresentante3();
        this.persisteRepresentante(representanteForm);

        RepresentanteForm representanteAlterado = RepresentanteForm.builder()
                .codigo("R-3")
                .nome("Jose")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();

        String requestPayload = objectMapper.writeValueAsString(representanteAlterado);
        this.mockMvc.perform(put("http://localhost:8080/representante/alterar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(representanteAlterado.getNome())));

    }

    /**
     * teste deve apagar um representante caso ele
     * exista no banco de dados.
     */

    @Test
    void deveApagarUmRepresentante() throws Exception {
        RepresentanteForm representanteForm = this.payloadRepresentante4();
        Representante representante = this.converte(representanteForm);
        this.representanteService.salvar(representante);

        this.mockMvc.perform(delete("http://localhost:8080/representante/delete/" + representante.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void NaoDeveApagarUmRepresentante() throws Exception {
        Long id = 3060L;
        RepresentanteForm representanteForm = this.payloadRepresentante5();
        Representante representante = this.converte(representanteForm);
        this.representanteService.salvar(representante);

        this.mockMvc.perform(delete("http://localhost:8080/representante/delete/" + id.toString())).andExpect(result -> result.getResponse().equals(status().isNoContent()));

    }



}
