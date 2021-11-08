package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.advisor.EntityNotFoundException;
import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Setor;
import com.bootcamp_w3_g3.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author hugo damm
 */

@Service
public class SetorService {

    @Autowired
    private final SetorRepository setorRepository;

    @Autowired
    private ArmazemService armazemService;

    @Autowired
    public SetorService(SetorRepository setorRepository){
        this.setorRepository = setorRepository;
    }

    @Transactional
    public Setor salvarSetor(Setor setor){
        return setorRepository.save(setor);
    }


    public Setor obterSetor(String codigo){
       Setor setor = setorRepository.findByCodigo(codigo);
       if (setor != null){
           return setor;
       }
       throw new EntityNotFoundException("setor não encontrado");
    }

    public List<Setor> listarSetores(){
        return setorRepository.findAll();
    }


    public Armazem retornaArmazem(String codigo){
       return armazemService.obterArmazem(codigo);
    }

    public Setor atualizarSetor(Setor setor){
        Setor editedSetor = setorRepository.findByCodigo(setor.getCodigo());

        editedSetor.setNome(setor.getNome());
        editedSetor.setTipoProduto(setor.getTipoProduto());
        editedSetor.setArmazem(setor.getArmazem());

        return setorRepository.save(editedSetor);
    }

    public Setor removerSetor(Long id) {
        setorRepository.deleteById(id);
        return null;
    }

}
