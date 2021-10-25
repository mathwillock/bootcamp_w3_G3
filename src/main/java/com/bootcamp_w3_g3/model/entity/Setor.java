package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * @author hugo damm
 */

@Setter
@Getter
@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private String tipoProduto;
    @Embedded
    private Dimensao dimensoes;
    @OneToOne
    private Representante representante;

    public Setor(){}


    public Setor(String codigo, String nome, String tipoProduto, Double temperaturaMin, Double temperaturaMax, Dimensao dimensoes, Representante representante ) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.dimensoes = dimensoes;
        this.representante = representante;
    }

    public Setor(String nome, String tipoProduto, Dimensao dimensoes, Representante representante) {
    }




    public Setor(String codigo){
        this.codigo = codigo;
    }


}