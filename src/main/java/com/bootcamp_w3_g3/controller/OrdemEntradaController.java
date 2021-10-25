package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.OrdemDeEntradaDTO;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.OrdemDeEntrada;
import com.bootcamp_w3_g3.service.OrdemDeEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Joaquim Borges
 */
@RestController
@RequestMapping("ordemEntrada/")
public class OrdemEntradaController {

    @Autowired
    private OrdemDeEntradaService ordemDeEntradaService;

    @PostMapping("/salvar")
    public ResponseEntity<List<Lote>> criarOrdem(@RequestBody OrdemDeEntradaDTO ordemDeEntradaDTO){
        OrdemDeEntrada ordemDeEntrada = ordemDeEntradaDTO.converterParaEntity();
        return new ResponseEntity<>(ordemDeEntradaDTO.retorna(ordemDeEntradaDTO), HttpStatus.OK);
    }
}
