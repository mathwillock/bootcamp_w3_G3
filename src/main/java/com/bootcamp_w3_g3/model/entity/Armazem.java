package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Armazem {

    private long id;
    private String nome;
    private String endereco;
    private String uf;
    private List<Representante> RepresentantesValidos;
    private List<Setor> SetoresDoArmazem;

    public Armazem() {
    }

    public Armazem(String nome, String endereco, String uf, List<Representante> representantesValidos, List<Setor> setoresDoArmazem) {
        this.nome = nome;
        this.endereco = endereco;
        this.uf = uf;
        RepresentantesValidos = representantesValidos;
        SetoresDoArmazem = setoresDoArmazem;
    }

    public Armazem(long id, String nome, String endereco, String uf, List<Representante> representantesValidos, List<Setor> setoresDoArmazem) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.uf = uf;
        RepresentantesValidos = representantesValidos;
        SetoresDoArmazem = setoresDoArmazem;
    }

    @Override
    public String toString() {
        return "Armazem{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
