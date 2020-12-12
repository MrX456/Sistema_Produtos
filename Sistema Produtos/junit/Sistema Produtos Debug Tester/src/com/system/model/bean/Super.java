/*
 * Superclasse de todos objetos mapeados
 */

package com.system.model.bean;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
 * Sistema Produtos/ Model / Bean / Super
 * @author MRX
 * Version : 1.0.0
 */

@MappedSuperclass
public abstract class Super implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
