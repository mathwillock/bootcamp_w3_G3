package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Estoque;
import com.bootcamp_w3_g3.model.entity.TipoProduto;

public class EstoqueDTO {

    private TipoProduto tipoDeProduto;
    private Double quantidade;
    private Long codEstoque;

    public EstoqueDTO() {
    }

    public EstoqueDTO(TipoProduto tipoDeProduto, Double quantidade, Long codEstoque) {
        this.tipoDeProduto = tipoDeProduto;
        this.quantidade = quantidade;
        this.codEstoque = codEstoque;
    }

    public static EstoqueDTO converter(Estoque estoque) {
        return new EstoqueDTO(estoque.getTipoDeProduto(), estoque.getQuantidade(), estoque.getCodEstoque());
    }
}
