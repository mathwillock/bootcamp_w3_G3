package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Comprador;
import lombok.*;

/**
 * @autor Joaquim Borges
 * @author Hugo Damm (Refaturação)
 */

@Data
@Builder
@AllArgsConstructor
public class CompradorForm {

    private String codigo;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String endereco;

    public CompradorForm() {
    }

    public Comprador converte(){
        return Comprador.builder()
                .codigo(this.codigo)
                .nome(this.nome)
                .sobrenome(this.sobrenome)
                .cpf(this.cpf)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .build();
    }
}
