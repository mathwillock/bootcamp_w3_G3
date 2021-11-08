package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.VendedorForm;
import com.bootcamp_w3_g3.model.entity.Vendedor;
import com.bootcamp_w3_g3.service.VendedorService;
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
public class VendedorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VendedorService vendedorService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private VendedorForm payloadVendedor(){
        return VendedorForm.builder()
                .codigo("V-10").nome("Alex").sobrenome("Cruz")
                .endereco("teste 1").cpf("111.111.111.01")
                .telefone("11-111111").build();
    }

    private VendedorForm payloadVendedor2(){
        return VendedorForm.builder()
                .codigo("V-11").nome("Hugo").sobrenome("Damm")
                .endereco("teste 1").cpf("111.111.111.01")
                .telefone("11-111111").build();
    }

    private VendedorForm payloadVendedor3(){
        return VendedorForm.builder()
                .codigo("V-12").nome("Marcelo").sobrenome("Sousa")
                .endereco("teste 1").cpf("111.111.111.01")
                .telefone("11-111111").build();
    }

    private VendedorForm payloadVendedor4(){
        return VendedorForm.builder()
                .codigo("V-13").nome("Matheus").sobrenome("Will")
                .endereco("teste 1").cpf("111.111.111.01")
                .telefone("11-111111").build();
    }


    private Vendedor persisteVendedor(VendedorForm vendedorForm){
        Vendedor vendedor = Vendedor.builder().codigo(vendedorForm.getCodigo())
                .nome(vendedorForm.getNome()).cpf(vendedorForm.getCpf())
                .endereco(vendedorForm.getEndereco())
                .sobrenome(vendedorForm.getSobrenome())
                .telefone(vendedorForm.getTelefone()).build();

        return vendedorService.salvar(vendedor);
    }


    /**
     * Teste deve salvar um vendedor
     */
    @Test
    void deveSalvarUmVendedor() throws Exception {
        VendedorForm vendedorForm = this.payloadVendedor();
        String requestPayload = objectMapper.writeValueAsString(vendedorForm);

        this.mockMvc.perform(post("http://localhost:8080/vendedor/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isCreated());
    }


    /**
     * Teste deve obter um vendedor
     * caso ele esteja cadastrado no banco de dados.
     */
    @Test
    void deveObterUmVendedor() throws Exception {
        VendedorForm vendedorForm = this.payloadVendedor2();
        Vendedor vendedor = this.persisteVendedor(vendedorForm);

        this.mockMvc.perform(get("http://localhost:8080/vendedor/obter/" + vendedor.getCodigo()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(vendedor.getNome())));
    }


    /**
     * teste deve listar todos os vendedores cadastrados
     */
    @Test
    void deveListarVendedores() throws Exception {
        VendedorForm vendedorForm = this.payloadVendedor3();
        this.persisteVendedor(vendedorForm);

        this.mockMvc.perform(get("http://localhost:8080/vendedor/listar"))
                .andExpect(status().isOk());
    }


    /*
    Teste deve alterar dados de um vendedor cadastrado.
     */
    @Test
    void deveAtualizarUmVendedor() throws Exception {
        VendedorForm vendedorForm = this.payloadVendedor4();
        this.persisteVendedor(vendedorForm);

        VendedorForm vendedorAtualizado = VendedorForm.builder()
                .codigo(vendedorForm.getCodigo())
                .nome("Paulo").sobrenome("Sousa")
                .endereco("qualquer").telefone(vendedorForm.getTelefone())
                .cpf(vendedorForm.getCpf()).build();

        String requestPayload = objectMapper.writeValueAsString(vendedorAtualizado);
        this.mockMvc.perform(put("http://localhost:8080/vendedor/alterar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(vendedorAtualizado.getNome())))
                .andExpect(jsonPath("$.sobrenome", is(vendedorAtualizado.getSobrenome())));
    }



}
