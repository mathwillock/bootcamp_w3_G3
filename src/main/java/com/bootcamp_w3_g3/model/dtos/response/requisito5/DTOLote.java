package com.bootcamp_w3_g3.model.dtos.response.requisito5;

import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Marcelo de Oliveira Santos
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DTOLote
{
    private Integer numero;
    private Integer codProduto;
    private TipoProduto tipoProduto;
    private LocalDate dataDeValidade;
    private Integer quantidadeAtual;

    public static DTOLote converter(Lote lote) {
        return DTOLote.builder()
                .numero(lote.getNumero())
                .codProduto(lote.getProduto().getCodigoDoProduto())
                .tipoProduto(lote.getProduto().getTipoProduto())
                .dataDeValidade(lote.getDataDeValidade())
                .quantidadeAtual(lote.getQuantidadeAtual())
                .build();

    }
}
