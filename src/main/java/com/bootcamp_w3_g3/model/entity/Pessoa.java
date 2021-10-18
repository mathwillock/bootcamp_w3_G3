package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Pessoa {

    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, String sobrenome, String cpf, String telefone, String endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

}
