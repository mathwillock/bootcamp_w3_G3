package com.bootcamp_w3_g3.model.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Joaquim Borges
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private LocalDate dataDeValidade;

    @Embedded
    private Dimensao dimensao;

    private Integer quantidadeDeIntens;

    @OneToMany(mappedBy = "lote", cascade = CascadeType.ALL)
    private List<Produto> produtos;



}
