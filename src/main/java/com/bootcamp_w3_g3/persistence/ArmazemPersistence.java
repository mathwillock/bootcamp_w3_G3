package com.bootcamp_w3_g3.persistence;

/**
 * @author mathwillock
 */

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;

import java.util.ArrayList;
import java.util.List;

public class ArmazemPersistence {

    List<Representante> representantesValidos = new ArrayList<>();
    List<Setor> setoresDoArmazem = new ArrayList<>();

    private Armazem armazem = new Armazem();


    /**
     * Adicionaar o representante na lista de representantes do Armazem, para futuras consultadas posteriormente.
     *
     * @param representante
     * @return
     */
    public String adicionarRepresentante(Representante representante) {

        armazem.setRepresentantesValidos(representantesValidos);

        armazem.getRepresentantesValidos().add(representante);

        return "Representante " + representante.getNome() + " foi adicionado.";

    }

    /**
     * Removendo um representante da lista
     * @param representante
     * @return
     */
    public String removerRepresentante(Representante representante) {

        armazem.getRepresentantesValidos().remove(representante);

        return "Representante " + representante.getNome() + " foi removido.";

    }

    /**
     * Buscar um representaante pelo cpf
     * @param cpf
     * @return
     */
    public String buscarRepresentante(String cpf) {

        for (int i = 0; i < armazem.getRepresentantesValidos().size(); i++) {

            if(armazem.getRepresentantesValidos().get(i).getCpf().equals(cpf)) {
                return armazem.getRepresentantesValidos().get(i).getNome() + " " +
                        armazem.getRepresentantesValidos().get(i).getCpf() + " foi encontrado.";

            }
        }
//
        return "Não encontramos o representante.";

    }


    /**
     * Adicionar um setor a lista de setor do armazem
     *
     * @param setor
     * @return
     */
    public String adicionarSetor(Setor setor) {
        armazem.setSetoresDoArmazem(setoresDoArmazem);

        armazem.getSetoresDoArmazem().add(setor);

        return " O setor " + setor.getNome() + " foi criado no Armazem";
    }

    /**
     * @param setor
     * @return remocao um objeto Setor do armazem
     */
    public String removerSetor(Setor setor) {
        armazem.getSetoresDoArmazem().remove(setor);

        return " O setor " + setor.getNome() + " foi removido no Armazem";
    }

    /**
     * @param nome
     * @return objeto da lista que contem o @param
     */
    public String buscarSetor(String nome) {

        for (int i = 0; i < armazem.getSetoresDoArmazem().size(); i++) {
            if (armazem.getSetoresDoArmazem().get(i).getNome().equals(nome)) {
                return "O setor " + setoresDoArmazem.get(i).getNome() + " foi encontrado.";
            }
        }

        return "Não encontramos o representante.";
    }

    /**
     * @return Lista completa de Setores do Armazem.
     */
    public String verSetores () {
       for (int i = 0; i < armazem.getSetoresDoArmazem().size(); i++) {
           System.out.println(
               armazem.getSetoresDoArmazem().get(i).getNome()
           );
       }
       return null;
    }










}
