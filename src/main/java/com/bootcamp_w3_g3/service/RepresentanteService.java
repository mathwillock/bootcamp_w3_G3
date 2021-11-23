package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.advisor.EntityNotFoundException;
import com.bootcamp_w3_g3.advisor.handler.DeleteException;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.repository.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * Classe RepresentanteService criada para implmentação do CRUD através das chamadas em métodos da representanteRepository.
 * Bem como aplicar camada dde regra de negócios neccessária.
 *
 * @author Alex Cruz
 */
@Service
public class RepresentanteService {

    private final RepresentanteRepository representanteRepository;

    @Autowired
    public RepresentanteService(RepresentanteRepository representanteRepository) {
        this.representanteRepository = representanteRepository;
    }

    /**
     * Este método valida os campos: nome, sobrenome e cpf
     * @param representante Passamos o objeto representante.
     * @return O save do objeto.
     * @author Matheus Wllock
     */
    @Transactional
    public Representante salvar(Representante representante) {

        int validateCpf = representante.getCpf().length();
        String nome = representante.getNome();
        String sobrenome = representante.getSobrenome();
        Representante repCPF = representanteRepository.getRepresentanteByCpf(representante.getCpf());
        Representante repCod = representanteRepository.getByCodigo(representante.getCodigo());

        if( repCod == null && repCPF == null && validateCpf == 11 && nome != null && !nome.equals("") && !nome.equals(" ")
                && sobrenome != null && !sobrenome.equals("") && !sobrenome.equals(" ")
        ) {
            return representanteRepository.save(representante);
        } if (sobrenome == null || sobrenome.equals("") || sobrenome.equals(" ")) {
            throw new EntityNotFoundException("sobrenome inválido");
        } if (nome == null || nome.equals("") || nome.equals(" ")) {
            throw new EntityNotFoundException("nome inválido");
        } if (repCPF != null) {
            throw new EntityNotFoundException("CPF já cadastrado");
        }if (repCod != null) {
            throw new EntityNotFoundException("Código do representante já cadastrado!");
        }

        throw new EntityNotFoundException("CPF inválido");

    }
    public Representante obter(String codigo) {
        Representante representante = representanteRepository.findByCodigo(codigo);
        if (representante != null ){
            return representante;
        }
        throw new EntityNotFoundException("representante não encontrado");
    }

    public List<Representante> listar() {
        return representanteRepository.findAll();
    }

    public Representante atualizar(Representante representante) {
        Representante representanteEdited = representanteRepository.getByCodigo(representante.getCodigo());
        representanteEdited.setNome(representante.getNome());
        representanteEdited.setSobrenome(representante.getSobrenome());
        representanteEdited.setTelefone(representante.getTelefone());
        representanteEdited.setEndereco(representante.getEndereco());

        return representanteRepository.save(representanteEdited);
    }

    public Representante apagar(Long id) {
        try {
            representanteRepository.deleteById(id);
        } catch (NoSuchElementException | IllegalArgumentException | EmptyResultDataAccessException e) {
            throw new DeleteException("Erro ao excluir o representante, ou ID inválido.");
        }

        return null;

    }
}
