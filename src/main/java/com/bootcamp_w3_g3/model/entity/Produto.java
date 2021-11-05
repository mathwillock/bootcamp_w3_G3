package com.bootcamp_w3_g3.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 *
 * @autor Alex Cruz
 */

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private Integer codigoDoProduto;
    private String nome;
    private Double preco;
    private Double temperaturaIndicada;
    private TipoProduto tipoProduto;
    private Integer codLote;

    private Integer codLote;

}
