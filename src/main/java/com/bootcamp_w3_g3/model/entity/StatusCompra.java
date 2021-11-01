package com.bootcamp_w3_g3.model.entity;

/**
 * @author Math Willock
 */

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusCompra {


    CANCELADO("CANCELADO"),
    CONCLUIDO("CONCLUIDO"),
    PENDENTE("PENDENTE");

    private String statusDaCompra;


}
