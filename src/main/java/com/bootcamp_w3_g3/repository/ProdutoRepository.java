package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Alex Cruz
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    Produto findByCodigoDoProduto(Integer codigo);

    Produto deleteProdutosByCodigoDoProduto(Integer codigo);

    List<Produto> findAllByTipoProduto(TipoProduto tipoProduto);




}


