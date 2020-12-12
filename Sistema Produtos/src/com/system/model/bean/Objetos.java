/*
 * Superclasse de Categorias e Produto
 * Subclasse de Super
 */

package com.system.model.bean;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/*
 * Sistema Produtos/ Model / Bean / Objetos
 * @author MRX
 * Version : 1.0.0
 */

@MappedSuperclass
public abstract class Objetos extends Super {
    
    @Column(nullable = false, length = 80)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
