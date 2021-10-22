package com.bootcamp_w3_g3.model.entity;


import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Marcelo de Oliveira Santos
 * Classe Dimensao: usada como um atributo de classes dimensionahveis do projeto,
 * retornando medidas e propriedades geomehtricas bahsicas para a logihstica.
 */

@Entity
@Setter
public class Dimensao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double comprimento;
    private Double largura;
    private Double altura;

    public Dimensao(){}

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
