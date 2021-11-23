package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.ArmazemForm;
import com.bootcamp_w3_g3.model.dtos.response.ArmazemDTO;
import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.service.ArmazemService;
import com.bootcamp_w3_g3.service.RepresentanteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("armazem")
@Api(description = "Conjunto de endpoints para criação, recuperação, atualização e exclusão de Armazéns.")
public class ArmazemController {

    @Autowired
    private ArmazemService armazemService;

    @Autowired
    private RepresentanteService representanteService;

    @PostMapping("/criar")
    @ApiOperation("Criar um novo armazem.")
    public ResponseEntity<ArmazemDTO> criarArmazem(
            @ApiParam("Informações do armazem para um novo armazem a ser criado.")
            @RequestBody ArmazemForm armazemForm) {
        Armazem armazem = armazemService.criarArmazem(armazemForm.converte(representanteService));

        return new ResponseEntity<>(ArmazemDTO.converter(armazem),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/listar")
    @ApiOperation("Retorna lista de todos os armazens no sistema.")
    public ResponseEntity<List<ArmazemDTO>> listar() {
        List<Armazem> armazemList = armazemService.listarArmazens();

        return new ResponseEntity<>(
                ArmazemDTO.armazemDTOListConverte(armazemList),
                HttpStatus.OK
        );

    }

    @GetMapping("/obter/{codigo}")
    @ApiOperation("Retorna um armazem específico por seu identificador. Erro 404 se não existir.")
    public ResponseEntity<ArmazemDTO> obterArmazem(@PathVariable String codigo) {
        Armazem armazem = armazemService.obterArmazem(codigo);

        return new ResponseEntity<>(ArmazemDTO.converter(armazem), HttpStatus.OK);
    }

    @PutMapping("/atualizar")
    @ApiOperation("Atualiza as informações do armazem cadastrado.")
    public ResponseEntity<ArmazemDTO> atualizarArmazem(@RequestBody ArmazemForm armazemForm) {
        Armazem armazem = armazemService.atualizarArmazem(armazemForm.converte(representanteService));


        return new ResponseEntity<>(ArmazemDTO.converter(armazem), HttpStatus.OK);
    }


}
