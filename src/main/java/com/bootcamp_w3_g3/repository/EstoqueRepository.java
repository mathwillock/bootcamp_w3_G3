package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Marcelo de Oliveira Santos
 */

@Repository
public interface EstoqueRepository extends JpaRepository< Estoque, Long> {
    Estoque findByCodEstoque(Integer codigo);

    Estoque deleteByCodEstoque(Integer codigo);

}
