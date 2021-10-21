package com.bootcamp_w3_g3.controller;


import com.bootcamp_w3_g3.model.dtos.request.LoteForm;
import com.bootcamp_w3_g3.model.dtos.response.LoteDTO;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lote/")
public class LoteController {

    @Autowired
    private LoteService loteService;

    @PostMapping("/salvar")
    public ResponseEntity<LoteDTO> salvar(@RequestBody LoteForm loteForm) {
        Lote lote = loteForm.converte();
        return new ResponseEntity<>(LoteDTO.converter(lote), HttpStatus.CREATED);
    }
}
