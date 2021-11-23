package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.advisor.EntityNotFoundException;
import com.bootcamp_w3_g3.model.entity.Vendedor;
import com.bootcamp_w3_g3.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcelo de Oliveira Santos
 */

@Service
public class VendedorService {



    private VendedorRepository vendedorRepository;

    @Autowired
    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    /**
     * Este método valida os campos: nome, sobrenome e cpf
     * @param vendedor Passamos o objeto representante.
     * @return O save do objeto.
     * @author Matheus Wllock
     */
    @Transactional
    public Vendedor salvar(Vendedor vendedor) {

        int validateCpf = vendedor.getCpf().length();
        String nome = vendedor.getNome();
        String sobrenome = vendedor.getSobrenome();
        Vendedor venCPF = vendedorRepository.getVendedorByCpf(vendedor.getCpf());
        Vendedor venCod = vendedorRepository.getByCodigo(vendedor.getCodigo());

        if( venCod == null && venCPF == null && validateCpf == 11 && nome != null && !nome.equals("") && !nome.equals(" ")
                && sobrenome != null && !sobrenome.equals("") && !sobrenome.equals(" ")
        ) {
            return vendedorRepository.save(vendedor);
        } if (sobrenome == null || sobrenome.equals("") || sobrenome.equals(" ")) {
            throw new EntityNotFoundException("sobrenome inválido");
        } if (nome == null || nome.equals("") || nome.equals(" ")) {
            throw new EntityNotFoundException("nome inválido");
        } if (venCPF != null) {
            throw new EntityNotFoundException("CPF já cadastrado");
        }if (venCod != null) {
            throw new EntityNotFoundException("Código do vendedor já cadastrado!");
        }

        throw new EntityNotFoundException("CPF inválido");

    }
    public Vendedor obter(String codigo) {
        Vendedor vendedor = vendedorRepository.getByCodigo(codigo);
        if (vendedor != null){
            return vendedor;
        }
        throw new EntityNotFoundException("vendedor não encontrado");
    }

    public List<Vendedor> listar() {
        return vendedorRepository.findAll();
    }

    public Vendedor atualizar(Vendedor vendedor) {
        Vendedor editedVendedor = vendedorRepository.getByCodigo(vendedor.getCodigo());
             if (editedVendedor != null) {
                 editedVendedor.setNome(vendedor.getNome());
                 editedVendedor.setSobrenome(vendedor.getSobrenome());
                 editedVendedor.setTelefone(vendedor.getTelefone());
                 editedVendedor.setEndereco(vendedor.getEndereco());
                 editedVendedor.setCpf(vendedor.getCpf());
             }

        return vendedorRepository.save(editedVendedor);
    }






}
