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
    private float temperaturaMin;
    private float temperaturaMax;
    private Dimensao dimensao;
    private Representante representante;

    public SetorDTORequest(){
    }

    public SetorDTORequest(String nome, String tipoProduto, float temperaturaMin, float temperaturaMax, Dimensao dimensao, Representante representante) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
        this.dimensao = dimensao;
        this.representante = representante;
    }

    public Setor converte(){
        return new Setor(nome, tipoProduto, temperaturaMin, temperaturaMax, dimensao, representante );
    }
}

