package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;


/**
 *
 * @Autor Alex Cruz
 */

@Getter
@Setter
public class Representante extends Pessoa{

    Integer codigo;

    public Representante(String nome, String sobrenome, String cpf, String telefone, String endereco) {
        super(nome, sobrenome, cpf, telefone, endereco);
    }

    public Representante() {
    }



}
