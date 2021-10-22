package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alex Cruz
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    Produto findByCodigo(Integer codigo);

    Produto deleteByCodigo(Integer codigo);

    Produto getByCodigo(Integer codigo);
}


