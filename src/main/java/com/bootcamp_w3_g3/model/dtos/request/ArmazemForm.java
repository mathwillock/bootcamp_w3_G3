package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@AllArgsConstructor

@NoArgsConstructor
public class ArmazemForm {

    private long codArmazem;
    private String nome;
    private String endereco;
    private Integer numero;
    private String uf;

    private Representante representante;

    private List<Setor> setoresDoArmazem;

    public Armazem converte() {


        return Armazem.builder()
                .codArmazem(codArmazem)
                .nome(nome)
                .endereco(endereco)
                .uf(uf)
                .representante(representante)
                .SetoresDoArmazem(setoresDoArmazem)
                .build()
        ;


    }

}
