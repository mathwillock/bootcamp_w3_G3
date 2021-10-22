package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Lote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joaquim Borges
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoteDTO {

    private Integer numero;
    private LocalDate dataDeValidade;
    private Integer quantidadeDeIntens;


    public static LoteDTO converter(Lote lote) {
        return LoteDTO.builder()
                .numero(lote.getNumero())
                .dataDeValidade(lote.getDataDeValidade())
                .quantidadeDeIntens(lote.getQuantidadeDeIntens())
                .build();
    }

    public static List<LoteDTO> converterLista(List<Lote> loteList) {
        List<LoteDTO> loteDTOList = new ArrayList<>();
        for (Lote lote : loteList) {
            loteDTOList.add(LoteDTO.builder()
                    .numero(lote.getNumero())
                    .dataDeValidade(lote.getDataDeValidade())
                    .quantidadeDeIntens(lote.getQuantidadeDeIntens())
                    .build());
        }
        return loteDTOList;
    }
}
