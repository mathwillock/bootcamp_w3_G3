package com.bootcamp_w3_g3.model.dtos.response.responseReq3;

import com.bootcamp_w3_g3.model.entity.Armazem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArmazemDto {

    private String codigo;
    private RepresentanteDto representanteDto;

    public static ArmazemDto converter(Armazem armazem) {
        return ArmazemDto.builder()
                .codigo(armazem.getCodArmazem())
                .representanteDto(RepresentanteDto.converter(armazem.getRepresentante())).build();
    }
}
