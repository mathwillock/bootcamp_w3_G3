package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.*;
import com.bootcamp_w3_g3.model.entity.*;
import com.bootcamp_w3_g3.repository.*;
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

import java.time.LocalDate;
import java.time.LocalTime;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @autor Joaquim Borges
 */
@SpringBootTest
@AutoConfigureMockMvc
public class OrdemEntradaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrdemDeEntradaService ordemDeEntradaService;
    @Autowired
    private OrdemDeEntradaRepository ordemDeEntradaRepository;
    @Autowired
    private LoteService loteService;
    @Autowired
    private RepresentanteService representanteService;
    @Autowired
    private ArmazemService armazemService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private SetorService setorService;
    @Autowired
    private VendedorService vendedorService;



    private static ObjectMapper objectMapper;


    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private ProdutoForm payloadProduto(ProdutoForm produtoForm) {
        Produto novoProduto = Produto.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .nome(produtoForm.getNome())
                .preco(produtoForm.getPreco())
                .tipoProduto(produtoForm.getTipoProduto())
                .temperaturaIndicada(produtoForm.getTemperaturaIndicada())
                .build();
        this.produtoService.salvar(novoProduto);
        return ProdutoForm.builder()
                .codigoDoProduto(novoProduto.getCodigoDoProduto())
                .nome(novoProduto.getNome())
                .preco(novoProduto.getPreco())
                .temperaturaIndicada(produtoForm.getTemperaturaIndicada())
                .tipoProduto(novoProduto.getTipoProduto())
                .preco(novoProduto.getPreco()).build();
    }

    private RepresentanteForm payloadRepresentante(){
        return RepresentanteForm.builder()
                .codigo("R-13")
                .nome("Pedro")
                .sobrenome("Gomes")
                .endereco("rua qualquer")
                .cpf("123.234.345-04")
                .telefone("11-2473648")
                .build();
    }

    private RepresentanteForm payloadRepresentante2(){
        return RepresentanteForm.builder()
                .codigo("R-14")
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
                .codArmazem("AR-11")
                .nome("armazem central")
                .codigoRepresentante(representanteForm.getCodigo())
                .endereco("qualquer lugar")
                .numero(100)
                .uf("SP").build();
    }

    private ArmazemForm payloadArmazem2() {
        RepresentanteForm representanteForm = this.payloadRepresentante2();
        this.persisteRepresentante(representanteForm);

        return ArmazemForm.builder()
                .codArmazem("AR-12")
                .nome("armazem central")
                .codigoRepresentante(representanteForm.getCodigo())
                .endereco("qualquer lugar")
                .numero(100)
                .uf("SP").build();
    }

    private void persisteArmazem(ArmazemForm armazemForm) {
        Representante representante = this.representanteService.obter(armazemForm.getCodigoRepresentante());

        Armazem armazem = Armazem.builder()
                .codArmazem(armazemForm.getCodArmazem())
                .nome(armazemForm.getNome())
                .representante(representante)
                .endereco(armazemForm.getEndereco())
                .uf(armazemForm.getUf())
                .build();

        this.armazemService.criarArmazem(armazem);
    }

    private void persisteSetor1(SetorForm setorForm, ArmazemForm armazemForm){

        Armazem armazemSetor = this.armazemService.obterArmazem(armazemForm.getCodArmazem());

        Setor setor = Setor.builder()
                .tipoProduto(setorForm.getTipoProduto())
                .nome(setorForm.getNome())
                .armazem(armazemSetor)
                .espacoDisponivel(setorForm.getEspacoDisponivel())
                .codigo(setorForm.getCodigo()).build();

        this.setorService.salvarSetor(setor);
    }

    private void persisteSetor2(SetorForm setorForm, ArmazemForm armazemForm){

        Armazem armazemSetor = this.armazemService.obterArmazem(armazemForm.getCodArmazem());

        Setor setor = Setor.builder()
                .tipoProduto(setorForm.getTipoProduto())
                .nome(setorForm.getNome())
                .armazem(armazemSetor)
                .espacoDisponivel(setorForm.getEspacoDisponivel())
                .codigo(setorForm.getCodigo()).build();

        this.setorService.salvarSetor(setor);
    }

    private void persisteLote(LoteForm loteForm, SetorForm setorForm, ProdutoForm produtoForm) {


        Setor setorDoLote = setorService.obterSetor(setorForm.getCodigo());
        Produto produto = this.produtoService.obter(produtoForm.getCodigoDoProduto());

        Lote loteEnviado = Lote.builder()
                .setor(setorDoLote)
                .numero(loteForm.getNumero())
                .produto(produto)
                .dataDeValidade(loteForm.getDataDeValidade())
                .dataDeFabricacao(loteForm.getDataDeFabricacao())
                .horaFabricacao(loteForm.getHoraFabricacao())
                .quantidadeAtual(loteForm.getQuantidadeAtual())
                .quantidadeMinina(loteForm.getQuantidadeMinina())
                .temperaturaMinima(loteForm.getTemperaturaMinima())
                .temperaturaAtual(loteForm.getTemperaturaAtual()).build();

        loteService.salvar(loteEnviado);
    }


    private void persisteLote2(LoteForm loteForm, SetorForm setorForm, ProdutoForm produtoForm) {

        Setor setorDoLote = setorService.obterSetor(setorForm.getCodigo());
        Produto produto = this.produtoService.obter(produtoForm.getCodigoDoProduto());

        Lote loteEnviado = Lote.builder()
                .setor(setorDoLote)
                .numero(loteForm.getNumero())
                .produto(produto)
                .dataDeValidade(loteForm.getDataDeValidade())
                .dataDeFabricacao(loteForm.getDataDeFabricacao())
                .horaFabricacao(loteForm.getHoraFabricacao())
                .quantidadeAtual(loteForm.getQuantidadeAtual())
                .quantidadeMinina(loteForm.getQuantidadeMinina())
                .temperaturaMinima(loteForm.getTemperaturaMinima())
                .temperaturaAtual(loteForm.getTemperaturaAtual()).build();

        loteService.salvar(loteEnviado);
    }


    private VendedorForm payloadVendedor(VendedorForm vendedorForm) {
        Vendedor novoVendedor = Vendedor.builder().codigo(vendedorForm.getCodigo()).build();
        novoVendedor.setNome( vendedorForm.getNome());
        this.vendedorService.salvar(novoVendedor);
        return VendedorForm.builder()
                .codigo(novoVendedor.getCodigo())
                .nome(novoVendedor.getNome())
                .build();
    }


    private void persisteOrdem(OrdemDeEntradaForm ordemDeEntradaForm, SetorForm setorForm, LoteForm loteForm, VendedorForm vendedorForm){
        Representante representante = representanteService.obter(ordemDeEntradaForm.getCodigoRepresentante());
        Vendedor vendedor = vendedorService.obter(vendedorForm.getCodigo());
        Setor setor = setorService.obterSetor(setorForm.getCodigo());
        Lote lote = loteService.obter(loteForm.getNumero());

        OrdemDeEntrada ordemDeEntrada = OrdemDeEntrada.builder()
                .vendedor(vendedor).representante(representante).setor(setor).lote(lote)
                .dataDaOrdem(ordemDeEntradaForm.getDataOrdem())
                .quantidade(ordemDeEntradaForm.getQtdLotes())
                .numeroDaOrdem(ordemDeEntradaForm.getNumeroOrdem()).build();

        ordemDeEntradaService.registra(ordemDeEntrada);
    }

    @Test
    public void deveRegistrarUmaOrdemDeEntrada() throws Exception{
        ProdutoForm produtoForm = ProdutoForm.builder()
                .tipoProduto(TipoProduto.FRESCOS).nome("carne").codigoDoProduto(23)
                .preco(20.0).temperaturaIndicada(13.1).build();

        ArmazemForm armazemForm = this.payloadArmazem();
        this.persisteArmazem(armazemForm);

        SetorForm setorForm = SetorForm.builder().codigo("Se-1").nome("central")
                .codigoArmazem(armazemForm.getCodArmazem()).tipoProduto(TipoProduto.FRESCOS).espacoDisponivel(10).build();

        this.persisteSetor1(setorForm, armazemForm);
        ProdutoForm produto = this.payloadProduto(produtoForm);

        LoteForm loteForm = LoteForm.builder()
                .numero(15).codigoSetor(setorForm.getCodigo()).temperaturaAtual(17.0)
                .temperaturaMinima(13.1).quantidadeMinina(2).quantidadeAtual(3)
                .codigoProduto(produto.getCodigoDoProduto()).horaFabricacao(LocalTime.now())
                .dataDeValidade(LocalDate.of(2021, 12, 20))
                .dataDeFabricacao(LocalDate.now()).build();

        this.persisteLote(loteForm, setorForm, produtoForm);

        VendedorForm vendedor = VendedorForm.builder().nome("João").codigo("V-4").build();
        this.payloadVendedor(vendedor);

        OrdemDeEntradaForm ordemDeEntrada = OrdemDeEntradaForm.builder()
                .dataOrdem(LocalDate.now())
                .codigoRepresentante(armazemForm.getCodigoRepresentante())
                .codigoSetor(loteForm.getCodigoSetor())
                .numeroOrdem(456)
                .codigoVendedor(vendedor.getCodigo())
                .codigoLote(loteForm.getNumero())
                .qtdLotes(2)
                .build();



        String requestPayload = objectMapper.writeValueAsString(ordemDeEntrada);

        this.mockMvc.perform(post("http://localhost:8080/api/ordem-entrada/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isCreated());

    }



    @Test
    public void deveAlterarUmaOrdemDeEntrada() throws Exception {

        ProdutoForm produtoForm = ProdutoForm.builder()
                .tipoProduto(TipoProduto.REFRIGERADOS).nome("sorvete").codigoDoProduto(43)
                .preco(20.0).temperaturaIndicada(14.1).build();

        ProdutoForm produto = this.payloadProduto(produtoForm);

        ArmazemForm armazemForm = this.payloadArmazem2();
        this.persisteArmazem(armazemForm);

        SetorForm setorForm = SetorForm.builder().codigo("Se-2").nome("central")
                .codigoArmazem(armazemForm.getCodArmazem()).tipoProduto(TipoProduto.FRESCOS).espacoDisponivel(10).build();
        this.persisteSetor2(setorForm, armazemForm);

        LoteForm loteForm = LoteForm.builder()
                .numero(17).codigoSetor(setorForm.getCodigo()).temperaturaAtual(17.0)
                .temperaturaMinima(13.1).quantidadeMinina(2).quantidadeAtual(3)
                .codigoProduto(produto.getCodigoDoProduto()).horaFabricacao(LocalTime.now())
                .dataDeValidade(LocalDate.of(2021, 12, 20))
                .dataDeFabricacao(LocalDate.now()).build();

        this.persisteLote(loteForm, setorForm, produtoForm);

        VendedorForm vendedor = VendedorForm.builder().nome("João").codigo("V-2").build();
        this.payloadVendedor(vendedor);

        OrdemDeEntradaForm ordemDeEntrada = OrdemDeEntradaForm.builder()
                        .numeroOrdem(3)
                        .codigoSetor(setorForm.getCodigo())
                        .codigoLote(loteForm.getNumero())
                        .dataOrdem(LocalDate.now())
                        .qtdLotes(2)
                        .codigoRepresentante(armazemForm.getCodigoRepresentante())
                        .codigoVendedor(vendedor.getCodigo()).build();

        this.persisteOrdem(ordemDeEntrada, setorForm, loteForm, vendedor);


        OrdemDeEntradaForm ordemDeEntradaAlterada = OrdemDeEntradaForm.builder()
                .numeroOrdem(3)
                .codigoSetor(setorForm.getCodigo())
                .codigoLote(loteForm.getNumero())
                .dataOrdem(LocalDate.now())
                .codigoRepresentante(armazemForm.getCodigoRepresentante())
                .qtdLotes(4)
                .codigoVendedor(vendedor.getCodigo()).build();

        String requestPayload = objectMapper.writeValueAsString(ordemDeEntradaAlterada);

        this.mockMvc.perform(put("http://localhost:8080/api/ordem-entrada/alterar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                        .andExpect(status().isCreated());

    }


}
