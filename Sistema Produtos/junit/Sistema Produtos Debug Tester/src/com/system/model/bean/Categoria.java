/*
 * Classe responsavel pelo mapeamento da tabela Categoria
 * Subclasse de Super e Objetos
 */

package com.system.model.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * Sistema Produtos/ Model / Bean / Categoria
 * @author MRX
 * Version : 1.0.0
 */

@Entity
public class Categoria extends Objetos {
    
    //#region - Colunas
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dt_registro;
    
    @Lob
    private Byte[] icone;
    //#endregion

    public Date getDt_registro() {
        return dt_registro;
    }

    public void setDt_registro(Date dt_registro) {
        this.dt_registro = dt_registro;
    }

    public Byte[] getIcone() {
        return icone;
    }

    public void setIcone(Byte[] icone) {
        this.icone = icone;
    }
    
}
