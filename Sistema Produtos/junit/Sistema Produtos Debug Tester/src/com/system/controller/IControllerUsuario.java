/*
 * Interface responsavel pelos métodos de controle de Usuario
 */

package com.system.controller;

import com.system.model.bean.Usuario;

/*
 * Sistema Produtos/ Controller / IUsuario
 * @author MRX
 * Version : 1.0.0
 */

public interface IControllerUsuario {
    
    public abstract boolean validate(Usuario usuario);
    
    public abstract void login(Usuario usuario);
    
}
