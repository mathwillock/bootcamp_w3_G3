package com.bootcamp_w3_g3.model.entity;

public class Estoque {

    private Long id;
    private Produto TipoDeProduto = new Produto();
    private Double quantidade;
    private Long CodProduto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getTipoDeProduto() {
        return TipoDeProduto;
    }

    public void setTipoDeProduto(Produto tipoDeProduto) {
        TipoDeProduto = tipoDeProduto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Long getCodProduto() {
        return CodProduto;
    }

    public void setCodProduto(Long codProduto) {
        CodProduto = codProduto;
    }


    public Estoque(Long id, Produto tipoDeProduto, Double quantidade, Long codProduto) {
        this.id = id;
        TipoDeProduto = tipoDeProduto;
        this.quantidade = quantidade;
        CodProduto = codProduto;
    }

    
}
