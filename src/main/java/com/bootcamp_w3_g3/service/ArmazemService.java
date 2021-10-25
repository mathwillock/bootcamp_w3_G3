package com.bootcamp_w3_g3.service;


/**
 * @author Matheus Willock
 */
import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Setor;
import com.bootcamp_w3_g3.repository.ArmazemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmazemService {

    private ArmazemRepository armazemRepository;

    @Autowired
    public ArmazemService(ArmazemRepository armazemRepository){
        this.armazemRepository = armazemRepository;
    }

    public ArmazemService() {}

    public Armazem criarArmazem(Armazem armazem){
        return armazemRepository.save(armazem);
    }

    public Armazem obterArmazem(Integer cod) {
        return armazemRepository.findByCodArmazem(cod);
    }

    public Armazem deletarArmazem(Integer cod){
        return armazemRepository.deleteByCodArmazem(cod);
    }

    public Armazem atualizarArmazem(Armazem armazem) {
        Armazem editedArmazem = armazemRepository.getById(armazem.getId());

        editedArmazem.setSetoresDoArmazem(armazem.getSetoresDoArmazem());
        editedArmazem.setRepresentante(armazem.getRepresentante());
        editedArmazem.setEndereco(armazem.getEndereco());
        editedArmazem.setUf(armazem.getUf());
        editedArmazem.setNome(armazem.getNome());

        return armazemRepository.save(editedArmazem);

    }

    public Armazem buscarRepresentante(Integer codigo) {
        return armazemRepository.findByRepresentanteCodigo(codigo);
    }

    public Armazem listarSetoresDoArmazem(List<Setor> setoresDoArmazem) {
       return armazemRepository.findAllBySetoresDoArmazem(setoresDoArmazem);
    }

}
