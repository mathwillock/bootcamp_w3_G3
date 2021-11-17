package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.dtos.response.responseReq3.SetorDto;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.Produto;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joaquim Borges
 * @autor Alex Cruz
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoteDTO {

    private Integer numero;
    private Integer quantidadeAtual;
    private ProdutoDTO produto;
    private SetorDto setorDto;
    private Double temperaturaAtual;
    private LocalDate dataDeValidade;


    public static LoteDTO converter(Lote lote) {
        return LoteDTO.builder()
                .numero(lote.getNumero())
                .setorDto(SetorDto.converter(lote.getSetor()))
                .produto(ProdutoDTO.convertEmProdutoDTO(lote.getProduto()))
                .temperaturaAtual(lote.getTemperaturaAtual())
                .quantidadeAtual(lote.getQuantidadeAtual())
                .dataDeValidade(lote.getDataDeValidade())
                .build();
    }

    public static List<LoteDTO> converterLista(List<Lote> loteList) {
        List<LoteDTO> loteDTOList = new ArrayList<>();
        for (Lote lote : loteList) {
            loteDTOList.add(LoteDTO.builder()
                    .numero(lote.getNumero())
                    .setorDto(SetorDto.converter(lote.getSetor()))
                    .produto(ProdutoDTO.convertEmProdutoDTO(lote.getProduto()))
                    .temperaturaAtual(lote.getTemperaturaAtual())
                    .quantidadeAtual(lote.getQuantidadeAtual())
                    .dataDeValidade(lote.getDataDeValidade())
                    .build());
        }
        return loteDTOList;
    }
}
