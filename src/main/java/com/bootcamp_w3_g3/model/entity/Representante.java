package com.bootcamp_w3_g3.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Class representing a representante tracked by the application.")
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Unique identifier of the person. No two persons can have the same id.", example = "1", required = true, position = 0)
    private Long id;
    private String codigo;
    @ApiModelProperty(notes = "First name of the person.", example = "John", required = true, position = 1)
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String endereco;

}
