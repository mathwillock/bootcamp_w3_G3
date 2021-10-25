package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.EstoqueForm;
import com.bootcamp_w3_g3.model.dtos.response.EstoqueDTO;
import com.bootcamp_w3_g3.model.entity.Estoque;
import com.bootcamp_w3_g3.service.EstoqueService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Marcelo de Oliveira Santos
 *
 * @implNote Nao serao criados objetos somente para retorno.
 */

@RestController
@RequestMapping(value="/estoques")
@Getter
public class EstoqueController {


    private EstoqueService estoqueService;
    private EstoqueForm estoqueForm;
    private EstoqueDTO estoqueDTO;


    @Autowired
    private EstoqueController(){}

    private EstoqueController(EstoqueService estoqueService, EstoqueForm estoqueForm, EstoqueDTO estoqueDTO){
        this.estoqueService = estoqueService;
        this.estoqueForm = estoqueForm;
        this.estoqueDTO = estoqueDTO;
    }

    /**
     * Create do CRUD
     * @param estoqueForm
     * @return EstoqueDTO
     */
    @PostMapping(value = "/cadastra")
    public ResponseEntity<EstoqueDTO> cadastrar(@RequestBody EstoqueForm estoqueForm)
    {
        return new ResponseEntity<>
                (
                        estoqueDTO.converter
                                (
                                        estoqueService.salvar
                                                (
                                                        estoqueForm.converte()
                                                )
                                ),      HttpStatus.CREATED
                );
    }

    /**
     * Read do CRUD
     * @param cod_prod
     * @return estoqueDTO
     */
    @GetMapping("/obter")
    public ResponseEntity<EstoqueDTO> obter(@RequestParam Integer cod_prod)
    {
        return new ResponseEntity<>
                (
                        estoqueDTO.converter
                                (
                                        estoqueService.obter(cod_prod)
                                ),      HttpStatus.OK
                );
    }

    /**
     * Put do CRUD
     * @param cod_prod
     * @param estoque
     * @return estoqueDTO
     */
    @PutMapping("/alterar")
    public ResponseEntity<EstoqueDTO> alterar(@RequestParam Integer cod_prod, EstoqueDTO estoque)
    {
        Estoque editedEstoque = (estoqueService.obter(cod_prod));

        editedEstoque.setCodEstoque(estoque.getCodEstoque());
        editedEstoque.setTipoDeProduto(estoque.getTipoDeProduto());
        editedEstoque.setQuantidade(estoque.getQuantidade());

        return new ResponseEntity<>
                (
                        estoqueDTO.converter(editedEstoque),
                        HttpStatus.OK
                );
    }

    /**
     * Delete do CRUD
     * @param cod_prod
     * @return estoqueDTO
     */
    @DeleteMapping(value="/deletar")
    public void cadastro(@RequestParam Integer cod_prod)
    {
        estoqueService.apagar(cod_prod);
    }

    /**
     * GET de todos os Estoques
     * @return List<EstoqueDTO>
     */
    @GetMapping("/listar")
    public List<EstoqueDTO> listar()
    {
        return estoqueDTO.converterLista(estoqueService.listar());
    }
}
