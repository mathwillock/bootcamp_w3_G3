package com.bootcamp_w3_g3.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import lombok.*;


import javax.persistence.*;
/**
 * @author Matheus Willock
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Armazem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String codArmazem;
    private String nome;
    private String endereco;
    private String uf;

    @OneToOne
    private Representante representante;

    public Armazem(String codigoArmazem){
        this.codArmazem = codigoArmazem;
    }

}
