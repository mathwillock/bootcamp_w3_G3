package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author hugo damm
 */

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Long> {

    Comprador findByCodigo(String codigo);

    Comprador deleteByCodigo(String codigo);

    Comprador getByCodigo(String codigo);

    Comprador getCompradorByCpf(String cpf);

}
