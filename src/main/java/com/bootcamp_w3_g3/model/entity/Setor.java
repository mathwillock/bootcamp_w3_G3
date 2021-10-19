package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hugo damm
 */

@Setter
@Getter
public class Setor extends Dimensao {

    private String id;
    private String nome;
    private String tipoProduto;
    private float temperaturaMin;
    private float temperaturaMax;
    private Representante representante;

    public Setor() {
    }

    public Setor(Double comprimento, Double largura, Double altura, String id, String nome, String tipoProduto, float temperaturaMin, float temperaturaMax, Representante representante ) {
        super(comprimento, largura, altura);
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
        this.representante = representante;
    }

    @Autowired
    public Setor(String nome, String tipoProduto, float temperaturaMin, float temperaturaMax, Dimensao dimensao, Representante representante) {
    }
}