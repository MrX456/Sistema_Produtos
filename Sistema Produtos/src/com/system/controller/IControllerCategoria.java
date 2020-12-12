/*
 * Interface responsavel pelos métodos de controle de Categoria
 */

package com.system.controller;

import com.system.model.bean.Categoria;

/*
 * Sistema Produtos/ Controller / ICategoria
 * @author MRX
 * Version : 1.0.0
 */

public interface IControllerCategoria {
    
    public abstract boolean validate(Categoria categoria);
    
}
