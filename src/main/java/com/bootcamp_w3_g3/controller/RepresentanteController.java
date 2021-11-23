package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.RepresentanteForm;
import com.bootcamp_w3_g3.model.dtos.response.RepresentanteDTO;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.service.RepresentanteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("representante/")
@Api(description = "Conjunto de endpoints para criação, recuperação, atualização e exclusão de Representantes.")
public class RepresentanteController {

        @Autowired
        private RepresentanteService representanteService;

        @PostMapping("/salvar")
        @ApiOperation("Criar um novo representante.")
        public ResponseEntity<RepresentanteDTO> salvar(
                @ApiParam("Informações do representante para um novo representante a ser criado.")
                @RequestBody RepresentanteForm representanteForm) {
            Representante representante = representanteService.salvar(representanteForm.converte());
            return new ResponseEntity<>(RepresentanteDTO.converteEmRepresentanteDTO(representante), HttpStatus.CREATED);
        }

        @GetMapping("/obter/{codigo}")
        @ApiOperation("Retorna um representante específico por seu identificador. Erro 404 se não existir.")
        public ResponseEntity<RepresentanteDTO> obter(@PathVariable String codigo) {
            Representante representante = representanteService.obter(codigo);
            return new ResponseEntity<>(RepresentanteDTO.converteEmRepresentanteDTO(representante), HttpStatus.OK);
        }

        @GetMapping("/listar")
        @ApiOperation("Retorna lista de todos os Representantes no sistema.")
        public ResponseEntity<List<RepresentanteDTO>> listar(){
            List<Representante> representanteList = representanteService.listar();
            return new ResponseEntity<>(RepresentanteDTO.converteListaParaRepresentanteDTO(representanteList), HttpStatus.OK);
        }

        @PutMapping("/alterar")
        @ApiOperation("Atualiza as informações do representante cadastrado.")
        public ResponseEntity<RepresentanteDTO> alterar(@RequestBody RepresentanteForm representanteForm) {
            Representante representante = representanteService.atualizar(representanteForm.converte());
            return new ResponseEntity<>(RepresentanteDTO.converteEmRepresentanteDTO(representante), HttpStatus.OK);
        }

        @DeleteMapping(value = "/delete/{id}")
        @ApiOperation("Exclui um representante do sistema. Erro 404 se o identificador do representante não for encontrado.")
        public ResponseEntity<String> apagar(@ApiParam("Id do representante a ser excluído. Não pode estar vazio.")
                                             @PathVariable Long id) {
                representanteService.apagar(id);
            return new ResponseEntity<>("Representante deletado com sucesso", HttpStatus.OK);
        }
    }
