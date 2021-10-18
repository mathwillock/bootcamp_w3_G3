package com.bootcamp_w3_g3.model.entity;

/**@author Marcelo de Oliveira Santos
 * 
 * Enum utilizado para a asserção dos tres tipos de produtos especificados:
 * CONGELADOS FRESCOS E REFRIGERADOS
 * @version 0.0.1
 */
public enum TipoProduto {

    CONGELADOS("CONGELADOS"),
    FRESCOS("FRESCOS"),
    REFRIGERADOS("REFRIGERADOS");


    private String tipoDeProduto;

    TipoProduto(String tipoDeProduto)
    {
        this.tipoDeProduto = tipoDeProduto;
    }

    public String getTipoDeProduto() {
        {
            return tipoDeProduto;
        }
    }
}
