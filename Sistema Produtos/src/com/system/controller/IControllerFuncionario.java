/*
 * Interface responsavel pelos métodos de controle de Funcionario
 */

package com.system.controller;

import com.system.model.bean.Funcionario;

/*
 * Sistema Produtos/ Controller / IFuncionario
 * @author MRX
 * Version : 1.0.0
 */

public interface IControllerFuncionario {
    
    public abstract boolean validate(Funcionario funcionario);
    
}
