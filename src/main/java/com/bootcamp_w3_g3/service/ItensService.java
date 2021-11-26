package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.advisor.EntityNotFoundException;
import com.bootcamp_w3_g3.model.entity.Itens;
import com.bootcamp_w3_g3.model.entity.TipoProduto;
import com.bootcamp_w3_g3.repository.ItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex Cruz
 */

@Service
public class ItensService {

    private final ItensRepository itensRepository;

    @Autowired
    public ItensService(ItensRepository itensRepository){
        this.itensRepository = itensRepository;
    }

    @Transactional
    public Itens salvar(Itens itens) {
        return itensRepository.save(itens);
    }

    public List<Itens> listar() {
        return itensRepository.findAll();
    }

    public List<Itens> listarCombos(String p, String a, String s) {
        List<Itens> combo1 = new ArrayList<>();
        
        double pPreco = 0.0;
        String pNome = null;

        switch (p) {
            case "carne" :
                pNome = "Carne Vermelha";
                pPreco = 46.0;
            break;
            case "peixe":
                pNome = "Salmão";
                pPreco = 73.0;
            break;
            case "frango":
                pNome = "Frango";
                pPreco = 30.0;
            break;

        }

        double aPreco = 0.0;
        String aNome = null;

        switch (a) {
            case "comum" :
                aNome = "Arroz comum";
                aPreco = 20;
            break;
            case "integral":
                aNome = "Arroz integral";
                aPreco = 23.0;
            break;
        }

        double sPreco = 0.0;
        String sNome = null;

        switch (s) {
            case "laranja" :
                sNome = "Be One Laranja 4L";
                sPreco = 22;
            break;
            case "uva":
                sNome = "Be One Uva 4L";
                sPreco = 22.0;
            break;
        }

        for(Itens item : listar()) {
            if(item.getProduto().getCodigoDoProduto() == 2003) {
                item.setQuantidade(7);
                item.getProduto().setNome(pNome);
                item.getProduto().setPreco(pPreco);

                combo1.add(item);
            }  if(item.getProduto().getCodigoDoProduto() == 2001) {
                item.setQuantidade(2);
                item.getProduto().setNome(aNome);
                item.getProduto().setPreco(aPreco);

                combo1.add(item);
            } if(item.getProduto().getCodigoDoProduto() == 2002) {
                item.setQuantidade(2);
                item.getProduto().setNome(sNome);
                item.getProduto().setPreco(sPreco);

                combo1.add(item);
            }
        }
            return combo1;



    }


}


