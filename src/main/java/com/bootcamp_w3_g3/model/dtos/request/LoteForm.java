package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Dimensao;

import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.Produto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Joaquim Borges
 */

@Getter
public class LoteForm {

    private Integer numero;
    private LocalDate dataDeValidade;
    private Dimensao dimensao;
    private Integer quantidadeDeIntens;
    private List<Produto> produtos;

    public LoteForm(Integer numero, LocalDate dataDeValidade, Dimensao dimensao, Integer quantidadeDeIntens, List<Produto> produtos) {
        this.numero = numero;
        this.dataDeValidade = dataDeValidade;
        this.dimensao = dimensao;
        this.quantidadeDeIntens = quantidadeDeIntens;
        this.produtos = produtos;
    }

    public LoteForm() {
    }

    public Lote converte() {
        return new Lote(numero, dataDeValidade, dimensao, quantidadeDeIntens, produtos);
    }
}
