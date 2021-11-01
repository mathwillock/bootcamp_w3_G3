package com.bootcamp_w3_g3.repository;

import com.bootcamp_w3_g3.model.entity.Carrinho;

public interface CarrinhoRepository {

    Carrinho getById(Integer id);

    Carrinho getByCodigo (Integer codigo);

    Carrinho getCompradorById (Integer id);


}
