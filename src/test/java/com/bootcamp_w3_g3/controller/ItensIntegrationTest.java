package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.ItensForm;
import com.bootcamp_w3_g3.model.dtos.request.ProdutoForm;
import com.bootcamp_w3_g3.model.entity.Itens;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
import com.bootcamp_w3_g3.service.ItensService;
import com.bootcamp_w3_g3.service.ProdutoService;
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


/**
 * @autor Joaquim Borges
 */

@SpringBootTest
@AutoConfigureMockMvc
public class ItensIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItensService itensService;

    @Autowired
    private ProdutoService produtoService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }


    private ProdutoForm payloadProduto() {
        return ProdutoForm.builder()
                .codigoDoProduto(20)
                .nome("carne seca")
                .tipoProduto(TipoProduto.FRESCOS)
                .preco(50.0)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto2() {
        return ProdutoForm.builder()
                .codigoDoProduto(21)
                .nome("sorvete")
                .tipoProduto(TipoProduto.CONGELADOS)
                .preco(50.0)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto3() {
        return ProdutoForm.builder()
                .codigoDoProduto(22)
                .nome("refri")
                .preco(50.0)
                .tipoProduto(TipoProduto.REFRIGERADOS)
                .temperaturaIndicada(16.0)
                .build();
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

    private void persisteItem(ItensForm itensForm){
        Produto produto = produtoService.obter(itensForm.getCodigoDoProduto());

        Itens itens = Itens.builder().produto(produto)
                .quantidade(itensForm.getQuantidade()).build();

        itensService.salvar(itens);
    }


    /**
     * Teste deve cadastrar itens para adicionar ao carrinho
     */
    @Test
    void deveCadastrarItem() throws Exception {
        ProdutoForm produtoForm = this.payloadProduto();
        this.persisteProduto(produtoForm);

        ItensForm itensForm = ItensForm.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .quantidade(1).build();

        String requestPayload = objectMapper.writeValueAsString(itensForm);
        this.mockMvc.perform(post("http://localhost:8080/itens/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload))
                .andExpect(status().isCreated());
    }


    /**
     * Teste deve listar todos os itens cadastrados
     */
    @Test
    void deveListarTodosItens() throws Exception {
        ProdutoForm produtoForm = this.payloadProduto2();
        this.persisteProduto(produtoForm);

        ItensForm itensForm = ItensForm.builder()
                .codigoDoProduto(produtoForm.getCodigoDoProduto())
                .quantidade(2).build();

        this.persisteItem(itensForm);

        this.mockMvc.perform(get("http://localhost:8080/itens/listar"))
                .andExpect(status().isOk());
    }





}
