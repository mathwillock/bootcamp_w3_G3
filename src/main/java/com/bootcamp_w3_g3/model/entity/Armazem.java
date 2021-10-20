package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Armazem {

    private long id;
    private String nome;
    private String endereco;
    private String uf;


    public Armazem() {
    }

    public Armazem(String nome, String endereco, String uf) {
        this.nome = nome;
        this.endereco = endereco;
        this.uf = uf;
    }

    public Armazem(long id, String nome, String endereco, String uf) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.uf = uf;
    }
}
