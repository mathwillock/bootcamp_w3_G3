package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.ItensForm;
import com.bootcamp_w3_g3.model.entity.Itens;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPong {


    @GetMapping("ping")
    public String pingPong() {
        return "Pong";
    }




}
