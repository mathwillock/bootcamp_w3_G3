package com.bootcamp_w3_g3.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author Alex Cruz
 */

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Representante extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;


    /**
     *     private String nome;
     *     private String sobrenome;
     *     private String cpf;
     *     private String telefone;
     *     private String endereco;
     */
//    public Representante(String nome, String sobreNome, String cpf, String telefone, String endereco, Long id, String codigo) {
//        super(nome, sobreNome, cpf, telefone, endereco);
//        this.id = id;
//        this.codigo = codigo;
//    }
}
