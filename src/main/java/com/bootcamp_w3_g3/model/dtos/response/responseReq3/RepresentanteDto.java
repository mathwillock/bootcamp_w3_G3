package com.bootcamp_w3_g3.model.dtos.response.responseReq3;

import com.bootcamp_w3_g3.model.entity.Representante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @autor Joaquim Borges
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepresentanteDto {

    private String codigo;


    public static RepresentanteDto converter(Representante representante) {
        return RepresentanteDto.builder()
                .codigo(representante.getCodigo()).build();
    }


}
