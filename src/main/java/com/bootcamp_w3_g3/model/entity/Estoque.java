package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Estoque {

    private Long id;
    private TipoProduto tipoDeProduto;
    private Double quantidade;
    private Long codEstoque;

    public Estoque() {
    }

    public Estoque(TipoProduto tipoDeProduto, Double quantidade, Long codEstoque) {
        this.tipoDeProduto = tipoDeProduto;
        this.quantidade = quantidade;
        this.codEstoque = codEstoque;
    }


    public Estoque(Long id, TipoProduto tipoDeProduto, Double quantidade, Long codEstoque) {
        this.id = id;
        this.tipoDeProduto = tipoDeProduto;
        this.quantidade = quantidade;
        this.codEstoque = codEstoque;
    }
}
