package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Armazem;

public class ArmazemForm {

    private String nome;
    private String endereco;
    private String uf;


    public ArmazemForm() {
    }

    public ArmazemForm(String nome, String endereco, String uf) {
        this.nome = nome;
        this.endereco = endereco;
        this.uf = uf;
    }

    public Armazem converte() {
        return new Armazem(nome, endereco, uf);
    }

}
