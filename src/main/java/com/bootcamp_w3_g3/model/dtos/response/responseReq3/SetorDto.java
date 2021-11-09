package com.bootcamp_w3_g3.model.dtos.response.responseReq3;

import com.bootcamp_w3_g3.model.entity.Setor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetorDto {

    private String codigo;
    private ArmazemDto armazemDto;


    public static SetorDto converter(Setor setor) {
        return SetorDto.builder()
                .codigo(setor.getCodigo())
                .armazemDto(ArmazemDto.converter(setor.getArmazem())).build();
    }
}
