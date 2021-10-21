package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Dimensao;
import com.bootcamp_w3_g3.model.entity.Produto;
import com.bootcamp_w3_g3.model.entity.Representante;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Classe criada para que atritibutos escolhidos possam ser retornado ao response.
 *
 * @author Alex Cruz
 */
@Getter
public class ProdutoDTO {

    private Integer codigoDoProduto;
    private String nome;
    private BigDecimal preco;
    private LocalDate dataDeValidadae;
    private Double temperaturaIndicada;
    private Dimensao dimensoes;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Integer codigoDoProduto, String nome, BigDecimal preco, LocalDate dataDeValidadae, Double temperaturaIndicada, Dimensao dimensoes) {
        this.codigoDoProduto = codigoDoProduto;
        this.nome = nome;
        this.preco = preco;
        this.dataDeValidadae = dataDeValidadae;
        this.temperaturaIndicada = temperaturaIndicada;
        this.dimensoes = dimensoes;
    }

    /**
     * Método que converte Produto em ProdutoDTO
     * 
     * @param produto
     * @return produtoDTO
     */
    public ProdutoDTO convertEmProdutoDTO(Produto produto){

        return new ProdutoDTO(produto.getCodigoDoProduto(),
                                produto.getNome(),
                                produto.getPreco(),
                                produto.getDataDeValidadae(),
                                produto.getTemperaturaIndicada(),
                                new Dimensao(dimensoes.getComprimento(),dimensoes.getAltura(), dimensoes.getLargura()));
    }

    /**
     *
     * Método que converte List de Produto em uma List de ProdutoDTO
     *
     * @param produtoList
     * @return produtoDTOList
     */
    public List<ProdutoDTO> convert(List<Produto> produtoList){
        List<ProdutoDTO> produtoDTOList = new ArrayList<>();
        
        for (Produto produto : produtoList){
            produtoDTOList.add(
                    new ProdutoDTO(produto.getCodigoDoProduto(),
                                    produto.getNome(),
                                    produto.getPreco(),
                                    produto.getDataDeValidadae(), 
                                    produto.getTemperaturaIndicada(),
                                    new Dimensao(produto.getDimensoes().getComprimento(),
                                    produto.getDimensoes().getAltura(),
                                    produto.getDimensoes().getLargura())));
        }
        return produtoDTOList;
    }
            
}
