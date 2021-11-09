package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.advisor.EntityNotFoundException;
import com.bootcamp_w3_g3.model.entity.Comprador;
import com.bootcamp_w3_g3.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 * @author Alex Cruz
 */
@Service
public class CompradorService {


    private CompradorRepository compradorRepository;

    @Autowired
    public CompradorService(CompradorRepository compradorRepository){
        this.compradorRepository = compradorRepository;
    }

    @Transactional
    public Comprador salvar(Comprador comprador) { return compradorRepository.save(comprador); }

    public Comprador obter(String codigo) {
        try {
            return compradorRepository.findByCodigo(codigo);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("comprador não encontrado");
        }
    }

    public Comprador atualizar(Comprador comprador) {
        Comprador compradorEdited = compradorRepository.getByCodigo(comprador.getCodigo());
        compradorEdited.setNome(comprador.getNome());
        compradorEdited.setSobrenome(comprador.getSobrenome());
        compradorEdited.setTelefone(comprador.getTelefone());
        compradorEdited.setEndereco(comprador.getEndereco());

        return compradorRepository.save(compradorEdited);
    }

}
