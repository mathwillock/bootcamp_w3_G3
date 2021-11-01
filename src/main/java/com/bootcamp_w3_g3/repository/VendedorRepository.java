package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marcelo de Oliveira Santos
 */

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, String> {

    Vendedor getByCodigo(String codigo);

    Vendedor getById(Long id);



    @Transactional
    Vendedor deleteById(Long id);


}