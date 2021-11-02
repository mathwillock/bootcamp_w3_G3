package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Carrinho getById(Long id);

    Carrinho getByCodigo (String codigo);

    Carrinho getCompradorById (Long id);

}
