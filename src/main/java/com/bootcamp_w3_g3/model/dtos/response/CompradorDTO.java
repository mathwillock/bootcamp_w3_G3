package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Comprador;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Joaquim Borges
 * @author Hugo Damm (Refaturação)
 */

@Builder
@Getter
public class CompradorDTO {

    private String codigo;
    private String nome;
    private String sobrenome;

    public CompradorDTO(){
    }

    public CompradorDTO(String codigo, String nome, String sobrenome){
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public static CompradorDTO converter (Comprador comprador){
        return new CompradorDTO(comprador.getCodigo(), comprador.getNome(), comprador.getSobrenome());
    }

    public static List<CompradorDTO> converteListaParaCompradorDTO (List<Comprador> compradorList) {
        List<CompradorDTO> compradorDTOList = new ArrayList<>();
        for (Comprador comprador : compradorList){
            compradorDTOList.add(new CompradorDTO(comprador.getCodigo(), comprador.getNome(), comprador.getSobrenome()));
        }
        return compradorDTOList;
    }

}