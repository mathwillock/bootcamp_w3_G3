package com.bootcamp_w3_g3.model.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Joaquim Borges
 */

@Getter
@Setter
public class Lote {

    private Long id;
    private Integer numero;
    private LocalDate dataDeValidade;
    private Dimensao dimensao;
    private Integer quantidadeDeIntens;
    private List<Produto> produtos;

    public Lote(Integer numero, LocalDate dataDeValidade, Dimensao dimensao, Integer quantidadeDeIntens, List<Produto> produtos) {
        this.numero = numero;
        this.dataDeValidade = dataDeValidade;
        this.dimensao = dimensao;
        this.quantidadeDeIntens = quantidadeDeIntens;
        this.produtos = produtos;
    }

    public Lote() {
    }
}
