package com.bootcamp_w3_g3.model.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Marcelo de Oliveira Santos
 * @author Joaquim Borges
 * Classe Dimensao: usada como um atributo de classes dimensionahveis do projeto,
 * retornando medidas e propriedades geomehtricas bahsicas para a logihstica.
 */


@Entity
@NoArgsConstructor
@Setter
public class Dimensao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double comprimento;
    private Double largura;
    private Double altura;


    @Autowired
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
