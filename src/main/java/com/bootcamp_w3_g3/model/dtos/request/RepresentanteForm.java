package com.bootcamp_w3_g3.model.dtos.request;

import com.bootcamp_w3_g3.model.entity.Representante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Criado para recceber payload com as informacõesmm do representante.
 *
 *
 * @autor Alex Cruz
 */
@Getter
@Builder
@AllArgsConstructor
public class RepresentanteForm {

    private String codigo;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String endereco;

    public RepresentanteForm() {
    }

    /**
     * Método criado sem reccebimento sem parametro, e retornando apenas um nono Representante.
     *
     * @return representante
     */
    public Representante converte(){
        return  Representante.builder()
                .codigo(this.codigo)
                .nome(this.nome)
                .sobrenome(this.sobrenome)
                .cpf(this.cpf)
                .telefone(this.telefone)
                .endereco(this.endereco).build();
    }

}
