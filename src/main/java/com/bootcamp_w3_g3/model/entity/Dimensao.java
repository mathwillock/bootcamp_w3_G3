package com.bootcamp_w3_g3.model.entity;

/**
 * @author Marcelo de Oliveira Santos
 */

public class Dimensao {

    private Double comprimento;
    private Double largura;
    private Double altura;

    public Dimensao(Double comprimento, Double largura, Double altura) {
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
    }


    public Double getComprimento() {
        return comprimento;
    }

    public Double getLargura() {
        return largura;
    }

    public Double getAltura() {
        return altura;
    }


    public Double volume()
    {
        return getComprimento() * getLargura() * getAltura();
    }

    public Double area_piso()
    {
        return getComprimento()*getLargura();
    }


}
