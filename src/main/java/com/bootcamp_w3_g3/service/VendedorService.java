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


    @Autowired
    VendedorRepository vendedorRepository;

    @Autowired
    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Transactional
    public Vendedor salvar(Vendedor Vendedor) {
        return vendedorRepository.save(Vendedor);
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

        assert editedVendedor != null;
        return vendedorRepository.save(editedVendedor);
    }






}
