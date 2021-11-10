package com.bootcamp_w3_g3.controller;


import com.bootcamp_w3_g3.BootcampW3G3Application;
import com.bootcamp_w3_g3.model.dtos.request.*;
import com.bootcamp_w3_g3.model.dtos.response.TokenDTO;
import com.bootcamp_w3_g3.model.entity.*;
import com.bootcamp_w3_g3.repository.LoteRepository;
import com.bootcamp_w3_g3.repository.UsuarioRepository;
import com.bootcamp_w3_g3.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Joaquim Borges
 */

@SpringBootTest(classes = BootcampW3G3Application.class)
@AutoConfigureMockMvc
public class LoteIntegrationTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LoteRepository loteRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private LoteService loteService;
    @Autowired
    private SetorService setorService;
    @Autowired
    private ArmazemService armazemService;
    @Autowired
    private RepresentanteService representanteService;
    @Autowired
    private UsuarioRepository usuarioRepository;


    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void iniciarMocks(){
        MockitoAnnotations.openMocks(this);
    }


    private ProdutoForm payloadProduto() {
        return ProdutoForm.builder()
                .codigoDoProduto(16)
                .nome("carne seca")
                .preco(50.0)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto2() {
        return ProdutoForm.builder()
                .codigoDoProduto(17)
                .nome("carne seca")
                .preco(50.0)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto3() {
        return ProdutoForm.builder()
                .codigoDoProduto(18)
                .nome("carne seca")
                .preco(50.0)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto4() {
        return ProdutoForm.builder()
                .codigoDoProduto(19)
                .nome("carne seca")
                .preco(50.0)
                .temperaturaIndicada(16.0)
                .build();
    }

    private void persisteProduto(ProdutoForm produtoForm){
        Produto produto = Produto.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .tipoProduto(produtoForm.getTipoProduto())
                .nome(produtoForm.getNome())
                .temperaturaIndicada(produtoForm.getTemperaturaIndicada())
                .preco(produtoForm.getPreco())
                .codLote(null).build();

        produtoService.salvar(produto);
    }


    private RepresentanteForm payloadRepresentante(){
        return RepresentanteForm.builder()
                .codigo("R-9")
                .nome("Joao")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante2(){
        return RepresentanteForm.builder()
                .codigo("R-10")
                .nome("Joao")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante3(){
        return RepresentanteForm.builder()
                .codigo("R-11")
                .nome("Joao")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante4(){
        return RepresentanteForm.builder()
                .codigo("RP-12")
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
                .codArmazem("AR-7")
                .nome("armazem central")
                .representante(representanteForm)
                .endereco("qualquer lugar")
                .numero(100)
                .uf("SP").build();
    }

    private ArmazemForm payloadArmazem2() {
        RepresentanteForm representanteForm = this.payloadRepresentante2();
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-8")
                .nome("armazem central")
                .representante(representanteForm)
                .endereco("qualquer lugar")
                .numero(100)
                .uf("SP").build();
    }

    private ArmazemForm payloadArmazem3() {
        RepresentanteForm representanteForm = this.payloadRepresentante3();
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-9")
                .nome("armazem central")
                .representante(representanteForm)
                .endereco("qualquer lugar")
                .numero(100)
                .uf("SP").build();
    }

    private ArmazemForm payloadArmazem4() {
        RepresentanteForm representanteForm = this.payloadRepresentante4();
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("ARM-10")
                .nome("armazem central")
                .representante(representanteForm)
                .endereco("qualquer lugar")
                .numero(100)
                .uf("SP").build();
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



    private void persisteSetor1(SetorForm setorForm){
        ArmazemForm armazemForm = payloadArmazem();
        this.persisteArmazem(armazemForm);
        Armazem armazemSetor = this.armazemService.obterArmazem(armazemForm.getCodArmazem());

        Setor setor = Setor.builder()
                .tipoProduto(setorForm.getTipoProduto())
                .nome(setorForm.getNome())
                .armazem(armazemSetor)
                .espacoDisponivel(setorForm.getEspacoDisponivel())
                .codigo(setorForm.getCodigo()).build();

        this.setorService.salvarSetor(setor);
    }

    private void persisteSetor2(SetorForm setorForm){
        ArmazemForm armazemForm = payloadArmazem2();
        this.persisteArmazem(armazemForm);
        Armazem armazemSetor = this.armazemService.obterArmazem(armazemForm.getCodArmazem());

        Setor setor = Setor.builder()
                .tipoProduto(setorForm.getTipoProduto())
                .nome(setorForm.getNome())
                .armazem(armazemSetor)
                .espacoDisponivel(setorForm.getEspacoDisponivel())
                .codigo(setorForm.getCodigo()).build();

        this.setorService.salvarSetor(setor);
    }


    private void persisteSetor3(SetorForm setorForm){
        ArmazemForm armazemForm = payloadArmazem3();
        this.persisteArmazem(armazemForm);
        Armazem armazemSetor = this.armazemService.obterArmazem(armazemForm.getCodArmazem());

        Setor setor = Setor.builder()
                .tipoProduto(setorForm.getTipoProduto())
                .nome(setorForm.getNome())
                .armazem(armazemSetor)
                .espacoDisponivel(setorForm.getEspacoDisponivel())
                .codigo(setorForm.getCodigo()).build();

        this.setorService.salvarSetor(setor);
    }

    private void persisteSetor4(SetorForm setorForm){
        ArmazemForm armazemForm = payloadArmazem4();
        this.persisteArmazem(armazemForm);
        Armazem armazemSetor = this.armazemService.obterArmazem(armazemForm.getCodArmazem());

        Setor setor = Setor.builder()
                .tipoProduto(setorForm.getTipoProduto())
                .nome(setorForm.getNome())
                .armazem(armazemSetor)
                .espacoDisponivel(setorForm.getEspacoDisponivel())
                .codigo(setorForm.getCodigo()).build();

        this.setorService.salvarSetor(setor);
    }

    private void persisteLote2(LoteForm loteForm) {
        SetorForm setorForm = SetorForm.builder()
                .tipoProduto(TipoProduto.CONGELADOS)
                .nome("Setor de congelados")
                .codigo("ST-1")
                .armazem(null)
                .espacoDisponivel(10).build();

        this.persisteSetor2(setorForm);

        Setor setorDoLote = setorService.obterSetor(setorForm.getCodigo());
        Produto produto = produtoService.obter(loteForm.getProdutoForm().getCodigoDoProduto());

        Lote loteEnviado = Lote.builder()
                .setor(setorDoLote)
                .produto(produto)
                .numero(loteForm.getNumero())
                .dataDeValidade(loteForm.getDataDeValidade())
                .dataDeFabricacao(loteForm.getDataDeFabricacao())
                .horaFabricacao(loteForm.getHoraFabricacao())
                .quantidadeAtual(loteForm.getQuantidadeAtual())
                .quantidadeMinina(loteForm.getQuantidadeMinina())
                .temperaturaMinima(loteForm.getTemperaturaMinima())
                .temperaturaAtual(loteForm.getTemperaturaAtual()).build();

        loteService.salvar(loteEnviado);
    }

    private void persisteLote3(LoteForm loteForm) {
        SetorForm setorForm = SetorForm.builder()
                .tipoProduto(TipoProduto.CONGELADOS)
                .nome("Setor de congelados")
                .codigo("SE-3")
                .armazem(null)
                .espacoDisponivel(10).build();

        this.persisteSetor3(setorForm);

        Setor setorDoLote = setorService.obterSetor(setorForm.getCodigo());
        Produto produto = produtoService.obter(loteForm.getProdutoForm().getCodigoDoProduto());

        Lote loteEnviado = Lote.builder()
                .setor(setorDoLote)
                .produto(produto)
                .numero(loteForm.getNumero())
                .dataDeValidade(loteForm.getDataDeValidade())
                .dataDeFabricacao(loteForm.getDataDeFabricacao())
                .horaFabricacao(loteForm.getHoraFabricacao())
                .quantidadeAtual(loteForm.getQuantidadeAtual())
                .quantidadeMinina(loteForm.getQuantidadeMinina())
                .temperaturaMinima(loteForm.getTemperaturaMinima())
                .temperaturaAtual(loteForm.getTemperaturaAtual()).build();

        loteService.salvar(loteEnviado);
    }

    private void persisteLote4(LoteForm loteForm) {

        Setor setorDoLote = setorService.obterSetor(loteForm.getSetorForm().getCodigo());
        Produto produto = produtoService.obter(loteForm.getProdutoForm().getCodigoDoProduto());

        Lote loteEnviado = Lote.builder()
                .setor(setorDoLote)
                .produto(produto)
                .numero(loteForm.getNumero())
                .dataDeValidade(loteForm.getDataDeValidade())
                .dataDeFabricacao(loteForm.getDataDeFabricacao())
                .horaFabricacao(loteForm.getHoraFabricacao())
                .quantidadeAtual(loteForm.getQuantidadeAtual())
                .quantidadeMinina(loteForm.getQuantidadeMinina())
                .temperaturaMinima(loteForm.getTemperaturaMinima())
                .temperaturaAtual(loteForm.getTemperaturaAtual()).build();

        loteService.salvar(loteEnviado);
    }


    /**
     * teste deve criar um lote se o payload estiver valido
     * e o representante estiver autenticado por token
     */
    @Test
    void deveSalvarUmLote() throws Exception{
        SetorForm setorDoLote =  SetorForm.builder()
                .tipoProduto(TipoProduto.CONGELADOS)
                .nome("Setor de congelados").codigo("Se-4")
                .armazem(null).espacoDisponivel(10).build();

        this.persisteSetor1(setorDoLote);

        ProdutoForm produtoForm = this.payloadProduto();
        this.persisteProduto(produtoForm);

        Usuario usuario = Usuario.builder().login("representante")
                .senha("$2a$10$BDoxHiGmU8F1ohZ7VEvRoeZujhmT7JP34Nmu/PGkmjPOP4sPX9nd6").build();
        usuarioRepository.save(usuario);

        LoteForm loteForm = LoteForm.builder()
                .numero(9).setorForm(setorDoLote).temperaturaAtual(17.0)
                .temperaturaMinima(13.1).quantidadeMinina(2).quantidadeAtual(3)
                .produtoForm(produtoForm).horaFabricacao(LocalTime.now())
                .dataDeValidade(LocalDate.of(2021, 12, 20))
                .dataDeFabricacao(LocalDate.now()).build();

        String login = "representante";
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

        String requestPayload = objectMapper.writeValueAsString(loteForm);

        this.mockMvc.perform(post("http://localhost:8080/lote/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload)
                        .header("Authorization", "Bearer " + tokenDTO.getToken()))
                .andExpect(status().isCreated());

    }

    /**
     *teste deve obter um lote caso ele exista no banco de dados.
     */
    @Test
    void deveObterUmLote() throws Exception {
        ProdutoForm produtoForm = this.payloadProduto2();
        this.persisteProduto(produtoForm);
        LoteForm loteForm = LoteForm.builder()
                .numero(10).setorForm(null).temperaturaAtual(17.0)
                .temperaturaMinima(13.1).quantidadeMinina(2).quantidadeAtual(3)
                .produtoForm(produtoForm).horaFabricacao(LocalTime.now())
                .dataDeValidade(LocalDate.of(2021, 12, 20))
                .dataDeFabricacao(LocalDate.now()).build();

        this.persisteLote2(loteForm);

        this.mockMvc.perform(get("http://localhost:8080/lote/obter/" + loteForm.getNumero()))
                .andExpect(status().isOk());

    }

    /**
     *teste deve listar todos os lotes
     */
    @Test
    void deveListarOsLotes() throws Exception {
        ProdutoForm produtoForm = this.payloadProduto3();
        this.persisteProduto(produtoForm);
        LoteForm loteForm = LoteForm.builder()
                .numero(11).setorForm(null).temperaturaAtual(17.0)
                .temperaturaMinima(13.1).quantidadeMinina(2).quantidadeAtual(3)
                .produtoForm(produtoForm).horaFabricacao(LocalTime.now())
                .dataDeValidade(LocalDate.of(2021, 12, 20))
                .dataDeFabricacao(LocalDate.now()).build();

        this.persisteLote3(loteForm);

        this.mockMvc.perform(get("http://localhost:8080/lote/listar"))
                .andExpect(status().isOk());

    }



    @Test
    void deveAlterar_dadosDoLote() throws Exception {
        ProdutoForm produtoForm = this.payloadProduto4();
        this.persisteProduto(produtoForm);

        SetorForm setorDoLote =  SetorForm.builder()
                .tipoProduto(TipoProduto.CONGELADOS)
                .nome("Setor de congelados").codigo("SeT-191")
                .armazem(null).espacoDisponivel(10).build();

        this.persisteSetor4(setorDoLote);

        LoteForm loteForm = LoteForm.builder()
                .numero(13).temperaturaAtual(17.0).setorForm(setorDoLote)
                .temperaturaMinima(13.1).quantidadeMinina(2).quantidadeAtual(3)
                .produtoForm(produtoForm).horaFabricacao(LocalTime.now())
                .dataDeValidade(LocalDate.of(2021, 12, 20))
                .dataDeFabricacao(LocalDate.now()).build();

        this.persisteLote4(loteForm);

        LoteForm loteAlterado = LoteForm.builder()
                .numero(13).setorForm(loteForm.getSetorForm()).temperaturaAtual(17.0)
                .temperaturaMinima(13.1).quantidadeMinina(2).quantidadeAtual(2)
                .produtoForm(produtoForm).horaFabricacao(LocalTime.now())
                .dataDeValidade(LocalDate.of(2021, 12, 20))
                .dataDeFabricacao(LocalDate.now()).build();

        String requestPayload = objectMapper.writeValueAsString(loteAlterado);


        this.mockMvc.perform(put("http://localhost:8080/lote/alterar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.quantidadeAtual", is(loteAlterado.getQuantidadeAtual())));

    }






}
