package com.bootcamp_w3_g3.service;


import com.bootcamp_w3_g3.advisor.EntityNotFoundException;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Joaquim Borges
 * @autor  Alex Cruz
 */

@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;
    @Autowired
    private ArmazemService armazemService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private SetorService setorService;

    @Autowired
    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }
    
    public LoteService(LoteRepository loteRepository, ProdutoService produtoService){
        this.loteRepository = loteRepository;
        this.produtoService = produtoService;
    }

    @Transactional
    public Lote salvar(Lote lote) {
        Produto produto = produtoService.obter(lote.getProduto().getCodigoDoProduto());
        produto.setCodLote(lote.getNumero());
        produtoService.atualizar(produto);

        return loteRepository.save(lote);
    }

    public Lote obter(Integer numeroDoLote) {
        Lote lote = loteRepository.findByNumero(numeroDoLote);
        if (lote != null){
            return lote;
        }
        throw new EntityNotFoundException("lote não encontrado");
    }

    public List<Lote> listar() {
        return loteRepository.findAll();
    }

    public Lote atualizar(Lote lote) {
        Lote editedLote = loteRepository.findByNumero(lote.getNumero());

        editedLote.setTemperaturaAtual(lote.getTemperaturaAtual());
        editedLote.setTemperaturaMinima(lote.getTemperaturaMinima());
        editedLote.setQuantidadeAtual(lote.getQuantidadeAtual());
        editedLote.setQuantidadeMinina(lote.getQuantidadeMinina());
        editedLote.setDataDeFabricacao(lote.getDataDeFabricacao());
        editedLote.setHoraFabricacao(lote.getHoraFabricacao());
        editedLote.setDataDeValidade(lote.getDataDeValidade());

        return loteRepository.save(editedLote);
    }




}
