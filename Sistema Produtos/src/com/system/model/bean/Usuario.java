/*
 * Classe responsavel pelo mapeamento da tabela Usuario
 * Subclasse de Pessoa e Super
 */

package com.system.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;

/*
 * Sistema Produtos/ Model / Bean / Usuario
 * @author MRX
 * Version : 1.0.0
 */

@Entity
public class Usuario extends Super {
    
    //#region - Colunas
    @Column(nullable = false, length = 25, unique = true)
    private String login;
    
    @Column(nullable = false, length = 10)
    private String senha;
    
    @Column(nullable = false, length = 7)
    private String perfil;
    
    @Column(nullable = false, length = 4)
    private String sexo;
    //#endregion

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
     public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
}
