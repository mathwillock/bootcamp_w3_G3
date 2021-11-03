package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.response.RepresentanteDTO;
import com.bootcamp_w3_g3.model.entity.Carrinho;
import com.bootcamp_w3_g3.model.entity.Itens;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.service.CarrinhoService;
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
@RequestMapping("carrinho/")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/salvar")
    public ResponseEntity<CarrinhoDTO> cadastrar(@RequestBody CarrinhoForm carrinhoForm) {
        Carrinho carrinho = carrinhoService.salvar(carrinhoForm.convert());
        return new ResponseEntity<>(CarrinhoDTO.convertToDTO(carrinho), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CarrinhoDTO>> listar(){
        List<Carrinho> carrinhoList = carrinhoService.listar();
        return new ResponseEntity<>(CarrinhoDTO.converteListToItensDTO(carrinhoList), HttpStatus.OK);
    }

    @PutMapping("/alterar")
    public ResponseEntity<CarrinhoDTO> alterar(@RequestBody CarrinhoForm carrinhoForm) {
        Carrinho carrinho = carrinhoService.atualizar(carrinhoForm.convert());
        return new ResponseEntity<>(CarrinhoDTO.converteEmCarrinhoDTO(carrinho), HttpStatus.OK);
    }
}

