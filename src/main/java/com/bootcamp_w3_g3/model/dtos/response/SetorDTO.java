package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Dimensao;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hugo damm
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SetorDTO {

    private String codigo;
    private String nome;
    private String tipoProduto;
    private Dimensao dimensoes;
    private Armazem armazem;

    public static SetorDTO converter(Setor setor){
        return new SetorDTO(
                setor.getCodigo(),
                setor.getNome(),
                setor.getTipoProduto(),
                setor.getDimensoes(),
                setor.getArmazem()
        );
    }

    public static List<SetorDTO> converterLista(List<Setor> setorList){
        List<SetorDTO> setorDTOList = new ArrayList<>();
        for (Setor setor : setorList){
            setorDTOList.add(new SetorDTO(
                    setor.getCodigo(),
                    setor.getNome(),
                    setor.getTipoProduto(),
                    setor.getDimensoes(),
                    setor.getArmazem()
            ));
        }
        return setorDTOList;
    }

}
