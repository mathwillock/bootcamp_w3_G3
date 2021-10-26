package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.OrdemDeEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Joaquim Borges
 */
public interface OrdemDeEntradaRepository extends JpaRepository<OrdemDeEntrada, Long> {

    OrdemDeEntrada findByNumeroDaOrdem(Integer numero);
}
