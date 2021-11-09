package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.BootcampW3G3Application;
import com.bootcamp_w3_g3.model.dtos.request.*;
import com.bootcamp_w3_g3.model.dtos.response.TokenDTO;
import com.bootcamp_w3_g3.model.entity.*;
import com.bootcamp_w3_g3.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


/**
 * @autor Joaquim Borges
 */
@SpringBootTest(classes = BootcampW3G3Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class ProdutoIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private RepresentanteService representanteService;
    @Autowired
    private SetorService setorService;
    @Autowired
    private ArmazemService armazemService;
    @Autowired
    private LoteService loteService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private ProdutoForm payloadProduto() {
        return ProdutoForm.builder()
                .codigoDoProduto(10)
                .nome("carne seca")
                .tipoProduto(TipoProduto.FRESCOS)
                .preco(50.0)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto2() {
        return ProdutoForm.builder()
                .codigoDoProduto(11)
                .nome("sorvete")
                .tipoProduto(TipoProduto.CONGELADOS)
                .preco(50.0)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto3() {
        return ProdutoForm.builder()
                .codigoDoProduto(12)
                .nome("refri")
                .preco(50.0)
                .tipoProduto(TipoProduto.REFRIGERADOS)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto4() {
        return ProdutoForm.builder()
                .codigoDoProduto(13)
                .nome("carne seca")
                .preco(50.0)
                .tipoProduto(TipoProduto.FRESCOS)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto5() {
        return ProdutoForm.builder()
                .codigoDoProduto(14)
                .nome("carne")
                .preco(50.0)
                .tipoProduto(TipoProduto.FRESCOS)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto6() {
        return ProdutoForm.builder().codigoDoProduto(30)
                .tipoProduto(TipoProduto.REFRIGERADOS)
                .nome("frango").preco(20.0).temperaturaIndicada(14.1)
                .build();
    }

    private RepresentanteForm payloadRepresentante(){
        return RepresentanteForm.builder()
                .codigo("R-30")
                .nome("Alex")
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

    private ArmazemForm payloadArmazem(RepresentanteForm representanteForm) {
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-30")
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
        Armazem armazemSetor = this.armazemService.obterArmazem(setorForm.getArmazem().getCodArmazem());

        Setor setor = Setor.builder()
                .tipoProduto(setorForm.getTipoProduto())
                .nome(setorForm.getNome())
                .armazem(armazemSetor)
                .espacoDisponivel(setorForm.getEspacoDisponivel())
                .codigo(setorForm.getCodigo()).build();

        this.setorService.salvarSetor(setor);
    }

    private void persisteLote2(LoteForm loteForm) {

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


    private void persisteProduto(ProdutoForm produtoForm) {
        Produto novoProduto = Produto.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .nome(produtoForm.getNome())
                .tipoProduto(produtoForm.getTipoProduto())
                .temperaturaIndicada(produtoForm.getTemperaturaIndicada())
                .build();
        this.produtoService.salvar(novoProduto);
    }

    private Produto converte(ProdutoForm produtoForm) {
        return Produto.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .nome(produtoForm.getNome())
                .preco(produtoForm.getPreco())
                .temperaturaIndicada(produtoForm.getTemperaturaIndicada()).build();
    }

    /**
     * teste deve cadastrar um produto caso
     * o payload seja valido
     */
    @Test
    void deveCadastrarUmProduto() throws Exception {

        ProdutoForm produto = payloadProduto();
        String requestPayload = objectMapper.writeValueAsString(produto);

        this.mockMvc.perform(post("http://localhost:8080/produtos/cadastra")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isCreated());
    }

    /**
     * teste deve obter um produto caso ele exista
     * no banco de dados, exigindo autenticacao do usuario.
     */

    @Test
    void deveObterUmProdutoCadastrado() throws Exception {
        ProdutoForm produtoForm = this.payloadProduto2();
        this.persisteProduto(produtoForm);

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


        this.mockMvc.perform(get("http://localhost:8080/produtos/obter/" + produtoForm.getCodigoDoProduto())
                        .header("Authorization", "Bearer " + tokenDTO.getToken()))
                        .andExpect(status().isOk());
    }


    /**
     * teste deve alterar dos dados de um produto
     * caso ele exista no banco e o payload seja valida
     * retorno comparado pelo nome
     */
    @Test
    void deveAlterarDadosDeUmProduto() throws Exception {
        ProdutoForm produtoForm = this.payloadProduto3();
        this.persisteProduto(produtoForm);

        ProdutoForm produtoAlterado = ProdutoForm.builder()
                .codigoDoProduto(12)
                .nome("carne de sol")
                .preco(60.0)
                .tipoProduto(TipoProduto.FRESCOS)
                .temperaturaIndicada(16.0)
                .build();

        String payloadRequest = objectMapper.writeValueAsString(produtoAlterado);
        this.mockMvc.perform(put("http://localhost:8080/produtos/alterar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(produtoAlterado.getNome())));
    }



    /**
     * teste deve apagar um produto caso ele exista
     * no banco de dados
     */
    @Test
    void deveApagarUmProduto() throws Exception {
        ProdutoForm produtoForm = this.payloadProduto4();
        Produto produto = this.converte(produtoForm);
        this.produtoService.salvar(produto);

        this.mockMvc.perform(delete("http://localhost:8080/produtos/deletar/" + produto.getId()))
                .andExpect(status().isOk());
    }

    /**
     * teste deve listar todos os produtos da mesma
     * categoria
     * @autor Joaquim Borges
     */
    @Test
    void deveListarProdutosPorCategoria() throws Exception {
        ProdutoForm produto = this.payloadProduto5();

        this.persisteProduto(produto);

        this.mockMvc.perform(get("http://localhost:8080/produtos/listar/" + produto.getTipoProduto()))
                .andExpect(status().isOk());
    }

    /**
     *não deve obter um produto
     * porque o usuario não está autenticado.
     */
    @Test
    void naoDeveObterUmProdutoCadastrado() throws Exception {
        ProdutoForm produto = ProdutoForm.builder()
                .codigoDoProduto(15)
                .nome("carne seca")
                .preco(60.0)
                .temperaturaIndicada(16.0)
                .build();

        this.persisteProduto(produto);

        this.mockMvc.perform(get("http://localhost:8080/produtos/obter/" + produto.getCodigoDoProduto()))
                .andExpect(status().isForbidden());
    }


    /**
     * Teste deve localizar o produto no setor
     * e listar os diferentes lotes em que ele pertence.
     */
    @Test
    void deveListarLotesDoProduto() throws Exception {
        ProdutoForm produtoForm = this.payloadProduto6();
        this.persisteProduto(produtoForm);

        RepresentanteForm representanteForm = this.payloadRepresentante();
        ArmazemForm armazemForm = this.payloadArmazem(representanteForm);
        this.persisteArmazem(armazemForm);

        SetorForm setorForm = SetorForm.builder().codigo("S-50")
                .nome("B").tipoProduto(TipoProduto.REFRIGERADOS)
                .espacoDisponivel(10).armazem(armazemForm)
                .build();

        this.persisteSetor1(setorForm);

        LoteForm loteForm = LoteForm.builder().numero(50)
                .setorForm(setorForm).produtoForm(produtoForm)
                .dataDeValidade(LocalDate.of(2021, 12, 20))
                .dataDeFabricacao(LocalDate.now()).horaFabricacao(LocalTime.now())
                .quantidadeAtual(4).quantidadeMinina(2).temperaturaAtual(13.2)
                .temperaturaMinima(12.5).build();

        this.persisteLote2(loteForm);

        this.mockMvc.perform(get("http://localhost:8080/produtos/lotes/listar/" + produtoForm.getCodigoDoProduto()))
                .andExpect(status().isOk());
    }


}
