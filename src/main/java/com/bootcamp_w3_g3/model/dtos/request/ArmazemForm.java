package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;

import java.util.List;

public class ArmazemForm {

    private String nome;
    private String endereco;
    private String uf;
    private List<Representante> RepresentantesValidos;
    private List<Setor> SetoresDoArmazem;

    public ArmazemForm() {
    }

    public ArmazemForm(String nome, String endereco, String uf, List<Representante> representantesValidos, List<Setor> setoresDoArmazem) {
        this.nome = nome;
        this.endereco = endereco;
        this.uf = uf;
        RepresentantesValidos = representantesValidos;
        SetoresDoArmazem = setoresDoArmazem;
    }

    public Armazem converte() {
        return new Armazem(nome, endereco, uf, RepresentantesValidos, SetoresDoArmazem);
    }

}
