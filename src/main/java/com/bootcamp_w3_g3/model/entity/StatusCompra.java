package com.bootcamp_w3_g3.model.entity;

import lombok.*;

import javax.persistence.Entity;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusCompra {


    CANCELADO("CANCELADO"),
    CONCLUIDO("CONCLUIDO"),
    PENDENTE("PENDENTE");

    private String statusDaCompra;


}
