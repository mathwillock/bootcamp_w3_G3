package com.bootcamp_w3_g3.model.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Math Willock
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private LocalDate dataDeOrdem;
    private StatusCompra statusCompra;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Itens> itensList = new ArrayList<>();
    private String codigoComprador;

}
