package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.BootcampW3G3Application;
import com.bootcamp_w3_g3.model.dtos.request.ArmazemForm;
import com.bootcamp_w3_g3.model.dtos.request.RepresentanteForm;
import com.bootcamp_w3_g3.model.dtos.request.SetorForm;
import com.bootcamp_w3_g3.model.dtos.request.UsuarioForm;
import com.bootcamp_w3_g3.model.dtos.response.TokenDTO;
import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
import com.bootcamp_w3_g3.service.ArmazemService;
import com.bootcamp_w3_g3.service.RepresentanteService;
import com.bootcamp_w3_g3.service.SetorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

/**
 * @autor Joaquim Borges
 */
@SpringBootTest(classes = BootcampW3G3Application.class)
@AutoConfigureMockMvc
public class SetorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private SetorService setorService;
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
                .codigo("R-19")
                .nome("Joaquim")
                .sobrenome("Borges")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante2(){
        return RepresentanteForm.builder()
                .codigo("R-20")
                .nome("Alex")
                .sobrenome("Cruz")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante3(){
        return RepresentanteForm.builder()
                .codigo("R-21")
                .nome("Hugo")
                .sobrenome("Damm")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante4(){
        return RepresentanteForm.builder()
                .codigo("R-22")
                .nome("Marcelo")
                .sobrenome("Santos")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante5(){
        return RepresentanteForm.builder()
                .codigo("R-23")
                .nome("MAtheus")
                .sobrenome("Willock")
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


    private ArmazemForm payloadArmazem(RepresentanteForm representanteForm) {
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-16")
                .nome("central")
                .representante(representanteForm)
                .endereco("rua qualquer")
                .uf("SP")
                .build();
    }

    private ArmazemForm payloadArmazem2(RepresentanteForm representanteForm) {
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-17")
                .nome("teste1")
                .representante(representanteForm)
                .endereco("rua qualquer")
                .uf("SP")
                .build();
    }

    private ArmazemForm payloadArmazem3(RepresentanteForm representanteForm) {
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-18")
                .nome("teste3")
                .representante(representanteForm)
                .endereco("rua qualquer")
                .uf("SP")
                .build();
    }

    private ArmazemForm payloadArmazem4(RepresentanteForm representanteForm) {
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-19")
                .nome("central")
                .representante(representanteForm)
                .endereco("rua qualquer")
                .uf("SP")
                .build();
    }

    private ArmazemForm payloadArmazem5(RepresentanteForm representanteForm) {
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-20")
                .nome("central")
                .representante(representanteForm)
                .endereco("rua qualquer")
                .uf("SP")
                .build();
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


    private Setor persisteSetor(SetorForm setorForm){

        Armazem armazemSetor = this.armazemService.obterArmazem(setorForm.getArmazem().getCodArmazem());

        Setor setor = Setor.builder()
                .tipoProduto(setorForm.getTipoProduto())
                .nome(setorForm.getNome())
                .armazem(armazemSetor)
                .espacoDisponivel(setorForm.getEspacoDisponivel())
                .codigo(setorForm.getCodigo()).build();

        return setorService.salvarSetor(setor);
    }


    /**
     * teste deve cadastrar um setor
     * caso o armazem já exista e tenha um representante
     * cadastrado.
     */
    @Test
    void deveCadastrarUmSetor() throws Exception {
        RepresentanteForm representanteForm = this.payloadRepresentante();
        ArmazemForm armazemForm = this.payloadArmazem(representanteForm);
        this.persisteArmazem(armazemForm);

        SetorForm setorForm = SetorForm.builder().codigo("S-18").nome("B")
                .tipoProduto(TipoProduto.FRESCOS).espacoDisponivel(10)
                .armazem(armazemForm).build();

        String requestPayload = objectMapper.writeValueAsString(setorForm);

        this.mockMvc.perform(post("http://localhost:8080/setor/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isCreated());

    }


    /**
     * Teste deve obter as informacoes de
     * um setor específico.
     * Somente se o representante estiver autenticado.
     */
    @Test
    void deveObterUmSetor() throws Exception {
       RepresentanteForm representanteForm = this.payloadRepresentante2();
       ArmazemForm armazemForm = this.payloadArmazem2(representanteForm);
       this.persisteArmazem(armazemForm);

        SetorForm setorForm = SetorForm.builder().codigo("S-19").nome("C")
                .tipoProduto(TipoProduto.CONGELADOS).espacoDisponivel(10)
                .armazem(armazemForm).build();

        this.persisteSetor(setorForm);

        String login = "joaquim";
        String senha = "123";
        UsuarioForm payload = UsuarioForm.builder().login(login).senha(senha).build();
        String isso = objectMapper.writeValueAsString(payload);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("http://localhost:8080/auth")
                        .content(isso)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(response, TokenDTO.class);

        this.mockMvc.perform(get("http://localhost:8080/setor/obter/"+ setorForm.getCodigo())
                        .header("Authorization", "Bearer " + tokenDTO.getToken()))
                        .andExpect(status().isOk());
    }


    /**
     * Teste deve apagar as informações
     * de um setor específico.
     */
    @Test
    void deveApagarUmSetor() throws Exception {
        RepresentanteForm representanteForm = this.payloadRepresentante3();

        ArmazemForm armazemForm = this.payloadArmazem3(representanteForm);
        this.persisteArmazem(armazemForm);

        SetorForm setorForm = SetorForm.builder().codigo("S-20").nome("D")
                .tipoProduto(TipoProduto.REFRIGERADOS).espacoDisponivel(10)
                .armazem(armazemForm).build();

        Setor setor = this.persisteSetor(setorForm);

        this.mockMvc.perform(delete("http://localhost:8080/setor/remover/" + setor.getId()))
                    .andExpect(status().isOk());
    }


    /**
     * Teste deve localizar o armazem do setor.
     * Passando o codigo do seu armazem.
     */
    @Test
    void deveBuscarOArmazemDoSetor() throws Exception {
        RepresentanteForm representanteForm = this.payloadRepresentante4();
        ArmazemForm armazemForm = this.payloadArmazem4(representanteForm);
        this.persisteArmazem(armazemForm);

        SetorForm setorForm = SetorForm.builder().codigo("S-21").nome("E")
                .tipoProduto(TipoProduto.REFRIGERADOS).espacoDisponivel(10)
                .armazem(armazemForm).build();

        this.persisteSetor(setorForm);

        this.mockMvc.perform(get("http://localhost:8080/setor/buscar-armazem/" + setorForm.getArmazem().getCodArmazem()))
                .andExpect(status().isOk());

    }

    /**
     *Teste deve atualizar os dados
     * do setor.
     */
    @Test
    void deveAtualizarUmSetor() throws Exception {
        RepresentanteForm representanteForm = this.payloadRepresentante5();
        ArmazemForm armazemForm = this.payloadArmazem5(representanteForm);
        this.persisteArmazem(armazemForm);

        SetorForm setorForm = SetorForm.builder().codigo("S-22").nome("F")
                .tipoProduto(TipoProduto.CONGELADOS).espacoDisponivel(10)
                .armazem(armazemForm).build();

        this.persisteSetor(setorForm);

        SetorForm setorAlterado = SetorForm.builder().codigo("S-22").nome("G")
                .tipoProduto(TipoProduto.FRESCOS).espacoDisponivel(10)
                .armazem(armazemForm).build();

        String requestPayload = objectMapper.writeValueAsString(setorAlterado);
        this.mockMvc.perform(put("http://localhost:8080/setor/alterar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.nome", is(setorAlterado.getNome())));


    }











}
