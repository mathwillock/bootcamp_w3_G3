package com.bootcamp_w3_g3.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
public class Armazem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer numero;
    private String nome;
    private String endereco;
    private String uf;

    @OneToOne
    private Representante representantesValidos;
    @OneToMany
    private List<Setor> setoresDoArmazem;

    public Armazem() {
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
