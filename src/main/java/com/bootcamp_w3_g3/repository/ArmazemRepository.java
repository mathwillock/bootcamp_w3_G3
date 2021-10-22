package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmazemRepository extends JpaRepository<Armazem, Long> {

    Armazem findByNumero(Integer numero);

    Armazem deleteByNumero(Integer numero);






}
