package com.bootcamp_w3_g3.controller;
import com.bootcamp_w3_g3.model.dtos.request.ProdutoForm;
import com.bootcamp_w3_g3.model.dtos.response.LoteDTO;
import com.bootcamp_w3_g3.model.dtos.response.ProdutoDTO;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
import com.bootcamp_w3_g3.service.CarrinhoService;
import com.bootcamp_w3_g3.service.LoteService;
import com.bootcamp_w3_g3.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Marcelo de Oliveira Santos
 * @implNote Nao serão criados somente para retorno.
 */

@RestController
@RequestMapping(value="produtos/")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LoteService loteService;

    @Autowired
    private CarrinhoService carrinhoService;


    /**
     * Create do CRUD
     * @return ProdutoDTO
     */
    @PostMapping(value = "/cadastra")
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody ProdutoForm produtoForm) {
       Produto produto = produtoService.salvar(produtoForm.convert());
       return new ResponseEntity<>(ProdutoDTO.convertEmProdutoDTO(produto), HttpStatus.CREATED);
    }

    /**
     * Read do CRUD
     * @return produtoDTO
     */
    @GetMapping("/obter/{cod}")
    public ResponseEntity<ProdutoDTO> obter(@PathVariable Integer cod) {
        Produto produto = produtoService.obter(cod);
        return new ResponseEntity<>(ProdutoDTO.convertEmProdutoDTO(produto), HttpStatus.OK);
    }

    /**
     * Put do CRUD
     * @return Produtodto
     */
    @PutMapping("/alterar")
    public ResponseEntity<ProdutoDTO> alterar(@RequestBody ProdutoForm produtoForm) {
        Produto produto = produtoService.atualizar(produtoForm.convert());
        return new ResponseEntity<>(ProdutoDTO.convertEmProdutoDTO(produto), HttpStatus.OK);
    }

    /**
     * Delete do CRUD
     * @return produtoDTO
     */
    @DeleteMapping(value="/deletar/{cod}")
    public ResponseEntity<Produto> apagar(@PathVariable Long cod) {
        produtoService.apagar(cod);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * GET de todos os Produtos
     * @return List<ProdutoDTO>
     */
    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDTO>> listar() {
        try {
            List<Produto> produtos = produtoService.listar();
            return new ResponseEntity<>(ProdutoDTO.convert(produtos), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     *endpoint deve listar todos os produtos da
     * mesma categoria.
     * @autor Joaquim Borges
     */
    @GetMapping("/listar/{categoria}")
    public ResponseEntity<List<Produto>> listarPorCategoria(@PathVariable TipoProduto categoria) {
        try {
            return new ResponseEntity<>(produtoService.listarPorCategoria(categoria), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *endpoint deve listar todos os produtos
     * contidos em um carrinho especifico.
     * @autor Joaquim Borges
     */
    @GetMapping("/carrinho/{idCarrinho}")
    public ResponseEntity<List<Produto>> mostrarProdutosDoPedido(@PathVariable Long idCarrinho) {
        return new ResponseEntity<>(carrinhoService.mostrarProdutosDoPedido(idCarrinho), HttpStatus.OK);
    }

    /**
     *endpoint para consultar um produto em stock
     * saber sua localização no setor e diferentes lotes
     * @autor Joaquim Borges
     */
    @GetMapping("/lotes/listar/{codProduto}")
    public ResponseEntity<List<LoteDTO>> retornaLotesDoProduto(@PathVariable Integer codProduto) {
        try {
            List<Lote> lotes = loteService.retornaLotesDoProduto(codProduto);
            return new ResponseEntity<>(LoteDTO.converterLista(lotes), HttpStatus.OK) ;
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @param codProduto
     * @param tipoDeOrdenacao (Lote, quantidade, vencimento)
     * @return Lista de lotes ordenados.
     * @author Matheus Willock
     */
    @GetMapping("/lotes/lista-ordem/{codProduto}/{tipoDeOrdenacao}")
    public ResponseEntity<List<LoteDTO>> ordenandoLotes(
            @PathVariable Integer codProduto,  @PathVariable String tipoDeOrdenacao)
    {

        List<Lote> lotes = loteService.retornaLotesDoProdutoOrdenados(codProduto, tipoDeOrdenacao);

        return new ResponseEntity<>(LoteDTO.converterLista(lotes), HttpStatus.OK);
    }

}