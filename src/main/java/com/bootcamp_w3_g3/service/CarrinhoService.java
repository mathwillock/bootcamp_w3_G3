package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.entity.Carrinho;
import com.bootcamp_w3_g3.model.entity.Itens;
import com.bootcamp_w3_g3.repository.CarrinhoRepository;
import com.bootcamp_w3_g3.repository.ItensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class CarrinhoService {

    private CarrinhoRepository carrinhoRepository;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository){
        this.carrinhoRepository = carrinhoRepository;
    }

    @Transactional
    public Carrinho salvar(Carrinho carrinho) { return carrinhoRepository.save(carrinho); }

    public List<Carrinho> listar() {
        return carrinhoRepository.findAll();
    }

    public Carrinho atualizar(Carrinho carrinho) {
        Carrinho carrinhoEdited = carrinhoRepository.getById(carrinho.getId());
        carrinhoEdited.setCodigoComprador(carrinho.getCodigoComprador());
        carrinhoEdited.setDataDeOrdem(carrinho.getDataDeOrdem());
        carrinhoEdited.setItensList(carrinho.getItensList());
        carrinhoEdited.setStatusCompra(carrinho.getStatusCompra());

        return carrinhoRepository.save(carrinhoEdited);
    }
}
