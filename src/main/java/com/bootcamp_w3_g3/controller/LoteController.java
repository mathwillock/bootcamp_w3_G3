package com.bootcamp_w3_g3.controller;


import com.bootcamp_w3_g3.model.dtos.request.LoteForm;
import com.bootcamp_w3_g3.model.dtos.response.LoteDTO;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.service.LoteService;
import com.bootcamp_w3_g3.service.ProdutoService;
import com.bootcamp_w3_g3.service.SetorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("lote/")
@Api(description = "Conjunto de endpoints para criação, recuperação, atualização e exclusão de Lotes.")
public class LoteController {

    @Autowired
    private SetorService setorService;

    @Autowired
    private LoteService loteService;

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/salvar")
    @ApiOperation("Criar um novo lote.")
    public ResponseEntity<LoteDTO> salvar(
            @ApiParam("Informações do lote para um novo lote a ser criado.")
            @RequestBody LoteForm loteForm) {
        Lote lote = loteService.salvar(loteForm.converte(produtoService, setorService));
        return new ResponseEntity<>(LoteDTO.converter(lote), HttpStatus.CREATED);
    }

    @GetMapping("/obter/{numero}")
    @ApiOperation("Retorna um lote específico por seu identificador. Erro 404 se não existir.")
    public ResponseEntity<LoteDTO> obter(@PathVariable Integer numero) {
        Lote lote = loteService.obter(numero);
        return new ResponseEntity<>(LoteDTO.converter(lote), HttpStatus.OK);
    }

    @GetMapping("/listar")
    @ApiOperation("Retorna lista de todos os lotes no sistema.")
    public ResponseEntity<List<LoteDTO>> listar(){
        List<Lote> lotes = loteService.listar();
        return new ResponseEntity<>(LoteDTO.converterLista(lotes), HttpStatus.OK);
    }

    @PutMapping("/alterar")
    @ApiOperation("Atualiza as informações do lote cadastrado.")
    public ResponseEntity<LoteDTO> alterar(@RequestBody LoteForm loteForm) {
        Lote lote = loteService.atualizar(loteForm.converte(produtoService, setorService));
        return new ResponseEntity<>(LoteDTO.converter(lote), HttpStatus.OK);
    }




}
