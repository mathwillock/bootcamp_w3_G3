package com.bootcamp_w3_g3.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @autor Alex Cruz
 */

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdemDeEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numeroDaOrdem;
    private LocalDate dataDaOrdem;
    private String codigoSetor;
    private String codigoArmazem;
    @OneToMany
    private List<Lote> loteList;


}
