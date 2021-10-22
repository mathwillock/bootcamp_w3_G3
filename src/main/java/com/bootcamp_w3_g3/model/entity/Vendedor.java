package com.bootcamp_w3_g3.model.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hugo damm
 */

@Setter
@Getter
public class Vendedor extends Pessoa {

    private int codigo;

    public Vendedor(String nome, String sobrenome, String cpf, String telefone, String endereco) {
        super(nome, sobrenome, cpf, telefone, endereco);
    }

}
