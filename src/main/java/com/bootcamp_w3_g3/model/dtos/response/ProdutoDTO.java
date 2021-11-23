package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Classe criada para que atritibutos escolhidos possam ser retornado ao response.
 *
 * @author Alex Cruz
 */
@Data
@AllArgsConstructor
public class ProdutoDTO {

    private Integer codigoDoProduto;
    private String nome;
    private Double preco;
    private Double temperaturaIndicada;

    public ProdutoDTO() {
    }

    /**
     * Método que converte Produto em ProdutoDTO
     * 
     * @param produto
     * @return produtoDTO9
     */
    public static ProdutoDTO convertEmProdutoDTO(Produto produto){

        return new ProdutoDTO(produto.getCodigoDoProduto(),
                                produto.getNome(),
                                produto.getPreco(),
                                produto.getTemperaturaIndicada()
        );
    }

    /**
     *
     * Método que converte List de Produto em uma List de ProdutoDTO
     *
     * @param produtoList
     * @return produtoDTOList
     */
    public static List<ProdutoDTO> convert(List<Produto> produtoList){
        List<ProdutoDTO> produtoDTOList = new ArrayList<>();
        
        for (Produto produto : produtoList){
            produtoDTOList.add(
                    new ProdutoDTO(produto.getCodigoDoProduto(),
                                    produto.getNome(),
                                    produto.getPreco(),
                                    produto.getTemperaturaIndicada()
                    )
            );
        }
        return produtoDTOList;
    }
            
}
