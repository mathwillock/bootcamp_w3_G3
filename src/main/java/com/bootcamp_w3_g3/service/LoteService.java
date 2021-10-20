package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.advisor.EntityNotFoundException;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Joaquim Borges
 */

@Service
public class LoteService {


    private final LoteRepository loteRepository;

    @Autowired
    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Lote salvar(Lote lote) {
        return loteRepository.save(lote);
    }

    public Lote obter(Long id) {
        Optional<Lote> lote = loteRepository.findById(id);
        if (lote.isPresent())
            return lote.get();

        throw new EntityNotFoundException("Lote não encontrado.");

    }

    public List<Lote> listar() {
        return loteRepository.findAll();
    }

    public Lote atualizar(Lote lote) {
        Lote editedLote = loteRepository.getById(lote.getId());
        editedLote.setDataDeValidade(lote.getDataDeValidade());
        editedLote.setQuantidadeDeIntens(lote.getQuantidadeDeIntens());

        return loteRepository.save(editedLote);
    }

    public Lote apagar(Integer numeroDoLote) {
        return loteRepository.deleteByNumero(numeroDoLote);
    }



}
