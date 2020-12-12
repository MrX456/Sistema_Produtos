/*
 * Classe responsavel pela implementação dos métodos de controle de Produto
 */
package com.system.controller;

import com.system.model.bean.Produto;

/*
 * Sistema Produtos/ Controller / Produto
 * @author MRX
 * Version : 1.0.0
 */
public class ControllerProduto extends Controller implements IControllerProduto {

    @Override
    public boolean validate(Produto produto) {
        
        if (produto.getDescricao().trim().isEmpty() || produto.getDescricao().length() > 80) {
            
            //MESSAGE HERE
            return false;
            
        }
        
        if(produto.getQtde().toString().trim().isEmpty()) {
            
             //MESSAGE HERE
            return false;
            
        }
        
        if(produto.getValor().toString().trim().isEmpty()) {
            
             //MESSAGE HERE
            return false;
            
        }
        
        if(produto.getCategoria().getId().toString().trim().isEmpty()) {
            
             //MESSAGE HERE
            return false;
            
        }
        
        return true;
    }
    
}
