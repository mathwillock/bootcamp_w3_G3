package com.bootcamp_w3_g3.model.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comprador {

    @Id
    private Long Id;

    private String usuario;
    private String senha;



}
