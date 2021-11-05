package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.SetorForm;
import com.bootcamp_w3_g3.model.dtos.response.ArmazemDTO;
import com.bootcamp_w3_g3.model.dtos.response.SetorDTO;
import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Setor;
import com.bootcamp_w3_g3.service.ArmazemService;
import com.bootcamp_w3_g3.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author hugo damm
 */
@RestController
@RequestMapping("setor/")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @Autowired
    private ArmazemService armazemService;

    @PostMapping("/salvar")
    public ResponseEntity<SetorDTO> salvar(@RequestBody SetorForm setorForm){
        Setor setor = setorService.salvarSetor(setorForm.converte(armazemService));
        return new ResponseEntity<>(SetorDTO.converter(setor), HttpStatus.CREATED);
    }

    @DeleteMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        setorService.removerSetor(id);

        return "Setor removido";
    }

    @GetMapping("/obter/{codigo}")
    public ResponseEntity<SetorDTO> obter(@PathVariable String codigo){
        Setor setor = setorService.obterSetor(codigo);
        return new ResponseEntity<>(SetorDTO.converter(setor), HttpStatus.OK);
    }

    @GetMapping("/buscar-armazem/{codigo}")
    public ResponseEntity<ArmazemDTO> buscarArmazem(@PathVariable String codigo){

        Armazem armazem = setorService.retornaArmazem(codigo);

        return new ResponseEntity<>(ArmazemDTO.converter(armazem), HttpStatus.OK);
    }

    @PutMapping("/alterar")
    public ResponseEntity<SetorDTO> alterar(@RequestBody SetorForm setorForm){
        Setor setor = setorService.atualizarSetor(setorForm.converte(armazemService));
        return new ResponseEntity<>(SetorDTO.converter(setor), HttpStatus.OK);
    }





}
