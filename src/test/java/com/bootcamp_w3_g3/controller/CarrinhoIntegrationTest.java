package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.BootcampW3G3Application;
import com.bootcamp_w3_g3.model.dtos.request.*;
import com.bootcamp_w3_g3.model.dtos.response.TokenDTO;
import com.bootcamp_w3_g3.model.entity.*;
import com.bootcamp_w3_g3.repository.UsuarioRepository;
import com.bootcamp_w3_g3.service.*;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BootcampW3G3Application.class)
@AutoConfigureMockMvc
public class CarrinhoIntegrationTest {

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
    @Autowired
    private CompradorService compradorService;
    @Autowired
    private ItensService itensService;
    @Autowired
    private CarrinhoService carrinhoService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private CompradorForm payLoadComprador111(){
        return CompradorForm.builder()
                .codigo("C-301")
                .nome("Henrique Alfonse")
                .sobrenome("Zang")
                .cpf("555.444.666-79")
                .telefone("51 5555-7777")
                .endereco("Av. João do meio fio.")
                .build();
    }

    private void persisteComprador(CompradorForm compradorForm){
        Comprador novoComprador = Comprador.builder()
                                    .codigo(compradorForm.getCodigo())
                                    .nome(compradorForm.getNome())
                                    .sobrenome(compradorForm.getSobrenome())
                                    .cpf(compradorForm.getCpf())
                                    .telefone(compradorForm.getTelefone())
                                    .endereco(compradorForm.getEndereco())
                                    .build();
        this.compradorService.salvar(novoComprador);
    }

    private ProdutoForm payLoadProduto111() {
        return ProdutoForm.builder()
                .codigoDoProduto(1111)
                .nome("carne seca")
                .tipoProduto(TipoProduto.FRESCOS)
                .preco(50.00)
                .temperaturaIndicada(16.00)
                .build();
    }

    private ProdutoForm payLoadProduto222() {
        return ProdutoForm.builder()
                .codigoDoProduto(2222)
                .nome("sorvete")
                .tipoProduto(TipoProduto.CONGELADOS)
                .preco(50.00)
                .temperaturaIndicada(16.00)
                .build();
    }

    private ProdutoForm payLoadProduto333() {
        return ProdutoForm.builder()
                .codigoDoProduto(3333)
                .nome("refri")
                .preco(50.00)
                .tipoProduto(TipoProduto.REFRIGERADOS)
                .temperaturaIndicada(16.00)
                .build();
    }

    private ProdutoForm payLoadProduto444() {
        return ProdutoForm.builder()
                .codigoDoProduto(4444)
                .nome("frango")
                .preco(35.00)
                .tipoProduto(TipoProduto.CONGELADOS)
                .temperaturaIndicada(16.00)
                .build();
    }

    private ProdutoForm payLoadProduto55() {
        return ProdutoForm.builder()
                .codigoDoProduto(5555)
                .nome("alface")
                .preco(3.50)
                .tipoProduto(TipoProduto.FRESCOS)
                .temperaturaIndicada(16.00)
                .build();
    }

    private void persisteProduto(ProdutoForm produtoForm) {
        Produto novoProduto = Produto.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .nome(produtoForm.getNome())
                .tipoProduto(produtoForm.getTipoProduto())
                .preco(produtoForm.getPreco())
                .temperaturaIndicada(produtoForm.getTemperaturaIndicada())
                .codLote(99)
                .build();
        this.produtoService.salvar(novoProduto);
    }

    private ItensForm payLoadItens111(ProdutoForm produtoForm){
        this.persisteProduto(produtoForm);

        return ItensForm.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .quantidade(10000)
                .build();
    }

    private ItensForm payLoadItens222(ProdutoForm produtoForm){
        this.persisteProduto(produtoForm);

        return ItensForm.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .quantidade(20000)
                .build();
    }

    private ItensForm payLoadItens333(ProdutoForm produtoForm){
        this.persisteProduto(produtoForm);

        return ItensForm.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .quantidade(30000)
                .build();
    }

    private ItensForm payLoadItens444(ProdutoForm produtoForm){
        this.persisteProduto(produtoForm);

        return ItensForm.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .quantidade(40000)
                .build();
    }

    private ItensForm payLoadItens555(ProdutoForm produtoForm){
        this.persisteProduto(produtoForm);

        return ItensForm.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .quantidade(50000)
                .build();
    }

    private void persisteItens(ItensForm itensForm){
        Produto produto = this.produtoService.obter(itensForm.getCodigoDoProduto());
        Itens novoItens = Itens.builder()
                .produto(produto)
                .quantidade(itensForm.getQuantidade())
                .build();
        this.itensService.salvar(novoItens);
    }

    private RepresentanteForm payLoadRepresentante111(){
        return RepresentanteForm.builder()
                .codigo("R-301")
                .nome("Alex")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payLoadRepresentante222(){
        return RepresentanteForm.builder()
                .codigo("R-1000")
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

    private ArmazemForm payLoadArmazem111(RepresentanteForm representanteForm) {

        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-3000")
                .nome("armazem central")
                .representante(representanteForm)
                .endereco("qualquer lugar")
                .numero(100)
                .uf("SP").build();
    }

    private ArmazemForm payLoadArmazem222(RepresentanteForm representanteForm) {
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-100")
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

    private void persisteSetor111(SetorForm setorForm){

        Armazem armazemSetor = this.armazemService.obterArmazem(setorForm.getArmazem().getCodArmazem());

        Setor setor = Setor.builder()
                .tipoProduto(setorForm.getTipoProduto())
                .nome(setorForm.getNome())
                .armazem(armazemSetor)
                .espacoDisponivel(setorForm.getEspacoDisponivel())
                .codigo(setorForm.getCodigo()).build();

        this.setorService.salvarSetor(setor);
    }

    private void persisteLote(LoteForm loteForm) {

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

    private CarrinhoForm payLoadCarrinho(){

        CompradorForm compradorForm111 = this.payLoadComprador111();
        this.persisteComprador(compradorForm111);

        ProdutoForm produtoForm111 = this.payLoadProduto111();
        ProdutoForm produtoForm222 = this.payLoadProduto222();
        ProdutoForm produtoForm333 = this.payLoadProduto333();

        ItensForm itensForm111 = this.payLoadItens111(produtoForm111);
        ItensForm itensForm222 = this.payLoadItens222(produtoForm222);
        ItensForm itensForm333 = this.payLoadItens333(produtoForm333);

        this.persisteItens(itensForm111);
        this.persisteItens(itensForm222);
        this.persisteItens(itensForm333);

        List<ItensForm> itensFormList = new ArrayList<>();
        itensFormList.add(itensForm111);
        itensFormList.add(itensForm222);
        itensFormList.add(itensForm333);

        return CarrinhoForm.builder()
                .codigoCarrinho("CAR-987")
                .dataDaOrdem(LocalDate.parse("2021-10-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .statusCompra(StatusCompra.CONCLUIDO)
                .codigoComprador(compradorForm111.getCodigo())
                .itensList(itensFormList)
                .build();
    }

    private void persisteCarrinho(CarrinhoForm carrinhoForm){
        List<ItensForm> itensFormList = carrinhoForm.getItensList();

        List<Itens> itensList = new ArrayList<>();

        for (ItensForm i : itensFormList) {
            Produto produto = this.produtoService.obter(i.getCodigoDoProduto());
            Integer quantidade = i.getQuantidade();
            itensList.add(new Itens(produto, quantidade));
        }

        Carrinho novoCarrinho = Carrinho.builder()
                .codigo(carrinhoForm.getCodigoCarrinho())
                .dataDeOrdem(carrinhoForm.getDataDaOrdem())
                .statusCompra(carrinhoForm.getStatusCompra())
                .itensList(itensList)
                .codigoComprador(carrinhoForm.getCodigoComprador())
                .build();
        this.carrinhoService.salvar(novoCarrinho);
    }

    @Test
    void deveRegistrarUmCarrinho() throws Exception{
        RepresentanteForm representanteForm111 = this.payLoadRepresentante111();

        ArmazemForm armazemForm111 = this.payLoadArmazem111(representanteForm111);
        this.persisteArmazem(armazemForm111);

        SetorForm setorDoLote =  SetorForm.builder()
                .tipoProduto(TipoProduto.CONGELADOS)
                .nome("Setor de congelados").codigo("Se-400")
                .armazem(armazemForm111).espacoDisponivel(100).build();

        this.persisteSetor111(setorDoLote);

        ProdutoForm produtoForm = this.payLoadProduto444();
        this.persisteProduto(produtoForm);

        LoteForm loteForm = LoteForm.builder()
                .numero(99).setorForm(setorDoLote).temperaturaAtual(17.0)
                .temperaturaMinima(13.1).quantidadeMinina(2).quantidadeAtual(3)
                .produtoForm(produtoForm).horaFabricacao(LocalTime.now())
                .dataDeValidade(LocalDate.of(2021, 12, 20))
                .dataDeFabricacao(LocalDate.now()).build();
        this.persisteLote(loteForm);

        CarrinhoForm carrinhoForm = this.payLoadCarrinho();

        Usuario usuario = Usuario.builder().login("representante")
                .senha("$2a$10$BDoxHiGmU8F1ohZ7VEvRoeZujhmT7JP34Nmu/PGkmjPOP4sPX9nd6").build();
        usuarioRepository.save(usuario);

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

        String requestPayload = objectMapper.writeValueAsString(carrinhoForm);

        this.mockMvc.perform(post("http://localhost:8080/carrinho/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload)
                .header("Authorization", "Bearer " + tokenDTO.getToken()))
                .andExpect(status().isCreated());
    }


}
