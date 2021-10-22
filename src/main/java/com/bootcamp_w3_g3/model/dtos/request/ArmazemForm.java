package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@AllArgsConstructor
public class ArmazemForm {

    private String nome;
    private String endereco;
    private Integer numero;
    private String uf;
    private Representante representantesValidos;
    private List<Setor> setoresDoArmazem;

    public ArmazemForm() {
    }



    public Armazem converte() {
        return Armazem.builder()
                .nome(nome)
                .numero(numero)
                .uf(uf)
                .endereco(endereco)
                .setoresDoArmazem(setoresDoArmazem)
                .representantesValidos(representantesValidos)
                .build();
    }

}
