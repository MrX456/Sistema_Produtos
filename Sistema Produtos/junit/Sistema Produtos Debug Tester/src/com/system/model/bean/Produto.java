/*
 * Classe responsavel pelo mapeamento da tabela Produto
 * Subclasse de Super e Objetos
 */

package com.system.model.bean;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 /*
 * Sistema Produtos/ Model / Bean / Produto
 * @author MRX
 * Version : 1.0.0
 */

@Entity
public class Produto extends Objetos {
    
    //#region - Colunas
    
    @Column(nullable = false)
    private Integer qtde;
    
    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal valor;
    
    @ManyToOne
    private Categoria categoria;
    
    //#endregion

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
 
}
