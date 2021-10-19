package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Dimensao;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hugo damm
 */

@Setter
@Getter
public class SetorDTO {

    private String nome;
    private String tipoProduto;
    private float temperaturaMin;
    private float temperaturaMax;
    private Dimensao dimensao;
    private Representante representante;

    public SetorDTO() {
    }

    public SetorDTO(String nome, String tipoProduto, float temperaturaMin, float temperaturaMax, Dimensao dimensao, Representante representante) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
        this.dimensao = dimensao;
        this.representante = representante;
    }

    public static SetorDTO converter(Setor setor){
        return new SetorDTO(
                setor.getNome(),
                setor.getTipoProduto(),
                setor.getTemperaturaMin(),
                setor.getTemperaturaMax(),
                new Dimensao(setor.getLargura(),setor.getComprimento(),setor.getAltura()),
                setor.getRepresentante()
        );
    }

    public static List<SetorDTO> converterLista(List<Setor> setorList){
        List<SetorDTO> setorDTOList = new ArrayList<>();
        for (Setor setor : setorList){
            setorDTOList.add(new SetorDTO(
                    setor.getNome(),
                    setor.getTipoProduto(),
                    setor.getTemperaturaMin(),
                    setor.getTemperaturaMax(),
                    new Dimensao(setor.getLargura(),setor.getComprimento(),setor.getAltura()),
                    setor.getRepresentante()
            ));
        }
        return setorDTOList;
    }
}
