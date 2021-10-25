package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Setor;
import com.bootcamp_w3_g3.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author hugo damm
 */

@Service
public class SetorService {

    private SetorRepository setorRepository;

    @Autowired
    public SetorService(SetorRepository setorRepository){
        this.setorRepository = setorRepository;
    }

    public Setor salvar(Setor setor){
        return setorRepository.save(setor);
    }

    public Setor obter(String codigo){
        return setorRepository.findByCodigo(codigo);
    }

    public List<Setor> listar(){
        return setorRepository.findAll();
    }

    public Setor atualizar(Setor setor){
        Setor editedSetor = setorRepository.findByCodigo(setor.getCodigo());
        editedSetor.setTipoProduto(setor.getTipoProduto());
        editedSetor.setRepresentante(setor.getRepresentante());

        return setorRepository.save(editedSetor);
    }

}
