package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Dimensao;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import lombok.Getter;

/**
 * @author hugo damm
 */

@Getter
public class SetorForm {

    private String codigo;
    private String nome;
    private String tipoProduto;
    private Dimensao dimensoes;
    private Representante representante;

    public SetorForm(){
    }


    public SetorForm(String codigo, String nome, String tipoProduto, Double temperaturaMin, Double temperaturaMax, Dimensao dimensoes, Representante representante) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.dimensoes = dimensoes;
        this.representante = representante;
    }

    public Setor converte(){
        return new Setor(codigo, nome, tipoProduto, dimensoes, representante );
    }

}

