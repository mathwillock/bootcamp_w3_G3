package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.OrdemDeEntrada;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import com.bootcamp_w3_g3.repository.OrdemDeEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Joaquim Borges
 */
@Service
public class OrdemDeEntradaService {

    private final OrdemDeEntradaRepository ordemRepository;
    private final ArmazemService armazemService;
    private final SetorService setorService;
    private final LoteService loteService;

    @Autowired
    public OrdemDeEntradaService(OrdemDeEntradaRepository ordemRepository, ArmazemService armazemService,
                                SetorService setorService, LoteService loteService) {

        this.ordemRepository = ordemRepository;
        this.armazemService = armazemService;
        this.setorService = setorService;
        this.loteService = loteService;
    }

    /**
     * verificar se o representante pertence ao armazem
     */
    private boolean representantePertenceAoArmazem(Integer codigo){
        Representante representante = armazemService.buscarRepresentante(codigo);
        return representante != null;
    }

    /**
     * verificar a existencia do armazem
     */
    private boolean armazemValido(String codigo) {
        Armazem armazem = armazemService.obterArmazem(codigo);
        return armazem != null;
    }

    /**
     * validar o setor
     */
    private boolean setorValido(String codigo, String tipoDeProduto) {
        Setor setor = setorService.obter(codigo);
        return setor.getTipoProduto().equals(tipoDeProduto);
    }


    public OrdemDeEntrada salvarOrdem(OrdemDeEntrada ordemDeEntrada) {
        if(armazemValido(ordemDeEntrada.getCodigoArmazem().getCodArmazem()) &&
            representantePertenceAoArmazem(ordemDeEntrada.getCodigoArmazem().getRepresentante().getCodigo())
            && setorValido(ordemDeEntrada.getCodigoSetor().getCodigo(), ordemDeEntrada.getCodigoSetor().getTipoProduto())){

            return ordemRepository.save(ordemDeEntrada);
        }
        return null;
    }





}
