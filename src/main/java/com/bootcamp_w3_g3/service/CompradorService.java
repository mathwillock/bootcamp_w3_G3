package com.bootcamp_w3_g3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CompradorService {


    private CompradorRepository compradorRepository;

    @Autowired
    public CompradorService(CompradorRepository compradorRepository){
        thwis.compradorRepository = compradorRepository;
    }

    @Transactional
    public Comprador salvar(Comprador comprador) { return compradorRepository.save(comprador); }

    public List<Comprador> listar() {
        return compradorRepository.findAll();
    }

    public Comprador atualizar(Comprador comprador) {
        Comprador compradorEdited = coccmpradoRepository.findByCodigoDocomprador(comprador.getCodigoDoComprador());
        compradorEdited.setPreco(comprador.getPreco());
        compradorEdited.setNome(comprador.getNome());
        compradorEdited.setTemperaturaIndicada(comprador.getTemperaturaIndicada());

        return produtoRepository.save(produtoEdited);
    }

}
