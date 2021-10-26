package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Vendedor;
import lombok.Getter;

/**
 *
 * @Autor hugo damm
 */

@Getter
public class VendedorForm {

    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String endereco;

    public VendedorForm() {
    }

    public VendedorForm(String nome, String sobrenome, String cpf, String telefone, String endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Vendedor converte(){
        return new Vendedor(nome, sobrenome, cpf, telefone, endereco);
    }
}
