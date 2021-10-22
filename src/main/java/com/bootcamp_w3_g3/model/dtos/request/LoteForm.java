package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Dimensao;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Joaquim Borges
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class LoteForm {

    private Integer numero;
    private LocalDate dataDeValidade;
    private Dimensao dimensao;
    private Integer quantidadeDeIntens;
    private List<Produto> produtos;



    public Lote converte() {
        return Lote.builder()
                .numero(numero)
                .dataDeValidade(dataDeValidade)
                .dimensao(dimensao)
                .quantidadeDeIntens(quantidadeDeIntens)
                .produtos(produtos)
                .build();

    }
}
