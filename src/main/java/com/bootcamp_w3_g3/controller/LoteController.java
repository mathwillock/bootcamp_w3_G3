package com.bootcamp_w3_g3.controller;


import com.bootcamp_w3_g3.model.dtos.request.LoteForm;
import com.bootcamp_w3_g3.model.dtos.response.LoteDTO;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lote/")
public class LoteController {

    @Autowired
    private LoteService loteService;

    @PostMapping("/salvar")
    public ResponseEntity<LoteDTO> salvar(@RequestBody LoteForm loteForm) {
        Lote lote = loteService.salvar(loteForm.converte());
        return new ResponseEntity<>(LoteDTO.converter(lote), HttpStatus.CREATED);
    }

    @GetMapping("/obter/{id}")
    public ResponseEntity<LoteDTO> obter(@PathVariable long id) {
        Lote lote = loteService.obter(id);
        return new ResponseEntity<>(LoteDTO.converter(lote), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LoteDTO>> listar(){
        List<Lote> lotes = loteService.listar();
        return new ResponseEntity<>(LoteDTO.converterLista(lotes), HttpStatus.OK);
    }

    @PutMapping("/alterar")
    public ResponseEntity<LoteDTO> alterar(@RequestBody LoteForm loteForm) {
        Lote lote = loteService.atualizar(loteForm.converte());
        return new ResponseEntity<>(LoteDTO.converter(lote), HttpStatus.OK);
    }




}
