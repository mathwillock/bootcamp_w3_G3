package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ArmazemDTO {

    private String nome;
    private String endereco;
    private String uf;
    private List<Setor> listaDeSetor;
    private Representante representantesValidos;

    public ArmazemDTO() {}


    public static ArmazemDTO converter(Armazem armazem) {
        return new ArmazemDTO(armazem.getNome(), armazem.getEndereco(), armazem.getUf(), armazem.getSetoresDoArmazem(), armazem.getRepresentantesValidos());
    }


}
