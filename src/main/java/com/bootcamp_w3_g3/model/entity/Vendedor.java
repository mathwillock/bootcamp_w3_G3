package com.bootcamp_w3_g3.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author hugo damm
 */


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String codigo;



    public Vendedor(String nome, String sobrenome, String cpf, String telefone, String endereco) {

        codigo = geraCodigo(nome, sobrenome);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }


    private String geraCodigo(String nome, String sobrenome)
    {
        return "MLVEND_" + (int)Math.floor(Math.random()*100000);
    }


}
