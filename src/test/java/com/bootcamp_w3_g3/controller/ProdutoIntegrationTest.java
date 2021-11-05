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

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private ProdutoForm payloadProduto() {
        return ProdutoForm.builder()
                .codigoDoProduto(111)
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
                .codigoDoProduto(441)
                .nome("refri")
                .preco(50.0)
                .tipoProduto(TipoProduto.REFRIGERADOS)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto4() {
        return ProdutoForm.builder()
                .codigoDoProduto(551)
                .nome("carne seca")
                .preco(50.0)
                .tipoProduto(TipoProduto.FRESCOS)
                .temperaturaIndicada(16.0)
                .build();
    }

    private ProdutoForm payloadProduto5() {
        return ProdutoForm.builder()
                .codigoDoProduto(55)
                .nome("carne")
                .preco(50.0)
                .tipoProduto(TipoProduto.FRESCOS)
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
                .codigoDoProduto(441)
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
                .codigoDoProduto(333)
                .nome("carne seca")
                .preco(60.0)
                .temperaturaIndicada(16.0)
                .build();

        this.persisteProduto(produto);

        this.mockMvc.perform(get("http://localhost:8080/produtos/obter/" + produto.getCodigoDoProduto()))
                .andExpect(status().isForbidden());
    }


}
