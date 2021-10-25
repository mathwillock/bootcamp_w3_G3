package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.Setor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class OrdemDeEntradaDTO {

    private Long id;
    private Integer numeroDaOrdem;
    private LocalDate dataDaOrdem;
    private Setor codigoSetor;
    private Armazem codigoArmazem;
    private List<Lote> loteList;

}
