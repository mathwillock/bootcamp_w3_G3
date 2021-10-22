package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @autor
 */
@Repository
public interface RepresentanteRepository extends JpaRepository <Representante, Long>{

    Representante findByCodigo(Integer codigo);

    Representante deleteByCodigo(Integer codigo);

    Representante getByCodigo(Integer Codigo);
}
