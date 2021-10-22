package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Estoque;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EstoqueDTO
{

    private TipoProduto tipoDeProduto;
    private Double quantidade;
    private Long codEstoque;

    
    public EstoqueDTO() {}

    @Autowired
    public EstoqueDTO(TipoProduto tipoDeProduto, Double quantidade, Long codEstoque)
    {
        this.tipoDeProduto = tipoDeProduto;
        this.quantidade = quantidade;
        this.codEstoque = codEstoque;
    }

    public EstoqueDTO converter(Estoque estoque)
    {
        return new EstoqueDTO(estoque.getTipoDeProduto(), estoque.getQuantidade(), estoque.getCodEstoque());
    }

    public List<EstoqueDTO> converterLista (List<Estoque> listaEstoque)
    {
        List<EstoqueDTO> listaConvertida = new ArrayList<>();

        for (Estoque e: listaEstoque)
        {
            listaConvertida.add(converter(e));
        }

        return listaConvertida;
    }
}
