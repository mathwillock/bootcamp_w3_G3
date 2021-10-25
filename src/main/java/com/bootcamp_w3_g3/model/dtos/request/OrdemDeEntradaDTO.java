package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.OrdemDeEntrada;
import com.bootcamp_w3_g3.model.entity.Setor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Joaquim Borges
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdemDeEntradaDTO {

    private Integer numeroDaOrdem;
    private LocalDate dataDaOrdem;

    @JsonProperty("setor_id")
    private String codigoSetor;

    @JsonProperty("armazem_id")
    private String codigoArmazem;

    private List<Lote> loteList;

    public OrdemDeEntrada converterParaEntity() {
        return OrdemDeEntrada.builder()
                .numeroDaOrdem(numeroDaOrdem)
                .dataDaOrdem(dataDaOrdem)
                .codigoSetor(new Setor(codigoSetor))
                .codigoArmazem(new Armazem(codigoArmazem))
                .loteList(loteList)
                .build();

    }

    public List<Lote> retorna(OrdemDeEntradaDTO ordemDeEntradaDTO){
        return ordemDeEntradaDTO.loteList;
    }

}
