package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;

import java.util.List;

public class ArmazemDTO {

    private String nome;
    private String endereco;
    private String uf;
    private List<Setor> listaDeSetor;
    private List<Representante> RepresentantesValidos;

    public ArmazemDTO() {}

    public ArmazemDTO(String nome, String endereco, String uf, List<Setor> listaDeSetor, List<Representante> representantesValidos) {
        this.nome = nome;
        this.endereco = endereco;
        this.uf = uf;
        this.listaDeSetor = listaDeSetor;
        RepresentantesValidos = representantesValidos;
    }

    public static ArmazemDTO converter(Armazem armazem) {
        return new ArmazemDTO(armazem.getNome(), armazem.getEndereco(), armazem.getUf(), armazem.getSetoresDoArmazem(), armazem.getRepresentantesValidos());
    }


}
