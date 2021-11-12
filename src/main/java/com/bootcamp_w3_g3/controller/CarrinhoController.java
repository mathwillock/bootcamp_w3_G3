package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.CarrinhoForm;
import com.bootcamp_w3_g3.model.entity.Carrinho;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.service.CarrinhoService;
import com.bootcamp_w3_g3.service.CompradorService;
import com.bootcamp_w3_g3.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Alex Ccruz
 */

@RestController
@RequestMapping("carrinho/")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private CompradorService compradorService;



    @PostMapping("/registrar")
    public ResponseEntity<BigDecimal> registrarPedido(@RequestBody CarrinhoForm carrinhoForm) {
        return new ResponseEntity<>(carrinhoService.registrarPedido(carrinhoForm
                        .converte(produtoService, compradorService)), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Carrinho>> listar(){
        List<Carrinho> carrinhoList = carrinhoService.listar();
        return new ResponseEntity<>(carrinhoList, HttpStatus.OK);
    }

    @PutMapping("/alterar")
    public ResponseEntity<Carrinho> alterar(@RequestBody CarrinhoForm carrinhoForm) {
        Carrinho carrinho = carrinhoService.atualizar(carrinhoForm.converte(produtoService, compradorService));
        return new ResponseEntity<>(carrinho, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carrinho> atualizarPedido(@RequestBody CarrinhoForm carrinhoForm, @PathVariable Long id){
        Carrinho carrinho = carrinhoService
                .alterarPedido(carrinhoForm.converte(produtoService, compradorService), id);
        return new ResponseEntity<>(carrinho, HttpStatus.OK);
    }

    /**
     *endpoint deve listar todos os produtos
     * contidos em um carrinho especifico.
     * @author Joaquim Borges
     */
    @GetMapping("/carrinho/{idCarrinho}")
    public ResponseEntity<List<Produto>> mostrarProdutosDoPedido(@PathVariable Long idCarrinho) {
        return new ResponseEntity<>(carrinhoService.mostrarProdutosDoPedido(idCarrinho), HttpStatus.OK);
    }

}

