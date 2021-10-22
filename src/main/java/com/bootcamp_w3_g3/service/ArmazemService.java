package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import com.bootcamp_w3_g3.repository.ArmazemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Matheus Willock
 */
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

    public Armazem obterArmazem(Integer numero) {
        return armazemRepository.findByNumero(numero);
    }

    public Armazem deletarArmazem(Integer numero){
        return armazemRepository.deleteByNumero(numero);
    }

    public Armazem atualizarArmazem(Armazem armazem) {
        Armazem editedArmazem = armazemRepository.getById(armazem.getId());

        editedArmazem.setSetoresDoArmazem(armazem.getSetoresDoArmazem());
        editedArmazem.setRepresentantesValidos(armazem.getRepresentantesValidos());
        editedArmazem.setEndereco(armazem.getEndereco());
        editedArmazem.setUf(armazem.getUf());
        editedArmazem.setNome(armazem.getNome());

        return armazemRepository.save(editedArmazem);

    }

    public Armazem buscarRepresentante(String cpf) {
        return null;
    }

    public Armazem buscarSetor(String nome) {
        return null;
    }

    public List<Representante> listarRepresentantesValidos() {
        return null;
    }

    public List<Setor> listarSetoresDoArmazem() {
        return null;
    }

}
