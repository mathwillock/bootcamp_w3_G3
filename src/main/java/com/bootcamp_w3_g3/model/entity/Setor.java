package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * @author hugo damm
 */

@Setter
@Getter
@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codigo;
    private String nome;
    private String tipoProduto;
    private Double temperaturaMin;
    private Double temperaturaMax;
    @Embedded
    private Dimensao dimensoes;
    @OneToOne
    private Representante representante;

    public Setor(){}

    public Setor(Integer codigo, String nome, String tipoProduto, Double temperaturaMin, Double temperaturaMax, Dimensao dimensoes, Representante representante ) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
        this.dimensoes = dimensoes;
        this.representante = representante;
    }

    public Setor(String nome, String tipoProduto, Double temperaturaMin, Double temperaturaMax, Dimensao dimensoes, Representante representante) {
    }

}