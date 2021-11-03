package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.response.RepresentanteDTO;
import com.bootcamp_w3_g3.model.entity.Itens;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.service.ItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Alex Ccruz
 */

@RestController
@RequestMapping("itens/")
public class ItensController {

    @Autowired
    private ItensService itensService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ItensDTO> cadastrar(@RequestBody ItensForm itensForm) {
        Itens itens = itensService.salvar(itensForm.convert());
        return new ResponseEntity<>(ItensDTO.convertToDTO(itens), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ItensDTO>> listar(){
        List<Itens> itensList = itensService.listar();
        return new ResponseEntity<>(ItensDTO.converteListToItensDTO(itensList), HttpStatus.OK);
    }

    @PutMapping("/alterar")
    public ResponseEntity<ItensDTO> alterar(@RequestBody ItensForm itensForm) {
        Itens itens = itensService.atualizar(itensForm.convert());
        return new ResponseEntity<>(ItensDTO.converteEmItensDTO(itens), HttpStatus.OK);
    }
}
