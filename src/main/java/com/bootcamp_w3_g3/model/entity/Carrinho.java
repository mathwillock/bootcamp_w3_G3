package com.bootcamp_w3_g3.model.entity;

/**
 * @author Math Willock
 */
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Carrinho {

    @Id
    private Long Id;
    private String codigo;
    private LocalDate dataDeOrdem;
    private StatusCompra statusCompra;

    @OneToMany
    private List<Itens> itensList;
    private String codigoComprador;

}
