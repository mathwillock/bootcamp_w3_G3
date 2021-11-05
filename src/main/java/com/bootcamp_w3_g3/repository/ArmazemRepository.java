package com.bootcamp_w3_g3.repository;


import com.bootcamp_w3_g3.model.entity.Armazem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matheus Willock
 */
@Repository
public interface ArmazemRepository extends JpaRepository<Armazem, Long> {

    Armazem findByCodArmazem(String cod);

    Armazem deleteByCodArmazem(String cod);

}
