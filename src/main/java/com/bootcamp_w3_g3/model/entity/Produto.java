package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @autor Alex Cruz
 */

@Getter
@Setter
public class Produto {

    private Integer codigoDoProduto;
    private String nome;
    private BigDecimal preco;
    private LocalDate dataDeValidadae;
    private Double temperaturaIndicada;
    private Dimensao dimensoes;

    public Produto(){}

    public Produto(Integer codigoDoProduto, String nome, BigDecimal preco, LocalDate dataDeValidadae, Double temperaturaIndicada, Dimensao dimensoes) {
        this.codigoDoProduto = codigoDoProduto;
        this.nome = nome;
        this.preco = preco;
        this.dataDeValidadae = dataDeValidadae;
        this.temperaturaIndicada = temperaturaIndicada;
        this.dimensoes = dimensoes;
    }


}
