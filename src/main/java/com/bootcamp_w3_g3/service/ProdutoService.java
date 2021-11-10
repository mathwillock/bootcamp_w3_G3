package com.bootcamp_w3_g3.service;
import com.bootcamp_w3_g3.advisor.EntityNotFoundException;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
import com.bootcamp_w3_g3.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * Classe ProdutoService criada para implmentação do CRUD através das chamadas em métodos da produtoRepository.
 * Bem como aplicar camada dde regra de negócios neccessária.
 *
 * @author Alex Cruz
 * @author  Joaquim Borges
 * @author  Matheus Willock
 */

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository produtoRepository;

    @Autowired

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto salvar(Produto produto) { return produtoRepository.save(produto); }

    public Produto obter(Integer codigo) {
        Produto produto = produtoRepository.findByCodigoDoProduto(codigo);
        if (produto != null){
            return produto;
        }
        throw  new EntityNotFoundException("produto não encontrado");
    }



    public List<Produto> listarPorCategoria(TipoProduto categoria){
            switch (categoria) {
                case CONGELADOS:
                 return produtoRepository.findAllByTipoProduto(TipoProduto.CONGELADOS);

                case FRESCOS:
                    return produtoRepository.findAllByTipoProduto(TipoProduto.FRESCOS);

                case REFRIGERADOS:
                    return produtoRepository.findAllByTipoProduto(TipoProduto.REFRIGERADOS);
            }
            return null;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto atualizar(Produto produto) {
        Produto produtoEdited = produtoRepository.findByCodigoDoProduto(produto.getCodigoDoProduto());
        produtoEdited.setPreco(produto.getPreco());
        produtoEdited.setNome(produto.getNome());
        produtoEdited.setCodLote(produto.getCodLote());
        produtoEdited.setTipoProduto(produto.getTipoProduto());
        produtoEdited.setTemperaturaIndicada(produto.getTemperaturaIndicada());

        return produtoRepository.save(produtoEdited);
    }

    public Produto apagar(Long id) {
       produtoRepository.deleteById(id);
       return null;
    }




    /**
     * método para listar todos os lotes de forma ordenada; por número do Lote, quantidadeMinima, vencimento.
     * @param codProduto
     * @param tipoDeOrdenacao
     * @return loteListProdutos
     */
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
                loteListProdutos.stream().sorted(
                        (lote1, lote2) -> lote1.getDataDeValidade().compareTo(lote2.getDataDeValidade())
                );
            break;
        }

        return loteListProdutos;
    }



}
