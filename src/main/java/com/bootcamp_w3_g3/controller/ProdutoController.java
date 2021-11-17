package com.bootcamp_w3_g3.controller;
import com.bootcamp_w3_g3.model.dtos.request.ProdutoForm;
import com.bootcamp_w3_g3.model.dtos.response.LoteDTO;
import com.bootcamp_w3_g3.model.dtos.response.ProdutoDTO;
import com.bootcamp_w3_g3.model.dtos.response.requisito4.DTOArmazem;
import com.bootcamp_w3_g3.model.dtos.response.requisito5.DTOLote;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
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
     * @author Joaquim Borges
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
     *endpoint para consultar um produto em stock
     * saber sua localização no setor e diferentes lotes
     * @author Joaquim Borges
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
     * @param codProduto (codigoDoProduto)
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

    /**
     * @param codProduto (codigoDoProduto)
     * @return quantidade total de Produtos por armazém
     * @author Hugo Damm
     */
    @GetMapping(value = "listar/armazem/{codProduto}")
    public ResponseEntity<List<DTOArmazem>> quantidadesProdutosPorArmazem(@PathVariable Integer codProduto){

        return new ResponseEntity<>(loteService.retornaQuantidadesDoProdutosPorArmazem(codProduto), HttpStatus.OK);
    }

    /**
     *
     * @param codSetor (códigoDoSetpr )
     * @param dias (passammos a quantidade de dias de vencimento que desejamos que o produto tenha a vencer.)
     * @return retorna uma lista de setores que contêm os produtos que estão no setor especifico e que contêm
     * o vencimento igual ou menor a qtd de Dias que passamos anteriormente.
     *
     * @author Matheus Willock
     */
    @GetMapping("/lotes/validade/{codSetor}/{dias}")
    public ResponseEntity<List<DTOLote>> lotesPorVencimento(@PathVariable String codSetor, @PathVariable Integer dias) {
        return new ResponseEntity<>(loteService.retornaLotesArmazenadosDoProduto(codSetor, dias), HttpStatus.OK);
    }



    /**
     *
     * @param tipoProduto (Tipo de produto. deve ser pertinente a ENUMERACAO)
     * @param dias (Dias a vencer)
     * @return uma lista de lotes que vencem até os dias passados.
     *
     * usamos @RequestParam para as variaveis supracitadas.
     * @author Marcelo de Oliveira
     */

    @GetMapping("/lotes/validade")

    public ResponseEntity<List<DTOLote>> LotesPorCategoria (@RequestParam TipoProduto tipoProduto, @RequestParam Integer dias)
    {
        return new ResponseEntity<>(loteService.retornarLotesPorCategoria(tipoProduto, dias), HttpStatus.OK);
    }

}