package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Lote;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joaquim Borges
 */

@Getter
public class LoteDTO {

    private int numero;
    private LocalDate dataDeValidade;
    private Integer quantidadeDeIntens;

    public LoteDTO() {
    }

    public LoteDTO(int numero, LocalDate dataDeValidade, Integer quantidadeDeIntens) {
        this.numero = numero;
        this.dataDeValidade = dataDeValidade;
        this.quantidadeDeIntens = quantidadeDeIntens;
    }

    public static LoteDTO converter(Lote lote) {
        return new LoteDTO(lote.getNumero(), lote.getDataDeValidade(), lote.getQuantidadeDeIntens());
    }

    public static List<LoteDTO> converterLista(List<Lote> loteList) {
        List<LoteDTO> loteDTOList = new ArrayList<>();
        for (Lote lote : loteList) {
            loteDTOList.add(new LoteDTO(lote.getNumero(), lote.getDataDeValidade(), lote.getQuantidadeDeIntens()));
        }
        return loteDTOList;
    }
}
