/*
 * Classe responsavel pela implementação dos métodos de controle de Categoria
 */

package com.system.controller;

import com.system.model.bean.Categoria;

/*
 * Sistema Produtos/ Controller / Categoria
 * @author MRX
 * Version : 1.0.0
 */

public class ControllerCategoria extends Controller implements IControllerCategoria {

    @Override
    public boolean validate(Categoria categoria) {
        
        if (categoria.getDescricao().trim().isEmpty() || categoria.getDescricao().length() > 80) {
            
            //MESSAGE HERE
            return false;
            
        }
        
        return true;
        
    }
    
    
}

