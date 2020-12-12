/*
 * Interface responsavel pelos métodos de controle de Produto
 */

package com.system.controller;

import com.system.model.bean.Produto;

/*
 * Sistema Produtos/ Controller / IProduto
 * @author MRX
 * Version : 1.0.0
 */

public interface IControllerProduto {
    
     public abstract boolean validate(Produto produto);
    
}
