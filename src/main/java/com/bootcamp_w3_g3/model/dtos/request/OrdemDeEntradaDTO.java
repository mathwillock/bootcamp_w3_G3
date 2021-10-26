package com.bootcamp_w3_g3.model.dtos.request;


import com.bootcamp_w3_g3.model.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * @author Joaquim Borges
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdemDeEntradaDTO {

    private String codigoSetor;
    private String codigoRepresentante;
    private String codigoVendedor;
    private Integer quantidade;
    private Produto produto;
    private LocalDate dataFabricacao;
    private LocalDate dataVencimento;




}
