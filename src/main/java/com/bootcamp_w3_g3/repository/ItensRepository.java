package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface ItensRepository extends JpaRepository<Produto, Long> {

        List<Produto> findItensById(Integer id);


    }

