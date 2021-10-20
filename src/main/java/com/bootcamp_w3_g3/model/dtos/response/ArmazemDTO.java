package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Armazem;

public class ArmazemDTO {
    private String nome;
    private String endereco;
    private String uf;

    public ArmazemDTO() {
    }

    public ArmazemDTO(String nome, String endereco, String uf) {
        this.nome = nome;
        this.endereco = endereco;
        this.uf = uf;
    }

    public static ArmazemDTO converter(Armazem armazem) {
        return new ArmazemDTO(armazem.getNome(), armazem.getEndereco(), armazem.getUf());
    }


}
