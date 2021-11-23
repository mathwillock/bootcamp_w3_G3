package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.VendedorForm;
import com.bootcamp_w3_g3.model.dtos.response.VendedorDTO;
import com.bootcamp_w3_g3.model.entity.Vendedor;
import com.bootcamp_w3_g3.service.VendedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hugo damm
 */

@RestController
@RequestMapping("vendedor/")
@Api(description = "Conjunto de endpoints para criação, recuperação, atualização e exclusão de Vendedores.")
public class VendedorController {

    @Autowired
    VendedorService vendedorService;

    @PostMapping("/salvar")
    @ApiOperation("Criar um novo vendedor.")
    public ResponseEntity<VendedorDTO> salvar(
            @ApiParam("Informações do vendedor para um novo vendedor a ser criado.")
            @RequestBody VendedorForm vendedorForm){
        Vendedor vendedor = vendedorService.salvar(vendedorForm.converte());
        return new ResponseEntity<>(VendedorDTO.converter(vendedor), HttpStatus.CREATED);
    }

    @GetMapping("/obter/{codigo}")
    @ApiOperation("Retorna um vendedor específico por seu identificador. Erro 404 se não existir.")
    public ResponseEntity<VendedorDTO> obter(@PathVariable String codigo){
        Vendedor vendedor = vendedorService.obter(
                codigo);
        return new ResponseEntity<>(VendedorDTO.converter(vendedor), HttpStatus.OK);
    }

    @GetMapping("/listar")
    @ApiOperation("Retorna lista de todos os Vendedores no sistema.")
    public ResponseEntity<List<VendedorDTO>> listar(){
        List<Vendedor> vendedores = vendedorService.listar();
        return new ResponseEntity<>(VendedorDTO.converteLista(vendedores), HttpStatus.OK);
    }

    @PutMapping("/alterar")
    @ApiOperation("Atualiza as informações do vendedor cadastrado.")
    public ResponseEntity<VendedorDTO> alterar(@RequestBody VendedorForm vendedorForm){
        Vendedor vendedor = vendedorService.atualizar(vendedorForm.converte());
        return new ResponseEntity<>(VendedorDTO.converter(vendedor), HttpStatus.OK);
    }

}
