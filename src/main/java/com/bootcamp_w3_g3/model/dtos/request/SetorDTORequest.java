package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Dimensao;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import lombok.Getter;

/**
 * @author hugo damm
 */

@Getter
public class SetorDTORequest {


    private String nome;
    private String tipoProduto;
    private double temperaturaMin;
    private double temperaturaMax;
    private Dimensao dimensoes;
    private Representante representante;

    public SetorDTORequest(){
    }

    public SetorDTORequest(String nome, String tipoProduto, double temperaturaMin, double temperaturaMax, Dimensao dimensoes, Representante representante) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
        this.dimensoes = dimensoes;
        this.representante = representante;
    }

    public Setor converte(){
        return new Setor(nome, tipoProduto, temperaturaMin, temperaturaMin, dimensoes, representante );
    }

}

