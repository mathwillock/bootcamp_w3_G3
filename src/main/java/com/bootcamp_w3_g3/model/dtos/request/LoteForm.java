package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Dimensao;
//import com.bootcamp_w3_g3.model.entity.Lote;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Joaquim Borges
 */

@Getter
public class LoteForm {

    private Integer numero;
    private LocalDate dataDeValidade;
    private Dimensao dimensao;
    private Integer quantidadeDeIntens;

    public LoteForm(Integer numero, LocalDate dataDeValidade, Dimensao dimensao, Integer quantidadeDeIntens) {
        this.numero = numero;
        this.dataDeValidade = dataDeValidade;
        this.dimensao = dimensao;
        this.quantidadeDeIntens = quantidadeDeIntens;
    }

    public LoteForm() {
    }

//    public Lote converte() {
//        return new Lote(numero, dataDeValidade, dimensao, quantidadeDeIntens);
//    }
}
