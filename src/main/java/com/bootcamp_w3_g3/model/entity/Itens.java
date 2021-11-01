package com.bootcamp_w3_g3.model.entity;

/**
 * @author Math Willock
 */
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Itens {

    @Id
    private Long Id;

    @OneToOne
    private Produto produto;
    private Integer quantidade;



}
