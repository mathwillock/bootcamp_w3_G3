package com.bootcamp_w3_g3.model.dtos.response.requisito4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DTOArmazem {

    private String codigoArmazem;
    private Integer quantidadeDoProdutos;

}
