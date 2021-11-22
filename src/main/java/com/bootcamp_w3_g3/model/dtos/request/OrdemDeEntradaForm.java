package com.bootcamp_w3_g3.model.dtos.request;


import com.bootcamp_w3_g3.model.entity.*;
import com.bootcamp_w3_g3.service.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * @author Joaquim Borges
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdemDeEntradaForm {

    private Integer numeroOrdem;
    private String codigoSetor;
    private String codigoRepresentante;
    private String codigoVendedor;
    private Integer qtdLotes;
    private LocalDate dataOrdem;
    private Integer codigoLote;


    /**
     * construindo o payload da ordem de entrada
     */
    public OrdemDeEntrada converte(VendedorService vendedorService, ArmazemService armazemService, SetorService setorService, LoteService loteService) {
       Vendedor vendedor = vendedorService.obter(codigoVendedor);
       Representante representante = armazemService.retornaRepresentanteDoArmazem(codigoRepresentante);
       Setor setor = setorService.obterSetor(codigoSetor);
       Lote lote = loteService.obter(codigoLote);


        return OrdemDeEntrada.builder()
                .dataDaOrdem(dataOrdem)
                .numeroDaOrdem(numeroOrdem)
                .representante(representante)
                .setor(setor)
                .vendedor(vendedor)
                .quantidade(qtdLotes)
                .lote(lote)
                .build();
    }


}
