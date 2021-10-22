package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Estoque;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Marcelo de Oliveira Santos
 */

@Repository
public interface EstoqueRepository
{
    Estoque findByCodigo (Long codigo);

    Estoque deleteByCodigo(Long codigo);

    Estoque save(Estoque estoque);

    List<Estoque> findAll();
}
