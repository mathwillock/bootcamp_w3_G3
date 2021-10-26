package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Dimensao;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hugo damm
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SetorForm {

    private String codigo;
    private String nome;
    private String tipoProduto;
    private Dimensao dimensoes;
    private Armazem armazem;

    public Setor converte(){
        return new Setor(codigo, nome, tipoProduto, dimensoes, armazem);
    }

}

