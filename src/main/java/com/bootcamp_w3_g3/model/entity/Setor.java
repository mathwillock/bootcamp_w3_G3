package com.bootcamp_w3_g3.model.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * @author hugo damm
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private String tipoProduto;

    @Embedded
    private Dimensao dimensoes;

    @OneToOne
    private Armazem armazem;

    public Setor(String codigo, String nome, String tipoProduto, Dimensao dimensoes, Armazem armazem) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.dimensoes = dimensoes;
        this.armazem = armazem;
    }



}