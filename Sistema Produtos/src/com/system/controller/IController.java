/*
 * Interface responsavel pelos métodos de controle aplicáveis a todos objetos
 */

package com.system.controller;

/*
 * Sistema Produtos/ IController
 * @author MRX
 * Version : 1.0.0
 */

public interface IController {
    
    public abstract boolean reachMaxLength(int maxLength, String text);
    
}
