package com.bootcamp_w3_g3.service;


import com.bootcamp_w3_g3.advisor.EntityNotFoundException;
import com.bootcamp_w3_g3.model.dtos.response.requisito4.DTOArmazem;
import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.repository.ArmazemRepository;
import com.bootcamp_w3_g3.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Joaquim Borges
 * @autor  Alex Cruz
 */

@Service
public class LoteService {

    private LoteRepository loteRepository;


    private ProdutoService produtoService;

    private ArmazemRepository armazemRepository;

    @Autowired
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


    /**
     * metodo para listar todos os lotes em que o
     * produto pertence
     * @autor Joaquim Borges
     */
    public List<Lote> retornaLotesDoProduto(Integer codProduto) {
        List<Lote> lotesDoProduto = new ArrayList<>();
        for (Lote lote : listar()) {
            if (lote.getProduto().getCodigoDoProduto().equals(codProduto)) {
                lotesDoProduto.add(lote);
            }
        }
        return lotesDoProduto;
    }

    public List<Lote> retornaLotesDoProdutoOrdenados(Integer codProduto, String tipoDeOrdenacao) {

        List<Lote> loteListProdutos = retornaLotesDoProduto(codProduto);

        switch (tipoDeOrdenacao) {

            case "lote" :
                loteListProdutos.sort(
                        (lote1, lote2) -> Integer.compare(lote1.getNumero(), lote2.getNumero())
                );
            break;

            case "quantidade" :
                loteListProdutos.sort(
                        (lote1, lote2) -> Integer.compare(lote1.getQuantidadeAtual(), lote2.getQuantidadeAtual())
                );
            break;

            case "vencimento" :
                loteListProdutos.sort(
                        (lote1, lote2) -> lote1.getDataDeValidade().compareTo(lote2.getDataDeValidade())
                );
            break;
        }

        return loteListProdutos;
    }

    /**
     * metodo para listar a quantidades total de Produtos
     * por armazém
     * @author Hugo Damm
     */
    public List<DTOArmazem> retornaQuantidadesDoProdutosPorArmazem(Integer codProduto){
        List<DTOArmazem> armazemListProduto = new ArrayList<>();
        List<Lote> quantidadeListProduto = retornaLotesDoProduto(codProduto);

        for (Lote lote : quantidadeListProduto){
            armazemListProduto.add(new DTOArmazem(lote.getSetor().getArmazem().getCodArmazem(),lote.getQuantidadeAtual()));
        }

        return armazemListProduto;

    }


}
