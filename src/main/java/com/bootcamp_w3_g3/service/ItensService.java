package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Comprador;
import com.bootcamp_w3_g3.model.entity.Itens;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class ItensService {

    private ItensRepository itensRepository;

    @Autowired
    public ItensService(ItensRepository itensRepository){
        this.itensRepository = itensRepository;
    }

    @Transactional
    public Itens salvar(Itens Itens) { return itensRepository.save(itens); }

    public List<Itens> listar() {
        return itensRepository.findAll();
    }

    public Itens atualizar(Itens itens) {
        Itens ItensEdited = itensRepository.findById(itens.getId());
        ItensEdited.setProduto(itens.getProduto());
        ItensEdited.setQuantidade(itens.getQuantidade());

        return itensRepository.save(ItensEdited);
    }
}
