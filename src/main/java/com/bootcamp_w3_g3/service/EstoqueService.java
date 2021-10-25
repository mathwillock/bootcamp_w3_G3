package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Estoque;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marcelo de Oliveira Santos
 */
@Service
public class EstoqueService
{
    private EstoqueRepository estoqueRepository;


    EstoqueService(){}

    @Autowired
    EstoqueService(EstoqueRepository estoqueRepository)
    {
        this.estoqueRepository = estoqueRepository;
    }

    public Estoque salvar (Estoque estoque)
    {
        return estoqueRepository.save(estoque);
    }

    public Estoque obter (Long codEstoque)
    {
        return estoqueRepository.findByCodigo(codEstoque);
    }


    public Estoque atualizar (Estoque estoque)
    {
        Estoque estoqueAtualizado = estoqueRepository.findByCodigo(estoque.getCodEstoque());

        estoqueAtualizado.setCodEstoque(estoque.getCodEstoque());
        estoqueAtualizado.setTipoDeProduto(estoque.getTipoDeProduto());
        estoqueAtualizado.setQuantidade(estoque.getQuantidade());

        return estoqueAtualizado;

    }

    public Estoque apagar (Long codEstoque)
    {
        return estoqueRepository.deleteByCodigo(codEstoque);
    }

    public List<Estoque> listar()
    {
        return estoqueRepository.findAll();
    }

}
