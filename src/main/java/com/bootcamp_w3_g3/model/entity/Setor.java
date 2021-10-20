package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hugo damm
 */

@Setter
@Getter
public class Setor {

    private String id;
    private String nome;
    private String tipoProduto;
    private double temperaturaMin;
    private double temperaturaMax;
    private Dimensao dimensoes;
    private Representante representante;

    public Setor(){}

    public Setor(String id, String nome, String tipoProduto, double temperaturaMin, double temperaturaMax, Dimensao dimensoes, Representante representante ) {
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
        this.dimensoes = dimensoes;
        this.representante = representante;
    }

    @Autowired
    public Setor(String nome, String tipoProduto, double temperaturaMin, double temperaturaMax, Dimensao dimensoes, Representante representante) {
    }
}